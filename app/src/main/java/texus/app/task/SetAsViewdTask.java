package texus.app.task;

import android.content.Context;
import android.os.AsyncTask;

import texus.app.database.DatabasesHelper;
import texus.app.model.Question;

/**
 * Created by sandeep on 6/8/2017.
 */

public class SetAsViewdTask extends AsyncTask<Void, Void, Void> {

    Context context;
    Question object;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public SetAsViewdTask(Question object, Context context) {
        this.object = object;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        if( context != null) {
            DatabasesHelper dbHelper = new DatabasesHelper(context);
            Question.setViewed(dbHelper, object);
            dbHelper.close();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

}
