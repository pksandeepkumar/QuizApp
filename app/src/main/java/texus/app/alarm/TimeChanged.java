package texus.app.alarm;

/**
 * Created by sandeep on 12/21/2016.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import texus.quizapp.SettingsActivity;

public class TimeChanged extends BroadcastReceiver {
    public TimeChanged() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        SettingsActivity.setNextAlarm(context);
    }
}
