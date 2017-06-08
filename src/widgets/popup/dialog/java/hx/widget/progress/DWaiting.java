package hx.widget.progress;

import android.app.Activity;
import android.app.Dialog;
import android.view.KeyEvent;
import hx.lib.R;


/**
 * Created by Rose on 3/2/2017.
 *
 * process waiting handle.
 * like request waiting, data process waiting.
 *
 */

public class DWaiting {

    public static Dialog show(Activity act){
        Dialog dialog = new Dialog(act, R.style.Dialog_Waiting);
        dialog.setContentView(R.layout.d_waiting);
        dialog.setOnKeyListener((dialog1, keyCode, event) -> {
            if(keyCode == KeyEvent.KEYCODE_BACK){
                dialog.dismiss();
            }
            return true;
        });
        dialog.show();
        return dialog;
    }
}
