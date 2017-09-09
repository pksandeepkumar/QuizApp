package texus.app.sync;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import texus.app.database.DatabasesHelper;
import texus.app.model.Question;
import texus.app.preferance.SavedPreferance;
import texus.app.rest.pojos.getquestions.GetQuestionsPOJO;
import texus.app.task.SaveValuesTask;
import texus.app.utils.AppConstance;

/**
 * Created by sandeep on 2/22/2017.
 */

public class SyncData {

    public Context context;
    public boolean errorOccured = false;
    public int numberOfQuestions = 0;

    //If this is true, whole db operations should be in background task
    public boolean doDbOperationsInBackground;


    int mRunningMethodCount = 0;

    OnStartAndEnd onStartAndEnd;

    public interface OnStartAndEnd {
        public void onStart();
        public void onEnd();
    }

    public void setOnStartAndEnd( OnStartAndEnd onStartAndEnd) {
        this.onStartAndEnd = onStartAndEnd;
    }

    public SyncData(Context context) {
        this.context = context;

    }


    //This is called by each method on start (their start)
    public void thisMethodStarted() {
        if(mRunningMethodCount == 0) {
            onStart();
        }
        mRunningMethodCount++;
    }

    //This is called by each method on end (their end)
    public void thisMethodEnds() {
        mRunningMethodCount--;
        if( mRunningMethodCount == 0) onEnd();
    }

    public void deleteData() {
    }
    
    public void onStart() {
        if(onStartAndEnd != null) {
            onStartAndEnd.onStart();
        }
        
    }

    public void onEnd() {
        if(onStartAndEnd != null) {
            onStartAndEnd.onEnd();
        }
    }



    public void fetchQuestions() {
        thisMethodStarted();
        int version = SavedPreferance.getVersion(context);
        fetchQuestions(++version);
    }

    private void fetchQuestions(final  int version) {

        final Question object = new Question();
        Call<GetQuestionsPOJO> call = object.getQuestions(AppConstance.QUIZ_ID, version);
        call.enqueue(new Callback<GetQuestionsPOJO>() {
            @Override
            public void onResponse(Call<GetQuestionsPOJO> call,
                                   Response<GetQuestionsPOJO> response) {

                if( response != null) {
                    if( !response.isSuccessful()) {
                        errorOccured = true;
                        thisMethodEnds();
                        return;
                    }
                }

                GetQuestionsPOJO pojo = response.body();
                if( pojo != null) {
                    List<texus.app.rest.pojos.getquestions.Question> objects =
                            pojo.getQuestions();
                    int size = 0;
                    if(objects != null) {
                        size = objects.size();
                        SaveValuesTask task = new SaveValuesTask(objects, context);
                        task.execute();
                        if( size != 0) {
                            numberOfQuestions = numberOfQuestions + size;
                            SavedPreferance.setVersion(context, version);
                        }
                    }

                    if( size == 0) {
                        SavedPreferance.setVersion(context, version - 1);
                        thisMethodEnds();
                    } else {
                        fetchQuestions( version + 1);
                    }
                }

                thisMethodEnds();


            }
            @Override
            public void onFailure(Call<GetQuestionsPOJO> call, Throwable t) {
                thisMethodEnds();

            }

        });

    }





}
