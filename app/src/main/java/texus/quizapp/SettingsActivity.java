package texus.quizapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import texus.quizapp.BaseAppCompatActivity;
import texus.quizapp.R;

public class SettingsActivity extends BaseAppCompatActivity {

    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
        setUpToolbar();
    }

    public void setUpToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.toolbar_quiz_activity);
            View customView = actionBar.getCustomView();
            if(customView != null) {
                tvTitle = (TextView) customView.findViewById(R.id.tvTitle);
                tvTitle.setText("Settings");
            }
        }
    }

    @Override
    public void initViews() {

    }
}
