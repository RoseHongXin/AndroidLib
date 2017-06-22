package hx.toolkit;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import hx.lib.R;


public class OptHelper {
    /**
     * 防止多次点击
     */
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
    public static void fastDoubleClick(final View view) {
        view.setClickable(false);
        view.postDelayed(() -> view.setClickable(true), 500);
    }

    public static void finishPageWithConfirm(Activity act, DialogInterface.OnClickListener cb) {
        new AlertDialog.Builder(act)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    cb.onClick(dialogInterface, i);
                })
                .setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> dialogInterface.dismiss())
                .setMessage(R.string.confirm_finish_current_page)
                .create()
                .show();
    }
}