package texus.app.preferance;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



/**
 * 
 * @author Sandeep Kumar
 *
 */
public class SavedPreferance {
	
	private static final String FIRST_SYNC = "FirstSycn";
	private static final String ALARM_HOUR = "AlarmHour";
	private static final String ALARM_MINUTE = "AlarmMinute";
	private static final String VERSION = "Version";
	private static final String NOTIFICATION_ALARM = "NotificationAlarm";

	public static void setAlarmActive(Context context, boolean status) {
		Editor edit = getPreferance(context).edit();
		edit.putBoolean(NOTIFICATION_ALARM, status);
		edit.commit();
	}

	public static boolean getAlarmActive(Context context) {
		return getPreferance(context).getBoolean(NOTIFICATION_ALARM, true);
	}


	public static void setAlarmHour(Context context, int value) {
		Editor edit = getPreferance(context).edit();
		edit.putInt(ALARM_HOUR, value);
		edit.commit();
	}

	public static int getAlarmHour(Context context) {
		return getPreferance(context).getInt(ALARM_HOUR, 0);
	}

	public static void setAlarmMinute(Context context, int value) {
		Editor edit = getPreferance(context).edit();
		edit.putInt(ALARM_MINUTE, value);
		edit.commit();
	}

	public static int getAlarmMinute(Context context) {
		return getPreferance(context).getInt(ALARM_MINUTE, 0);
	}

	public static void setVersion(Context context, int value) {
		Editor edit = getPreferance(context).edit();
		edit.putInt(VERSION, value);
		edit.commit();
	}

	public static int getVersion(Context context) {
		return getPreferance(context).getInt(VERSION, 0);
	}


	public static void setFirstSync(Context context, boolean status) {
		Editor edit = getPreferance(context).edit();
		edit.putBoolean(FIRST_SYNC, status);
		edit.commit();
	}

	public static boolean getFirstSync(Context context) {
		return getPreferance(context).getBoolean(FIRST_SYNC, false);
	}

	private static SharedPreferences getPreferance(Context context) {
		SharedPreferences preferences = android.preference.PreferenceManager
				.getDefaultSharedPreferences(context);
		return preferences;
	}
}
