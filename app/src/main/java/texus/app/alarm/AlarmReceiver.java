package texus.app.alarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import texus.quizapp.AlarmHandlerActivity;
import texus.quizapp.HomeActivity;
import texus.quizapp.MainActivity;
import texus.quizapp.R;

/**
 * Created by sandeep on 5/2/2017.
 */

public class AlarmReceiver  extends BroadcastReceiver {

    private static final String TAG = AlarmReceiver.class.getSimpleName();
    NotificationCompat.Builder notification;
    Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        showNotification();
//        Intent taskActivity = new Intent(context, AlarmHandlerActivity.class);
//        taskActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity( taskActivity);

    }

    protected void showNotification() {
        notification = new NotificationCompat.Builder(context);
        notification.setContentTitle("You have pending Questions");
        notification.setTicker("Quizapp");
        notification.setSmallIcon(R.mipmap.ic_launcher);
        Intent resultIntent = new Intent(context, HomeActivity.class);
//        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(contentIntent);

        NotificationManager manager =(NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notification.build());

    }
}
