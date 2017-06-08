package hx.widget.progress;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.DimenRes;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.TextView;

import hx.lib.R;

/**
 * Created by Rose on 3/2/2017.
 */

public class DConfirm {

    public static AlertDialog show(Activity act, String title, String content){
        return show(act, title, content, act.getString(R.string.confirm), null);
    }

    public static AlertDialog show(Activity act, String title, String content, View.OnClickListener clickListener){
        return show(act, title, content, act.getString(R.string.confirm), clickListener);
    }

    public static AlertDialog show(Activity act, String title, String content, String bt, View.OnClickListener clickListener){
        return show(act,
                (_tv_title, _tv_content, _bt_) -> {
                    if(TextUtils.isEmpty(title)) _tv_title.setVisibility(View.GONE);
                    else _tv_title.setText(title);
                    _tv_content.setText(content);
                 _bt_.setText(bt);
                }
                ,clickListener);
    }

    public static AlertDialog show(Activity act, Callback cb, View.OnClickListener clickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(act, R.style.d_with_bt);
        View layout  = act.getLayoutInflater().inflate(R.layout.d_confirm, null);
        AlertDialog dialog = builder.setView(layout).setCancelable(false).create();
        TextView _bt_ = (TextView) layout.findViewById(R.id._bt);

        cb.onViewsSetup((TextView) layout.findViewById(R.id._tv_title), (TextView) layout.findViewById(R.id._tv_content), _bt_);

        _bt_.setOnClickListener(view -> {
            dialog.dismiss();
            if(clickListener != null) clickListener.onClick(_bt_);
        });
//        DialogHelper.erasePadding(dialog, Gravity.CENTER);
        DialogHelper.padding(dialog, 24, 24);
        dialog.show();
        return dialog;
    }


    public interface Callback{
        void onViewsSetup(TextView _tv_title, TextView _tv_content, TextView _bt_);
    }

}
