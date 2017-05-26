package hx.widget.progress;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import hx.lib.R;

/**
 * Created by Rose on 3/2/2017.
 */

public class DConfirmAndCancel {

    public static AlertDialog show(Activity act, String content, View.OnClickListener cb){
        return show(act, "", content, cb);
    }

    public static  AlertDialog show(Activity act, String title, String content, View.OnClickListener cb){
        return show(act, title, content, act.getString(R.string.cancel), null, act.getString(R.string.confirm), cb);
    }

    public static  AlertDialog show(Activity act, String title, String content, View.OnClickListener cb0, View.OnClickListener cb1){
        return show(act, title, content, act.getString(R.string.cancel), cb0, act.getString(R.string.confirm), cb1);
    }

    public static AlertDialog show(Activity act, String title, String content, String bt0, View.OnClickListener cb0, String bt1, View.OnClickListener cb1){
        AlertDialog.Builder builder = new AlertDialog.Builder(act, R.style.d_with_bt);
        View layout  = act.getLayoutInflater().inflate(R.layout.d_confirm_and_cancel, null);
        AlertDialog dialog = builder.setView(layout).setCancelable(false).create();

        TextView _tv_title = (TextView)layout.findViewById(R.id._tv_title);
        if(TextUtils.isEmpty(title)) _tv_title.setVisibility(View.GONE);
        else _tv_title.setText(title);

        ((TextView)layout.findViewById(R.id._tv_content)).setText(content);

        TextView _bt_0 = (TextView) layout.findViewById(R.id._bt_0);
        _bt_0.setText(bt0);
        _bt_0.setOnClickListener(view -> {
            dialog.dismiss();
            if(cb0 != null) cb0.onClick(_bt_0);
        });
        TextView _bt_1 = (TextView)layout.findViewById(R.id._bt_1);
        _bt_1.setText(bt1);
        _bt_1.setOnClickListener(view -> {
            dialog.dismiss();
            if(cb1 != null) cb1.onClick(_bt_1);
        });

        DialogHelper.padding(dialog, 24, 24);

        dialog.show();
        return dialog;
    }

}
