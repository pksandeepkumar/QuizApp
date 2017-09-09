package texus.app.alarm;

import java.util.Calendar;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class AlarmSetter {

	public static final String COMMAND = "com.texus.quizapp.NOTIFICATION_ACTION";
	
	public static void setAlarm(Context context, long targetTimeInMillisecond) {

		Intent myIntent = new Intent(context,
			AlarmServiceClass.class);
		myIntent.setAction(COMMAND);
		PendingIntent pendingIntent = PendingIntent.getService(
				context, 123, myIntent,  PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
			alarmManager.set(AlarmManager.RTC_WAKEUP, targetTimeInMillisecond , pendingIntent);
//			alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetTimeInMillisecond
//					,AlarmManager.INTERVAL_DAY , pendingIntent);
	}
	
	
	public static void cancelAlarm(Context context) {

		 Intent myIntent = new Intent(context,
					AlarmServiceClass.class);
		 PendingIntent pendingIntent = PendingIntent.getService(
				 context, 0, myIntent, 0);
		 AlarmManager alarmManager = (AlarmManager) context
						.getSystemService(Context.ALARM_SERVICE);

		 alarmManager.cancel(pendingIntent);
	}
}
