package hx.widgets.tb;


import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meloinfo.kaxi.R;

import hx.components.base.ABase;

/**
 * Created by rose on 16-8-12.
 */
public class TbUtil {

    public static <ACT extends ABase> View add(ACT act, @DrawableRes int icon, @StringRes int title, IconClickCallback callback){
        View _tb =  ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_simple, act._getLayout(), false);
        ImageView _iv_icon = (ImageView) _tb.findViewById(R.id._iv_icon);
        _iv_icon.setImageResource(icon);
        _iv_icon.setOnClickListener(view -> {
            if(callback != null) callback.onClicked();
        });
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        return _tb;
    }
    public static <ACT extends ABase> View add(ACT act, @StringRes int title, @DrawableRes int iconLeft, IconClickCallback lcb, @DrawableRes int iconRight, IconClickCallback rcb){
        View _tb = ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_right_iv, act._getLayout(), false);
        ImageView _iv_icon = (ImageView) _tb.findViewById(R.id._iv_icon);
        _iv_icon.setImageResource(iconLeft);
        _iv_icon.setOnClickListener(view -> {
            if(lcb != null) lcb.onClicked();
        });
        ImageView iv_right = (ImageView)_tb.findViewById(R.id._iv_right);
        iv_right.setImageResource(iconRight);
        iv_right.setOnClickListener(view -> {
            if(rcb != null) rcb.onClicked();
        });
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        return _tb;
    }

    public static <ACT extends ABase> View add(ACT act){
        View _tb = ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_simple, act._getLayout(), false);
        ImageView _iv_icon = (ImageView) _tb.findViewById(R.id._iv_icon);
        _iv_icon.setImageResource(R.mipmap.i_back);
        _iv_icon.setOnClickListener(view -> act.finish());
        return _tb;
    }
    public static <ACT extends ABase> View add(ACT act, @StringRes int title){
        return add(act, title, null);
    }
    public static <ACT extends ABase> View add(ACT act, @StringRes int title, IconClickCallback cb){
        View _tb = ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_simple, act._getLayout(), false);
        ImageView _iv_icon = (ImageView) _tb.findViewById(R.id._iv_icon);
        _iv_icon.setImageResource(R.mipmap.i_back);
        _iv_icon.setOnClickListener(view -> {
            if(cb == null){
                act.finish();
                return;
            }
            cb.onClicked();
        });
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        return _tb;
    }
    public static <ACT extends ABase> View add(ACT act, @StringRes int title, String right, IconClickCallback callback){
        View _tb = ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_right_tv, act._getLayout(), false);
        ImageView _iv_icon = (ImageView) _tb.findViewById(R.id._iv_icon);
        _iv_icon.setImageResource(R.mipmap.i_back);
        _iv_icon.setOnClickListener(view -> act.finish());
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        TextView tv_right = (TextView)_tb.findViewById(R.id._tv_right);
        tv_right.setText(right);
        tv_right.setOnClickListener(view -> {
            if(callback != null) callback.onClicked();
        });
        return _tb;
    }
    public static <ACT extends ABase> View add(ACT act, String title, String right, IconClickCallback callback){
        View _tb = ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_right_tv, act._getLayout(), false);
        ImageView _iv_icon = (ImageView) _tb.findViewById(R.id._iv_icon);
        _iv_icon.setImageResource(R.mipmap.i_back);
        _iv_icon.setOnClickListener(view -> act.finish());
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

}
