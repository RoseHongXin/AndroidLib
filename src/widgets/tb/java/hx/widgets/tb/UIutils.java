package hx.widgets.tb;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import hx.lib.R;


/**
 * Created by rose on 16-8-12.
 */
public class UIutils {

    public static Toolbar addToolBar(Activity act, @DrawableRes int icon, String title, IconClickCallback callback){
        Toolbar _tb =  (Toolbar) ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_simple, null);
        ImageView iv_icon = (ImageView) _tb.findViewById(R.id._iv_icon);
        iv_icon.setImageResource(icon);
        iv_icon.setOnClickListener(view -> {
            if(callback != null) callback.onClicked();
        });
        _tb.setTitle("");
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        return _tb;
    }
    public static Toolbar addToolBar(Activity act, String title, @DrawableRes int iconLeft, IconClickCallback lcb, @DrawableRes int iconRight, IconClickCallback rcb){
        Toolbar _tb =  (Toolbar) ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_right_iv, null);
        ImageView iv_icon = (ImageView) _tb.findViewById(R.id._iv_icon);
        iv_icon.setImageResource(iconLeft);
        iv_icon.setOnClickListener(view -> {
            if(lcb != null) lcb.onClicked();
        });
        ImageView iv_right = (ImageView)_tb.findViewById(R.id._iv_right);
        iv_right.setImageResource(iconRight);
        iv_right.setOnClickListener(view -> {
            if(rcb != null) rcb.onClicked();
        });
        _tb.setTitle("");
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        return _tb;
    }
    
    public static Toolbar addToolBar(Activity act, String title){
        Toolbar _tb =  (Toolbar) ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_simple, null);
        ImageView iv_icon = (ImageView) _tb.findViewById(R.id._iv_icon);
        iv_icon.setImageResource(R.mipmap.i_edit_back);
        iv_icon.setOnClickListener(view -> act.finish());
        _tb.setTitle("");
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        return _tb;
    }
    public static Toolbar addToolBar(Activity act, String title, String right, IconClickCallback callback){
        Toolbar _tb =  (Toolbar) ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_right_tv, null);
        ImageView iv_icon = (ImageView) _tb.findViewById(R.id._iv_icon);
        iv_icon.setImageResource(R.mipmap.i_edit_back);
        iv_icon.setOnClickListener(view -> act.finish());
        _tb.setTitle("");
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        TextView tv_right = (TextView)_tb.findViewById(R.id._tv_right);
        tv_right.setText(right);
        tv_right.setOnClickListener(view -> {
            if(callback != null) callback.onClicked();
        });
        return _tb;
    }

    public interface IconClickCallback{
        void onClicked();
    }

    public static DisplayImageOptions getImageLoaderHeaderConfig(){
        return new DisplayImageOptions.Builder()
                .showImageOnFail(R.mipmap.img_header_default)
                .showImageForEmptyUri(R.mipmap.img_header_default)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

}
