package texus.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import texus.quizapp.R;


/**
 * Created by sandeep on 21/2/17.
 *
 */
public class ProgressDialogLarge2 extends Dialog {

    public ProgressDialogLarge2(Context context) {
        super(context);
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_progress_dialog_large2);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public void hideDialog() {
        if(isShowing()) {
            hide();
        }
    }

}
