package texus.app.alarm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import texus.quizapp.HomeActivity;
import texus.quizapp.R;
import texus.quizapp.SettingsActivity;

public class AlarmServiceClass extends Service {

	public static final int NOTIFICATION_ID = 12412;
	@Override
	public void onCreate() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);

		if( intent != null) {
			String action = intent.getAction();
			if (AlarmSetter.COMMAND.equals(action)) {
				 notifyMe();
			}
		}
		SetNextAlarmTask task = new SetNextAlarmTask(this);
		task.execute();

	}
	
	/**
	 * Create a notification
	 * 
	 */
	@SuppressWarnings("deprecation")
	public void notifyMe() {

		final NotificationManager mgr = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);
		@SuppressWarnings("deprecation")
		Notification note = new Notification(R.mipmap.ic_launcher,
				"You have pending Questions", System.currentTimeMillis());
		PendingIntent i = PendingIntent.getActivity(this, 0, new Intent(this,
				HomeActivity.class), 0);
		
//		note.setLatestEventInfo(this, "IAS Quiz", "You have "
//				+ (DBHelper.getUnAnsweredQuestionsID(this).size()) +" Questions to view", i);
		mgr.notify(NOTIFICATION_ID, note);
	}


	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
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
				Thread.sleep(2000);
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