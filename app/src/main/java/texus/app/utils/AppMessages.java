package texus.app.utils;

import android.os.Handler;
import android.widget.Toast;

import texus.quizapp.App;


/**
 * Global message display class
 * Created by SW on 2015-07-17.
 */
public class AppMessages {

    public static final String ERROR_MESSAGE1 = "Something went wrong! Please try later";

    private static final int MSG_SHOW_TOAST = 1;

    private static Handler messageHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == MSG_SHOW_TOAST) {
                String message = (String) msg.obj;
                Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show();
            }
        }
    };

    public static void displayMessage(String message) {

        android.os.Message msg = new android.os.Message();
        msg.what = MSG_SHOW_TOAST;
        msg.obj = message;
        messageHandler.sendMessage(msg);
    }

    public static void showErrorMessage() {
        AppMessages.displayMessage(ERROR_MESSAGE1);
    }
}
