package texus.app.controls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by sandeep on 6/13/2017.
 */

public class TextViewWithFont extends AppCompatTextView {

    static Typeface typeface = null;
    private static final String TAG = "TextView";

    public TextViewWithFont(Context context) {
        super(context);
    }

    public TextViewWithFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public TextViewWithFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        loadFont(ctx);
        setTypeface(typeface);
//        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.TextViewPlus);
//        String customFont = a.getString(R.styleable.TextViewPlus_customFont);
//        setCustomFont(ctx, customFont);
//        a.recycle();
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }

    public static void loadFont(Context ctx) {
        if(typeface != null) return ;
        try {
//            typeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/Montserrat-Light.ttf");
        } catch (Exception e) {
        }
    }

}
