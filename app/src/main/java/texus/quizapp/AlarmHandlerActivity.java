package texus.quizapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Calendar;


public class AlarmHandlerActivity extends AppCompatActivity {

    NotificationCompat.Builder notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }







    protected void showNotification() {
        notification = new NotificationCompat.Builder(this);
        notification.setContentTitle("You have pending Questions");
        notification.setTicker("Quizapp");
        notification.setSmallIcon(R.mipmap.ic_launcher);
        Intent resultIntent = new Intent(this, HomeActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(contentIntent);

        NotificationManager manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notification.build());

    }

    public class SetNextAlarmTask extends AsyncTask<Void, Void, Void> {
        Context context;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        public SetNextAlarmTask(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(context != null) {
                SettingsActivity.setNextAlarm(context);
            }
        }
    }


}
