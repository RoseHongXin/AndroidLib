package hx.toolkit;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;


public class ClickUtils {
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
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setClickable(true);
            }
        }, 500);
    }

    public static void returnWithConfirm(Activity act, Callback callback) {
        /*AlertUiDialog dialog = AlertUiDialog.showDialog(mAct, true, "", "确定离开当前页面...？\n离开当前页面会丢失当前填写的数据！", mAct.getString(android.R.string.cancel), mAct.getString(android.R.string.ok));
        //dialog.getWindow().setIcon();
        dialog.setListener(position -> {
            if (position == 1) {
                callback.onOk();
            }
        });*/
        new AlertDialog.Builder(act)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> callback.onOk())
                .setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> {})
                .setMessage("确定离开当前页面")
                .create()
                .show();
    }

    public interface Callback{
        void onOk();
    }
}