package texus.quizapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.sdsmdg.harjot.rotatingtext.RotatingTextWrapper;
import com.sdsmdg.harjot.rotatingtext.models.Rotatable;

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

    }

    public void feedback(View view) {

    }

    public void reteUsTask(View view) {

    }

    public void shareAppTask(View view) {

    }


}
