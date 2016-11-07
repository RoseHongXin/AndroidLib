package hx.ui.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hx.global.R;

/**
 * Created by rose on 16-8-11.
 */

public class LineItem extends RelativeLayout {

    @IdRes
    final private int ID_ICON = 1;
    @IdRes
    final private int ID_TITLE = 2;

    final private int NO_ID = 0;

    @ColorRes
    final private int NO_COLOR_RES = 0;
    @DrawableRes
    final private int NO_DRAWABLE_RES = 0;

    final private int DEFAULT_INTERVALGAP = 12;
    final private int DEFAULT_PADDINGLEFT = 12;

    @DrawableRes
    int iconRes;
    String title;
    int intervalGap;
    int paddingLeft;
    //@ColorRes
    ColorStateList textSelectorRes;
    @DrawableRes
    int itemSelectorRes;
    @ColorRes
    int textColorRes;
    @DimenRes
    int textSizeRes;
    float textSize;

    public LineItem(Context context) {
        super(context);
        init(context, null);
    }

    public LineItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LineItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context ctx, AttributeSet attrs){
        TypedArray ta = ctx.obtainStyledAttributes(attrs, R.styleable.LineItem);

        this.iconRes = ta.getResourceId(R.styleable.LineItem_iconDrawable, NO_ID);

        int titleRes = ta.getResourceId(R.styleable.LineItem_titleText, NO_ID);
        if(titleRes == NO_ID) this.title = ta.getString(R.styleable.LineItem_titleText);
        else this.title = getResources().getString(titleRes);

        this.intervalGap = (int)ta.getDimension(R.styleable.LineItem_intervalGap, DEFAULT_INTERVALGAP);
        this.paddingLeft = (int)ta.getDimension(R.styleable.LineItem_paddingLeft, DEFAULT_PADDINGLEFT);

        this.textSelectorRes = ta.getColorStateList(R.styleable.LineItem_textSelector);
        this.itemSelectorRes = ta.getResourceId(R.styleable.LineItem_itemSelector, NO_DRAWABLE_RES);

        this.textColorRes = ta.getResourceId(R.styleable.LineItem_textColor, -1);
        //if(textColorRes == -1) this.textColorRes = ta.getColor(R.styleable.LineItem_textColor, -1);

//        this.textSizeRes = ta.getResourceId(R.styleable.LineItem_textSize, -1);

        this.textSize = ta.getDimensionPixelSize(R.styleable.LineItem_textSize, -1);

        ta.recycle();

        if(itemSelectorRes != NO_DRAWABLE_RES) setBackgroundResource(itemSelectorRes);
        setPadding(paddingLeft, 0, 0, 0);
        if(iconRes != NO_ID) addIcon(ctx);
        if(!TextUtils.isEmpty(title)) addTitle(ctx);
    }

    private void addIcon(Context ctx){
        ImageView iv = new ImageView(ctx);
        iv.setImageResource(iconRes);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        iv.setId(ID_ICON);
        addView(iv, 0, params);
    }

    private void addTitle(Context ctx){
        TextView tv = new TextView(ctx);
        if(textColorRes != -1) tv.setTextColor(getResources().getColor(textColorRes));
        //if(textSizeRes != -1) tv.setTextSize(textSize);
        if(textSize != -1) tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tv.setText(title);
        tv.setId(ID_TITLE);
        if(textSelectorRes != null) tv.setTextColor(textSelectorRes);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        if(getChildCount() != 0) {
            params.addRule(RelativeLayout.RIGHT_OF, getChildAt(0).getId());
            params.leftMargin = intervalGap;
        }
        addView(tv, params);
    }
}
