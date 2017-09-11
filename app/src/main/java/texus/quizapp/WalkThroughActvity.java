package texus.quizapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sdsmdg.harjot.rotatingtext.RotatingTextWrapper;
import com.sdsmdg.harjot.rotatingtext.models.Rotatable;
import com.viewpagerindicator.CirclePageIndicator;

import texus.app.adapter.WalkThroughAdapter;
import texus.app.preferance.SavedPreferance;
import texus.app.sync.SyncData;
import texus.app.utils.AppMessages;

public class WalkThroughActvity extends BaseAppCompatActivity {

    private ViewPager viewpager;
    private CirclePageIndicator indicator;
    private int postion = 0;
    private ImageView imLeft, imRight;
    private TextView tvLeft, tvRight;
    int totalSlides = 2;

//    https://stackoverflow.com/questions/9884202/custom-circle-button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);
        initViews();
        SavedPreferance.setHelpViewed(this, true);
    }


    @Override
    public void initViews() {
        viewpager   = (ViewPager) findViewById(R.id.viewpager);
        imLeft   = (ImageView) findViewById(R.id.imLeft);
        imRight   = (ImageView) findViewById(R.id.imRight);
        tvLeft   = (TextView) findViewById(R.id.tvLeft);
        tvRight   = (TextView) findViewById(R.id.tvRight);
        indicator   = (CirclePageIndicator) findViewById(R.id.indicator);

        leftVisible(View.INVISIBLE);

        if(totalSlides > 0) {
            rightVisible(View.VISIBLE);
        }

        WalkThroughAdapter adapter = new WalkThroughAdapter( this, totalSlides);

        viewpager.setAdapter(adapter);
//
        indicator.setFillColor(Color.parseColor("#FFFFFF"));
        indicator.setStrokeColor(Color.parseColor("#FFFFFF"));
        indicator.setStrokeWidth(getResources()
                .getDimensionPixelSize(R.dimen.pager_indecator_width));
        indicator.setRadius(indicator.getRadius() * 3f);
        indicator.setViewPager(viewpager);

        viewpager.setCurrentItem(postion);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if( position == 0) {
                    leftVisible( View.INVISIBLE);
                } else {
                    leftVisible( View.VISIBLE);
                }

                if( position == totalSlides-1) {
                    rightVisible( View.INVISIBLE);
                } else {
                    rightVisible( View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    public void leftVisible(int value) {
        imLeft.setVisibility(value);
        tvLeft.setVisibility(value);
    }

    public void rightVisible(int value) {
        imRight.setVisibility(value);
        tvRight.setVisibility(value);
    }

    public void gotoLeft( View view) {
        try {
            viewpager.setCurrentItem(getItem(-1), true);
        } catch ( IndexOutOfBoundsException e) {
        }

    }

    public void gotoRight( View view) {
        try {
            viewpager.setCurrentItem(getItem(+1), true);
        } catch ( IndexOutOfBoundsException e) {
        }
    }

    private int getItem(int posOffset) {
        int resultPos = viewpager.getCurrentItem() + posOffset;
        if( resultPos > 0  && resultPos < totalSlides) {
            return resultPos;
        }
        return 0;
    }

    public void okIgotIt( View view) {
        SavedPreferance.setHelpViewed(this, true);
        finish();
    }

}
