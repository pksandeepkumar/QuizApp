package texus.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.sdsmdg.harjot.rotatingtext.RotatingTextWrapper;
import com.sdsmdg.harjot.rotatingtext.models.Rotatable;

import texus.app.adapter.WalkThroughAdapter;
import texus.app.dialog.ProgressDialogLarge;
import texus.app.sync.SyncData;
import texus.app.utils.AppMessages;

public class HomeActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
    }

    @Override
    public void initViews() {

    }


    public void startQuiz(View view) {
        startPage(QuizActivity.class);
    }

    public void scoreBoard(View view) {
        startPage(ScoreBoardActivity.class);
    }

    public void chckForNewQuestions(View view) {
        doSync();
    }

    private void doSync() {
        final ProgressDialogLarge progressDialogLarge = new ProgressDialogLarge(this);
        final SyncData syncData = new SyncData(this);
        syncData.setOnStartAndEnd(new SyncData.OnStartAndEnd() {
            @Override
            public void onStart() {
                progressDialogLarge.show();
            }

            @Override
            public void onEnd() {
                progressDialogLarge.hide();
                if(syncData.errorOccured) {
                    AppMessages.displayMessage("Something went wrong. Please try later");
                } else {

                    if( syncData.numberOfQuestions == 0) {
                        showMessage("No new questions available. Please try later");
                    } else {
                        showMessage(syncData.numberOfQuestions + " new questions updated!!!");
                    }

                }

            }
        });

        syncData.fetchQuestions();
    }

    public void showMessage( String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void openSettings(View view) {
        startPage(SettingsActivity.class);
    }

    public void reteUsTask(View view) {

    }

    public void shareAppTask(View view) {

    }

//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
//                .setMessage("Are you sure?")
//                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        Intent intent = new Intent(Intent.ACTION_MAIN);
//                        intent.addCategory(Intent.CATEGORY_HOME);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                        finish();
////                        System.exit(0);
//                    }
//                }).setNegativeButton("no", null).show();
//    }


}
