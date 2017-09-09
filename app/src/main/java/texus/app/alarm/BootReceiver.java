package texus.app.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import texus.quizapp.SettingsActivity;

/**
 * Created by sandeep on 5/2/2017.
 */

public class BootReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SettingsActivity.setNextAlarm(context);
    }

}