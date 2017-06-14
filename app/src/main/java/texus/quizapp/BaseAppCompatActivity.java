package texus.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


/**
 * Created by sandeep on 2/13/2017.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    protected boolean enableBackNavigation = true;
    private Toolbar toolbar;


    public abstract void initViews();


    protected void setActivityTitle(String title) {
        if( getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    protected void setBackButtonOnToolbar() {
        if( getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (enableBackNavigation && toolbar != null ) {
//            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    public void startPage( Class classObject) {
        Intent intent = new Intent(this, classObject);
        startActivity(intent);
    }






}
