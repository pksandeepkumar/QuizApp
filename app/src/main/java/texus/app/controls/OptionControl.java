package texus.app.controls;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import texus.quizapp.R;

/**
 * Created by sandeep on 6/12/2017.
 */

public class OptionControl  extends RelativeLayout {

    public static final String OPTION_A = "A";
    public static final String OPTION_B = "B";
    public static final String OPTION_C = "C";
    public static final String OPTION_D = "D";

    Button btnOption;
    TextView tvOptionText;
    FloatingActionButton fabIndicatorRight, fabIndicatorWrong;
    private Animation rotate_forward;
    Context mContext ;
    String option;
    OnOptionClick onOptionClick;

    public interface OnOptionClick {
        public void OnOptionClick(String option);
    }

    public void setOnOptionClick( OnOptionClick onOptionClick) {
        this.onOptionClick = onOptionClick;
    }

    public OptionControl(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public OptionControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OptionControl(Context context) {
        super(context);
        init(context);
    }

    public void setOption( String option) {
        this.option = option;
        btnOption.setText(option.toUpperCase());
    }

    public void setAnswer( String answer) {
        tvOptionText.setText(answer);
    }




    private void init(Context context) {

        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater)  getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View child =  inflater.inflate(R.layout.item_option,this);

        btnOption = (Button) child.findViewById(R.id.btnOption);
        tvOptionText = (TextView) child.findViewById(R.id.tvOptionText);
        fabIndicatorRight = (FloatingActionButton) child.findViewById(R.id.fabIndicatorRight);
        fabIndicatorWrong = (FloatingActionButton) child.findViewById(R.id.fabIndicatorWrong);

        rotate_forward = AnimationUtils.loadAnimation(mContext,R.anim.rotate_forward);

        btnOption.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onOptionClick != null) {
                    onOptionClick.OnOptionClick(option.toUpperCase());
                }

            }
        });

    }

    public void setAnswerWrong() {
        fabIndicatorWrong.show();
        fabIndicatorWrong.startAnimation(rotate_forward);
    }

    public void setAnswerRight() {
        fabIndicatorRight.show();
        fabIndicatorRight.startAnimation(rotate_forward);
    }

    public void clearAnswers() {
        fabIndicatorRight.hide();
        fabIndicatorWrong.hide();
    }




}