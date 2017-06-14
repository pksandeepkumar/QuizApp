package texus.quizapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import texus.app.adapter.QuizPagerAdapter;
import texus.app.database.DatabasesHelper;
import texus.app.model.Question;
import texus.app.task.SetAsViewdTask;

public class QuizActivity extends BaseAppCompatActivity {

    ViewPager viewPager;
    ArrayList<Question> questions;
    int totalQuestionCount = 0;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_activity);
        setUpToolbar();
        initViews();


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
            actionBar.setCustomView(R.layout.toolbar_quiz_activity);
            View customView = actionBar.getCustomView();
            if(customView != null) {
                tvTitle = (TextView) customView.findViewById(R.id.tvTitle);
            }
            setBackButton();
        }
    }

    public void setTitle( int position, int totalCount) {
        if(tvTitle == null) return;
        tvTitle.setText( "" + (position) + "/" + totalCount );

    }

    @Override
    public void initViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        DatabasesHelper databasesHelper = new DatabasesHelper(this);
        questions = Question.getAllObjectsID(databasesHelper);
        databasesHelper.close();
        totalQuestionCount = questions.size();

        QuizPagerAdapter adapter = new QuizPagerAdapter(this, questions);
        viewPager.setAdapter(adapter);

        setTitle(1,totalQuestionCount );

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setThisQuestionAsViewd(position);
                setTitle(position+1,totalQuestionCount );

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
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

    public void setThisQuestionAsViewd( int position) {
        if( questions == null ) return;
        if(questions.size() <= position) return;
        Question question = questions.get(position);
        SetAsViewdTask task = new SetAsViewdTask(question, this);
        task.execute();
    }


    public void startQuiz(View view) {

    }

    public void scoreBoard(View view) {

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
