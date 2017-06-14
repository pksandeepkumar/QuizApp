package texus.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sdsmdg.harjot.rotatingtext.RotatingTextWrapper;
import com.sdsmdg.harjot.rotatingtext.models.Rotatable;
import com.wang.avi.AVLoadingIndicatorView;

import texus.app.preferance.SavedPreferance;
import texus.app.sync.SyncData;
import texus.app.utils.AppMessages;

public class MainActivity extends BaseAppCompatActivity {

    TextView tvPleaseWait;
    AVLoadingIndicatorView avlProgress;
    ImageView imSplashScreen;
    TextView tvBe;
    RelativeLayout rlHolder;
    RotatingTextWrapper rotatingTextWrapper;
    boolean errorOccured = false;

//    https://stackoverflow.com/questions/9884202/custom-circle-button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setViews();

        if(SavedPreferance.getVersion(this) == 0) {
            doSync();
        }
    }


    @Override
    public void initViews() {

        imSplashScreen  = (ImageView) findViewById(R.id.imSplashScreen);
        tvBe            = (TextView) findViewById(R.id.tvBe);
        tvPleaseWait    = (TextView) findViewById(R.id.tvPleaseWait);
        rlHolder        = (RelativeLayout) findViewById(R.id.rlHolder);
        avlProgress     = (AVLoadingIndicatorView) findViewById(R.id.avlProgress);
        rotatingTextWrapper = (RotatingTextWrapper) findViewById(R.id.custom_switcher);
    }

    private void setViews() {
        Typeface typefaceThin = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Thin.ttf");
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(), "fonts/reckoner.bold.ttf");
        Glide.with(this) .load(R.drawable.spash_screen1) .into(imSplashScreen);

        rlHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoHomePage();
            }
        });
        tvBe.setTypeface(typefaceThin);

        RotatingTextWrapper rotatingTextWrapper
                = (RotatingTextWrapper) findViewById(R.id.custom_switcher);
        rotatingTextWrapper.setSize(35);
        rotatingTextWrapper.setTypeface(typefaceThin);

        Rotatable rotatable = new Rotatable(Color.parseColor("#FFFFFF"),
                1000, "Wise", "Smart", "Awesome");
        rotatable.setSize(65);
        rotatable.setCenter(true);
        rotatable.setUpdated(true);
        rotatable.setAnimationDuration(700);
        rotatable.setTypeface(typefaceBold);
        rotatable.setInterpolator(new AccelerateInterpolator());
        rotatingTextWrapper.setContent("", rotatable);

        hideProgress();
    }

    private void showProgress() {
        tvPleaseWait.setVisibility(View.VISIBLE);
        avlProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        tvPleaseWait.setVisibility(View.INVISIBLE);
        avlProgress.setVisibility(View.INVISIBLE);
    }

    private void gotoHomePage() {
        int  currentVersion = SavedPreferance.getVersion(this);
        if( currentVersion != 0 ) {
            startPage(HomeActivity.class);
            finish();
        } else {
            if(errorOccured) {
                AppMessages.displayMessage("Something went wrong. Please try later");
                return;
            }
            AppMessages.displayMessage("Downloading questions. Please wait");
        }
    }

    private void doSync() {
        final SyncData syncData = new SyncData(this);
        syncData.setOnStartAndEnd(new SyncData.OnStartAndEnd() {
            @Override
            public void onStart() {
                showProgress();
            }

            @Override
            public void onEnd() {
                hideProgress();
                if(syncData.errorOccured) {
                    errorOccured = true;
                    AppMessages.displayMessage("Something went wrong. Please try later");
                }
            }
        });

        syncData.fetchQuestions();
    }


}
