package hx.widget.dialog;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import hx.lib.R;
import hx.view.sv.WheelView;

/**
 * Created by Rose on 3/2/2017.
 */

public class DItemSelect {

    public static void show(Activity act, List<String> data, WheelView.OnWheelViewListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(act, R.style.Dialog_BottomUp);
        View layout = act.getLayoutInflater().inflate(R.layout.d_item_select, null);
        AlertDialog dialog = builder.setView(layout).setCancelable(false).create();
        WheelView _whv_ = (WheelView)layout.findViewById(R.id._whv_);
        _whv_.setItems(data);
        layout.findViewById(R.id._bt_0).setOnClickListener(view -> dialog.dismiss());
        layout.findViewById(R.id._bt_1).setOnClickListener(view -> {
//            listener.onSelected(_whv_.getSeletedIndex() - 1, _whv_.getSeletedItem());
            listener.onSelected(_whv_.getSeletedIndex(), _whv_.getSeletedItem());
            dialog.dismiss();
        });

        Window window = dialog.getWindow();
        if(window != null) {
            window.setGravity(Gravity.BOTTOM); //可设置dialog的位置
            window.getDecorView().setPadding(0, 0, 0, 0); //消除边距
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }

        dialog.show();

    }
}
