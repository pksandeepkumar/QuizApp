package texus.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import texus.quizapp.R;


/**
 * Created by sandeep on 21/2/17.
 *
 * https://github.com/81813780/AVLoadingIndicatorView
 */
public class ProgressDialogLarge extends Dialog {

    public ProgressDialogLarge(Context context) {
        super(context);
        init();
    }

    private void init() {

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));

        setContentView(R.layout.dialog_progress_dialog_large);

        setCanceledOnTouchOutside(false);

        setCancelable(false);

    }

    public void hideDialog() {
        if(isShowing()) {
            hide();
        }
    }

}
