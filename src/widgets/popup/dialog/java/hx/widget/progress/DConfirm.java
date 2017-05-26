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

public class DConfirm {

    public AlertDialog show(Activity act, String title, String content){
        return show(act, title, content, act.getString(R.string.confirm), null);
    }

    public AlertDialog show(Activity act, String title, String content, View.OnClickListener cb){
        return show(act, title, content, act.getString(R.string.confirm), cb);
    }

    public AlertDialog show(Activity act, String title, String content, String bt, View.OnClickListener cb){
        AlertDialog.Builder builder = new AlertDialog.Builder(act, R.style.d_with_bt);
        View layout  = act.getLayoutInflater().inflate(R.layout.d_confirm, null);
        AlertDialog dialog = builder.setView(layout).setCancelable(false).create();
        TextView _tv_title = (TextView)layout.findViewById(R.id._tv_title);
        if(TextUtils.isEmpty(title)) _tv_title.setVisibility(View.GONE);
        else _tv_title.setText(title);
        ((TextView)layout.findViewById(R.id._tv_content)).setText(content);
        TextView _bt = (TextView) layout.findViewById(R.id._bt);
        _bt.setText(bt);
        _bt.setOnClickListener(view -> {
            dialog.dismiss();
            if(cb != null) cb.onClick(_bt);
        });
        dialog.show();
        return dialog;
    }

}
