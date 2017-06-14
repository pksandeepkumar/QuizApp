package texus.app.task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import texus.app.database.DatabasesHelper;
import texus.app.model.Question;

/**
 * Created by sandeep on 6/8/2017.
 */

public class SaveAnswerTask extends AsyncTask<Void, Void, Void> {

    Context context;
    Question object;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public SaveAnswerTask(Question object, Context context) {
        this.object = object;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        DatabasesHelper dbHelper = new DatabasesHelper(context);
        Question.saveAnswer(dbHelper, object);
        dbHelper.close();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

}
