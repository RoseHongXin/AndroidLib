package hx.widget.tb;


import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import hx.components.ABase;
import hx.lib.R;

/**
 * Created by rose on 16-8-12.
 */

public class Tb {

    public static <ACT extends ABase> View create(ACT act){
        return create(act, R.mipmap.i_back, "", null);
    }
    public static <ACT extends ABase> View create(ACT act, String title){
        return create(act, R.mipmap.i_back, title, null);
    }
    public static <ACT extends ABase> View create(ACT act, String title, View.OnClickListener listener){
        return create(act, R.mipmap.i_back, title, listener);
    }
    public static <ACT extends ABase> View create(ACT act, @DrawableRes int iconRes, String title, View.OnClickListener listener){
        View _tb = getLayout(act, R.layout.tb_left_iv);
        ImageView _iv_left = (ImageView) _tb.findViewById(R.id._iv_left);
        _iv_left.setImageResource(iconRes);
        _iv_left.setOnClickListener(view -> {
            if(listener == null){
                act.finish();
                return;
            }
            listener.onClick(_iv_left);
        });
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(TextUtils.isEmpty(title) ? "" : title);
        return _tb;
    }

    public static <ACT extends ABase> View create(ACT act, String title, String rightText, View.OnClickListener listener){
        View _tb = getLayout(act, R.layout.tb_right_tv);
        TextView _tv_right = (TextView)_tb.findViewById(R.id._tv_right);
        _tv_right.setOnClickListener(view -> {
            if(listener == null){
                act.finish();
                return;
            }
            listener.onClick(_tv_right);
        });
        _tv_right.setText(rightText);
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(TextUtils.isEmpty(title) ? "" : title);
        return _tb;
    }
    
    public static <ACT extends ABase> View create(ACT act, String title,
                                                  @DrawableRes int leftIconRes, View.OnClickListener leftListener,
                                                  String rightText, View.OnClickListener rightListener){
        View _tb = getLayout(act, R.layout.tb_left_iv_right_tv);
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        ImageView _iv_left = (ImageView) _tb.findViewById(R.id._iv_left);
        _iv_left.setImageResource(leftIconRes);
        _iv_left.setOnClickListener(leftListener);
        TextView tv_right = (TextView)_tb.findViewById(R.id._tv_right);
        tv_right.setText(rightText);
        tv_right.setOnClickListener(rightListener);
        return _tb;
    }
    public static <ACT extends ABase> View create(ACT act, String title,
                                                  @DrawableRes int leftIconRes, View.OnClickListener leftListener,
                                                  @DrawableRes int rightIconRes, View.OnClickListener rightListener){
        View _tb = getLayout(act, R.layout.tb_left_iv_right_iv);
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        ImageView _iv_left = (ImageView) _tb.findViewById(R.id._iv_left);
        _iv_left.setImageResource(leftIconRes);
        _iv_left.setOnClickListener(leftListener);
        ImageView _iv_right = (ImageView)_tb.findViewById(R.id._iv_right);
        _iv_right.setImageResource(rightIconRes);
        _iv_right.setOnClickListener(rightListener);
        return _tb;
    }

    public static <ACT extends ABase> View create(ACT act, String title,
                                                  String leftText, View.OnClickListener leftListener,
                                                  @DrawableRes int rightIconRes, View.OnClickListener rightListener){
        View _tb = getLayout(act, R.layout.tb_left_tv_right_iv);
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        TextView _tv_left = (TextView) _tb.findViewById(R.id._tv_left);
        _tv_left.setText(leftText);
        _tv_left.setOnClickListener(leftListener);
        ImageView _iv_right = (ImageView)_tb.findViewById(R.id._iv_right);
        _iv_right.setImageResource(rightIconRes);
        _iv_right.setOnClickListener(rightListener);
        return _tb;
    }
    public static <ACT extends ABase> View create(ACT act, String title,
                                                  String leftText, View.OnClickListener leftListener,
                                                  String rightText, View.OnClickListener rightListener){
        View _tb = getLayout(act, R.layout.tb_left_tv_right_iv);
        ((TextView)_tb.findViewById(R.id._tv_title)).setText(title);
        TextView _tv_left = (TextView) _tb.findViewById(R.id._tv_left);
        _tv_left.setText(leftText);
        _tv_left.setOnClickListener(leftListener);
        TextView _tv_right = (TextView)_tb.findViewById(R.id._tv_right);
        _tv_right.setOnClickListener(rightListener);
        _tv_right.setText(rightText);
        return _tb;
    }

    private static <ACT extends ABase>  View getLayout(ACT act, @LayoutRes int layoutRes){
        View _tb = ((LayoutInflater)act.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)).inflate(layoutRes, act.sGetLayout(), false);
        return _tb;
    }
}
