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
	private static final String VERSION = "Version";

	public static void setVersion(Context context, int value) {
		Editor edit = getPreferance(context).edit();
		edit.putInt(VERSION, value);
		edit.commit();
	}

	public static int getVersion(Context context) {
//		return 0;
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
