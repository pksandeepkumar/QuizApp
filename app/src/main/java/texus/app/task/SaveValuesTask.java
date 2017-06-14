package texus.app.task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import texus.app.database.DatabasesHelper;
import texus.app.rest.pojos.getquestions.Question;

/**
 * Created by sandeep on 6/8/2017.
 */

public class SaveValuesTask  extends AsyncTask<Void, Void, Void> {

    Context context;
    List<Question> objects;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public SaveValuesTask(List<Question> objects, Context context) {
        this.objects = objects;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        DatabasesHelper dbHelper = new DatabasesHelper(context);
        for( Question object: objects) {
            if( object ==  null) continue;
            texus.app.model.Question temp = new texus.app.model.Question(object);
            texus.app.model.Question.insertOrUpdate(dbHelper, temp);
        }
        dbHelper.close();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

}
