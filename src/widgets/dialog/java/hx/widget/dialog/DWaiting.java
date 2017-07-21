package hx.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import hx.lib.R;


/**
 * Created by Rose on 3/2/2017.
 *
 * process waiting handle.
 * like request waiting, data process waiting.
 *
 */

public class DWaiting {

    public static Dialog create(Activity act){
        return create(act, null);
    }
    public static Dialog create(Activity act, String hint){
        return create(act, hint, true);
    }

    public static Dialog force(Activity act, String hint){
        return create(act, hint, false);
    }
    public static Dialog force(Activity act){
        return create(act, null, false);
    }
    public static Dialog create(Activity act, String hint, boolean cancelable){
        Dialog dialog = new Dialog(act, R.style.Dialog_Waiting);
        View layout = dialog.getLayoutInflater().inflate(R.layout.d_waiting, null);
        dialog.setContentView(layout);
        dialog.setCancelable(cancelable);
        dialog.setOnKeyListener((dialog1, keyCode, event) -> {
            if(keyCode == KeyEvent.KEYCODE_BACK){
                dialog.dismiss();
            }
            return true;
        });
        if(!TextUtils.isEmpty(hint)) {
            TextView _tv_hint = (TextView) layout.findViewById(R.id._tv_hint);
            _tv_hint.setVisibility(View.VISIBLE);
            _tv_hint.setText(hint);
        }
//        act.getWindow().getDecorView().post(dialog::create);
//        dialog.show();
        return dialog;
    }

    public static Dialog show(Activity act){
        Dialog dialog = create(act);
        dialog.show();
        return dialog;
    }
    public static Dialog show(Activity act, String hint){
        Dialog dialog = create(act, hint);
        dialog.show();
        return dialog;
    }
    public static Dialog showForce(Activity act){
        Dialog dialog = force(act);
        dialog.show();
        return dialog;
    }
    public static Dialog showForce(Activity act, String hint){
        Dialog dialog = force(act, hint);
        dialog.show();
        return dialog;
    }

}
