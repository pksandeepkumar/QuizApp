package texus.app.boot;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import texus.quizapp.SettingsActivity;

public class BootReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		 if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
			 SettingsActivity.setNextAlarm(context);
	        }
	}
}