package hx.widgets.tb;


import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meloinfo.kaxi.R;

/**
 * Created by rose on 16-8-12.
 */
public class TbUtil2 {

    public static View add(Activity act, @DrawableRes int icon, @StringRes int title, IconClickCallback callback){
        View _tb =  ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_simple1, null);
        TextView _tv = (TextView) _tb.findViewById(R.id._tv);
        _tv.setCompoundDrawablesWithIntrinsicBounds(icon, -1, -1, -1);
        _tv.setOnClickListener(view -> {
            if(callback != null) callback.onClicked();
        });
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        return _tb;
    }
    public static View add(Activity act, @StringRes int title, @DrawableRes int iconLeft, IconClickCallback lcb, @DrawableRes int iconRight, IconClickCallback rcb){
        View _tb = ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_simple1, null);
        TextView _tv = (TextView) _tb.findViewById(R.id._tv);
        _tv.setCompoundDrawablesWithIntrinsicBounds(iconLeft, -1, -1, -1);
        _tv.setOnClickListener(view -> {
            if(lcb != null) lcb.onClicked();
        });
        TextView _tv_right = (TextView) _tb.findViewById(R.id._tv_right);
        _tv_right.setCompoundDrawablesWithIntrinsicBounds(-1, -1, iconRight, -1);
        _tv_right.setOnClickListener(view -> {
            if(rcb != null) rcb.onClicked();
        });
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        return _tb;
    }
    
    public static View add(Activity act, @StringRes int title){
        View _tb = ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_simple1, null);
        TextView _tv = (TextView) _tb.findViewById(R.id._tv);
        _tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.i_back, -1, -1, -1);
        _tv.setOnClickListener(view -> act.finish());
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        return _tb;
    }
    public static View add(Activity act, @StringRes int left, IconClickCallback lcb, @StringRes int right, IconClickCallback rcb){
        View _tb = ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tb_right_tv, null);
        TextView _tv = (TextView) _tb.findViewById(R.id._tv);
        _tv.setText(left);
        _tv.setOnClickListener(view -> {
            if(lcb != null) lcb.onClicked();
        });
        TextView _tv_right = (TextView) _tb.findViewById(R.id._tv_right);
        _tv_right.setText(right);
        _tv_right.setOnClickListener(view -> {
            if(rcb != null) rcb.onClicked();
        });
        return _tb;
    }

    public interface IconClickCallback{
        void onClicked();
    }

}
