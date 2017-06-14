package texus.app.controls;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import texus.app.database.DatabasesHelper;
import texus.app.model.Question;
import texus.app.task.SaveAnswerTask;
import texus.quizapp.R;

/**
 * Created by sandeep on 6/12/2017.
 */

public class QuestionControl extends RelativeLayout {


    Context mContext ;
    LinearLayout llHolder;
    TextView tvQuestion;
    Question question;
    ArrayList<OptionControl> options;


    public QuestionControl(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public QuestionControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public QuestionControl(Context context) {
        super(context);
        init(context);
    }


    private void init(Context context) {

        this.mContext = context;
        options = new ArrayList<OptionControl>();
        LayoutInflater inflater = (LayoutInflater)  getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View child =  inflater.inflate(R.layout.item_question,this);

        tvQuestion = (TextView) child.findViewById(R.id.tvQuestion);
        llHolder = (LinearLayout) child.findViewById(R.id.llHolder);

    }

    public void loadAndSetQuestion(Question question) {
        LoadQuestionTask task = new LoadQuestionTask(mContext, question.question_id);
        task.execute();
    }

    public void setQuestion(Question question) {
        if( question == null) return;
        this.question = question;
        tvQuestion.setText(question.question);

        if(question.opA.length() != 0) {
            llHolder.addView(getOption(question.opA, OptionControl.OPTION_A));
        }
        if(question.opB.length() != 0) {
            llHolder.addView(getOption(question.opB, OptionControl.OPTION_B));
        }
        if(question.opC.length() != 0) {
            llHolder.addView(getOption(question.opC, OptionControl.OPTION_C));
        }
        if(question.opD.length() != 0) {
            llHolder.addView(getOption(question.opD, OptionControl.OPTION_D));
        }

        if(!question.userAnswer.equals(Question.UNANSWER_STRING)) {
            setAnswer(question.userAnswer);
        }
    }

    public OptionControl getOption( String answer, String option) {
        final OptionControl optionControl = new OptionControl(mContext);
        optionControl.setAnswer(answer);
        optionControl.setOption(option);
        optionControl.setOnOptionClick(new OptionControl.OnOptionClick() {
            @Override
            public void OnOptionClick(String option) {
                question.userAnswer = option;
                SaveAnswerTask task = new SaveAnswerTask(question, mContext);
                task.execute();
                setAnswer(option);
            }
        });
        options.add(optionControl);
        return optionControl;
    }

    public void setAnswer(String option) {

        for( OptionControl ctrl : options) {
            ctrl.clearAnswers();
        }

        for( OptionControl ctrl : options) {

            if( ctrl.option.equals(option) && !(question.answerKey.equals(option))) {
                ctrl.setAnswerWrong();
            } else if( ctrl.option.equals(question.answerKey)) {
                ctrl.setAnswerRight();
            } else {
                ctrl.clearAnswers();
            }
        }

    }

    public class LoadQuestionTask  extends AsyncTask<Void, Void, Void> {

        Context context;
        Question question;
        int questionID;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        public LoadQuestionTask(Context context, int questionID) {
            this.questionID = questionID;
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... params) {
            DatabasesHelper dbHelper = new DatabasesHelper(context);
            question = Question.getAnObjectQuestionID(dbHelper, questionID);
            dbHelper.close();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(question != null) {
                setQuestion(question);
            }
        }

    }


}