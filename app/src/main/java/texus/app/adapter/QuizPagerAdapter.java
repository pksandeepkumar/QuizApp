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

public class QuizPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<Question> questions;

    public QuizPagerAdapter(Context context, ArrayList<Question> questions) {
        mContext = context;
        this.questions = questions;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        Question modelObject = questions.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(
                R.layout.pager_item_question, collection, false);
        collection.addView(layout);

        QuestionControl questionControl = (QuestionControl) layout.findViewById(R.id.ctrQuestion);
        questionControl.loadAndSetQuestion(modelObject);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        if(questions == null) return 0;
        return questions.size();
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
