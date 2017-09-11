package texus.app.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import texus.app.controls.QuestionControl;
import texus.app.model.Question;
import texus.quizapp.R;

/**
 * Created by sandeep on 6/12/2017.
 */

public class WalkThroughAdapter extends PagerAdapter {

    private Context mContext;
    private int mSlideCount = 0;

    public WalkThroughAdapter(Context context, int slideCount) {
        mContext = context;
        mSlideCount = slideCount;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if( position == 0) {
            ViewGroup layout = (ViewGroup) inflater.inflate(
                    R.layout.pager_item_option_tutorial, collection, false);
            collection.addView(layout);
            return layout;
        }

        if( position == 1) {
            ViewGroup layout = (ViewGroup) inflater.inflate(
                    R.layout.pager_item_tutorial_swipe, collection, false);
            collection.addView(layout);
            return layout;
        }


        return null;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mSlideCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}
