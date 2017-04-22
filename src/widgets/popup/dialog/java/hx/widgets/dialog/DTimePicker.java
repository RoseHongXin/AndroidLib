package hx.widgets.dialog;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TimePicker;

import com.meloinfo.kaxi.R;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Rose on 3/2/2017.
 */

public class DTimePicker {

    public static void show(Activity act, TimePicker.OnTimeChangedListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(act, R.style.dialog_item_select);
        View layout = act.getLayoutInflater().inflate(R.layout.d_time_picker, null);
        AlertDialog dialog = builder.setView(layout).create();

        TimePicker _tp_ = (TimePicker)layout.findViewById(R.id._tp_);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
       /* _dp.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), (view, year, monthOfYear, dayOfMonth) -> {

        });*/
//        _tp_.setHour(calendar.get(Calendar.HOUR_OF_DAY));
//        _tp_.setMinute(calendar.get(Calendar.MINUTE));
        _tp_.setIs24HourView(true);
        _tp_.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        _tp_.setCurrentMinute(calendar.get(Calendar.MINUTE));

        layout.findViewById(R.id._bt_0).setOnClickListener(view -> dialog.dismiss());
        layout.findViewById(R.id._bt_1).setOnClickListener(view -> {
            if(listener != null) listener.onTimeChanged(_tp_, _tp_.getCurrentHour(), _tp_.getCurrentMinute());
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
