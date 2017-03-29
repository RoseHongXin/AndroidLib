package hx.widgets.dialog;

import android.app.Activity;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import hx.global.R;

/**
 * Created by rose on 16-8-1.
 */

public class DImgPicker {

    public static void show(Activity act, ItemClickCallback callback){
        DialogPlus dialog = DialogPlus.newDialog(act)
                //.setInAnimation(com.orhanobut.dialogplus.R.anim.slide_in_bottom)
                //.setOutAnimation(com.orhanobut.dialogplus.R.anim.slide_out_bottom)
                .setContentHolder(new ViewHolder(R.layout.d_img_picker))
                .setOnClickListener((dialogPlus, view) -> {
                    int id = view.getId();
                    if(id == R.id._v_camera && callback != null) {
                        callback.onCamera();
                        dialogPlus.dismiss();
                    } else if(id == R.id._v_gallery && callback != null) {
                        callback.onGallery();
                        dialogPlus.dismiss();
                    } else if(id == R.id._v_cancel) dialogPlus.dismiss();
                })
                .setContentBackgroundResource(android.R.color.transparent)
                .setMargin(24, 0, 24, 24)
                .create();
        dialog.show();
    }

    public interface ItemClickCallback{
        void onCamera();
        void onGallery();
    }
}
