package hx.widgets.dialog;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import hx.lib.R;

/**
 * Created by Rose on 3/2/2017.
 */

public class DConfirmAndCancel {

    private static DConfirmAndCancel mInstance;

    public static DConfirmAndCancel create(){
        if(mInstance == null) mInstance = new DConfirmAndCancel();
        return mInstance;
    }

    public AlertDialog show(Activity act, String title, String content){
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        View layout  = act.getLayoutInflater().inflate(R.layout.d_base_0, null);
        AlertDialog dialog = builder.setView(layout).setCancelable(false).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ((TextView)layout.findViewById(R.id._tv_title)).setText(title);
        ((TextView)layout.findViewById(R.id._tv_content)).setText(content);
        layout.findViewById(R.id._bt).setOnClickListener(view -> dialog.dismiss());
        dialog.show();
        return dialog;
    }

    public AlertDialog show(Activity act, String title, String content, Callback cb0){
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        View layout  = act.getLayoutInflater().inflate(R.layout.d_base_0, null);
        AlertDialog dialog = builder.setView(layout).setCancelable(false).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ((TextView)layout.findViewById(R.id._tv_title)).setText(title);
        ((TextView)layout.findViewById(R.id._tv_content)).setText(content);
        layout.findViewById(R.id._bt).setOnClickListener(view -> {
            dialog.dismiss();
            if(cb0 != null) cb0.onClick();
        });
        dialog.show();
        return dialog;
    }

    public AlertDialog show(Activity act, String title, String content, String bt0, Callback cb0){
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        View layout  = act.getLayoutInflater().inflate(R.layout.d_base_0, null);
        AlertDialog dialog = builder.setView(layout).setCancelable(false).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ((TextView)layout.findViewById(R.id._tv_title)).setText(title);
        ((TextView)layout.findViewById(R.id._tv_content)).setText(content);
        TextView _bt = (TextView) layout.findViewById(R.id._bt);
        _bt.setText(bt0);
        _bt.setOnClickListener(view -> {
            dialog.dismiss();
            if(cb0 != null) cb0.onClick();
        });
        dialog.show();
        return dialog;
    }

    public AlertDialog show(Activity act, String title, String content, Callback cb0, Callback cb1){
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        View layout  = act.getLayoutInflater().inflate(R.layout.d_base_1, null);
        AlertDialog dialog = builder.setView(layout).setCancelable(false).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ((TextView)layout.findViewById(R.id._tv_title)).setText(title);
        ((TextView)layout.findViewById(R.id._tv_content)).setText(content);
        layout.findViewById(R.id._bt_0).setOnClickListener(view -> {
            dialog.dismiss();
            if(cb0 != null) cb0.onClick();
        });
        layout.findViewById(R.id._bt_1).setOnClickListener(view -> {
            dialog.dismiss();
            if(cb1 != null) cb1.onClick();
        });
        dialog.show();
        return dialog;
    }
    public AlertDialog show(Activity act, String title, String content, String bt0, Callback cb0, String bt1, Callback cb1){
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        View layout  = act.getLayoutInflater().inflate(R.layout.d_base_1, null);
        AlertDialog dialog = builder.setView(layout).setCancelable(false).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ((TextView)layout.findViewById(R.id._tv_title)).setText(title);
        ((TextView)layout.findViewById(R.id._tv_content)).setText(content);
        TextView _bt_0 = (TextView) layout.findViewById(R.id._bt_0);
        _bt_0.setText(bt0);
        _bt_0.setOnClickListener(view -> {
            dialog.dismiss();
            if(cb0 != null) cb0.onClick();
        });
        TextView _bt_1 = (TextView)layout.findViewById(R.id._bt_1);
        _bt_1.setText(bt1);
        _bt_1.setOnClickListener(view -> {
            dialog.dismiss();
            if(cb1 != null) cb1.onClick();
        });
        dialog.show();
        return dialog;
    }


    public interface Callback{
        void onClick();
    }

}
