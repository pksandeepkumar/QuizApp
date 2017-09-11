package texus.quizapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

import texus.app.alarm.AlarmReceiver;
import texus.app.alarm.AlarmServiceClass;
import texus.app.alarm.AlarmSetter;
import texus.app.controls.TextViewWithBoldFont;
import texus.app.database.DatabasesHelper;
import texus.app.dialog.ProgressDialogLarge;
import texus.app.model.DateManager;
import texus.app.model.Question;
import texus.app.preferance.SavedPreferance;
import texus.app.utils.AppMessages;
import texus.quizapp.BaseAppCompatActivity;
import texus.quizapp.R;

public class SettingsActivity extends BaseAppCompatActivity {

    TextView tvTitle;
    Context context;
    ProgressDialogLarge dialogLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_settings);
        initViews();
        setUpToolbar();
    }

    public void setBackButton() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    public void setUpToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.toolbar_quiz_activity_left);
            View customView = actionBar.getCustomView();
            if(customView != null) {
                tvTitle = (TextView) customView.findViewById(R.id.tvTitle);
                tvTitle.setText("Settings");
            }

            setBackButton();
        }
    }

    @Override
    public void initViews() {
        context = this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            return true;
        }
        if(id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void resetQuiz( View view) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        ResetQuizTask task = new ResetQuizTask(context);
                        task.execute();
                        dialog.dismiss();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Do you want to reset quiz?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void feedback( View view) {

    }
    public void likeUs( View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dialogLarge != null) {
            dialogLarge.dismiss();
        }
    }

    public void performSetTime(View view ) {
        final TextViewWithBoldFont tv = (TextViewWithBoldFont) view;
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        TimePickerDialog.OnTimeSetListener l = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tv.setText(getAMPMString(selectedHour, selectedMinute));
                if ( SavedPreferance.getAlarmActive(context) ){
                    SavedPreferance.setAlarmHour( context, selectedHour );
                    SavedPreferance.setAlarmMinute( context, selectedMinute );
                    SettingsActivity.setNextAlarm(context);
                }
            }


        };

        mTimePicker = new TimePickerDialog(view.getContext(), l, hour, minute, false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private String getAMPMString(int selectedHour, int selectedMinute) {
        if(selectedHour >= 13) {
            return setPadding((selectedHour-12)) + ":" + setPadding(selectedMinute) + " PM ";
        }
        return setPadding((selectedHour)) + ":" + setPadding(selectedMinute) + " AM ";
    }

    private String setPadding(int num) {
        return (num < 10) ? "0" + num : "" + num;
    }

    public static void setNextAlarm( Context context) {

        if( !SavedPreferance.getAlarmActive(context)) return;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        long nextAlarmTime = getNextAlarmTime( context );
        long currentTime = Calendar.getInstance().getTimeInMillis();
        long time =  nextAlarmTime - currentTime;
//        alarmManager.set(AlarmManager.RTC_WAKEUP,time,pendingIntent);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                nextAlarmTime, time, pendingIntent);
//        alarmManager.set(AlarmManager.RTC_WAKEUP,time,pendingIntent);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        30 * 1000, pendingIntent);

//        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                nextAlarmTime, pendingIntent);
//        SavedPreferance.setTourPlanAlarmSet(context,true);

    }


    public static long getNextAlarmTime( Context context) {
        int hour = SavedPreferance.getAlarmHour( context );
        int minute = SavedPreferance.getAlarmMinute( context );
        DateManager dm = new DateManager(Calendar.getInstance().getTimeInMillis());
        DateManager dmAlarmTime = new DateManager(Calendar.getInstance().getTimeInMillis());
        dmAlarmTime.setTime(hour, minute, 0);
        int value = dm.compare(dmAlarmTime);
        long alarmTime = dmAlarmTime.getTimeInMilliseconds();
        if(value == DateManager.LESS) {
            //means next notification time is a time in today
            return  dmAlarmTime.getTimeInMilliseconds();
        }

        if( value == DateManager.GREATER || value == DateManager.EQUAL) {
            dmAlarmTime.addDay(1);

        }
        return dmAlarmTime.getTimeInMilliseconds();

    }

    public static void cancelAlarm(Context context) {
        Intent myIntent = new Intent(context, AlarmServiceClass.class);
        PendingIntent pendingIntent = PendingIntent.getService( context, 0, myIntent, 0);
        AlarmManager alarmManager = (AlarmManager) context .getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    public class ResetQuizTask extends AsyncTask<Void, Void, Void> {
        Context context;

        int updatedCount;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogLarge = new ProgressDialogLarge(context);
            dialogLarge.show();
        }

        public ResetQuizTask(Context context) {
            this.context = context;

        }

        @Override
        protected Void doInBackground(Void... params) {
            DatabasesHelper databasesHelper = new DatabasesHelper(context);
            updatedCount = Question.resetQuiz( databasesHelper);
            databasesHelper.close();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            dialogLarge.dismiss();
//            dialogLarge.dismiss();
            if(updatedCount > 0) {
                AppMessages.displayMessage("Quiz reset success!!!");
            }

        }
    }
}
