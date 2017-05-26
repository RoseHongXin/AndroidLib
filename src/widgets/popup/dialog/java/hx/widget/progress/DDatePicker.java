package hx.widget.progress;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Locale;

import hx.lib.R;

/**
 * Created by Rose on 3/2/2017.
 */

public class DDatePicker {

    public static void show(Activity act, DatePicker.OnDateChangedListener listener ){
        AlertDialog.Builder builder = new AlertDialog.Builder(act, R.style.dialog_item_select);
        View layout = act.getLayoutInflater().inflate(R.layout.d_date_picker, null);
        AlertDialog dialog = builder.setView(layout).create();

        DatePicker _dp = (DatePicker)layout.findViewById(R.id._dp_);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        _dp.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        layout.findViewById(R.id._bt_0).setOnClickListener(view -> dialog.dismiss());
        layout.findViewById(R.id._bt_1).setOnClickListener(view -> {
            if(listener != null) listener.onDateChanged(_dp, _dp.getYear(), _dp.getMonth(), _dp.getDayOfMonth());
            dialog.dismiss();
        });

        DialogHelper.erasePadding(dialog);

        dialog.show();

    }
}
