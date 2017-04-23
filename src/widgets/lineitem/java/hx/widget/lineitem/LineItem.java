package hx.widget.lineitem;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hx.lib.R;

/**
 * Created by rose on 16-8-11.
 */

public class LineItem extends FrameLayout {

    final private int NOTHING_SET = -1;

    ImageView _iv_left;
    TextView _tv_left;
    ImageView _iv_right;
    TextView _tv_right;

    @DrawableRes int iconLeft, iconRight;
    int iconSizeLeft, iconSizeRight;
    @ColorInt int textColorLeft, textColorRight;
    int textSizeLeft, textSizeRight;
    String textLeft, textRight;
    int gapLeft, gapRight, paddingHorizontal, paddingVertical;
    View layout;


    public ImageView _iv_left() {
        return _iv_left;
    }
    public ImageView _iv_right() {
        return _iv_right;
    }
    public TextView _tv_left() {
        return _tv_left;
    }
    public TextView _tv_right() {
        return _tv_right;
    }

    public int getPaddingHorizontal() {
        return paddingHorizontal;
    }
    public void setPaddingHorizontal(int paddingHorizontal) {
        this.paddingHorizontal = paddingHorizontal;
//        layout.setPadding(paddingHorizontal, getPaddingTop(), paddingHorizontal, getPaddingBottom());
//        requestLayout();
        setPadding(paddingHorizontal, getPaddingTop(), paddingHorizontal, getPaddingBottom());
    }
    public int getPaddingVertical() {
        return paddingVertical;
    }
    public void setPaddingVertical(int paddingVertical) {
        this.paddingVertical = paddingVertical;
//        layout.setPadding(getPaddingLeft(), paddingVertical, getPaddingRight(), paddingVertical);
//        requestLayout();
        setPadding(getPaddingLeft(), paddingVertical, getPaddingRight(), paddingVertical);
    }
    public int getGapLeft() {
        return gapLeft;
    }
    public void setGapLeft(int gapLeft) {
        this.gapLeft = gapLeft;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) _iv_left.getLayoutParams();
        params.rightMargin = gapLeft;
        _iv_left.setLayoutParams(params);
    }
    public int getGapRight() {
        return gapRight;
    }
    public void setGapRight(int gapRight) {
        this.gapRight = gapRight;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) _tv_right.getLayoutParams();
        params.rightMargin = gapRight;
        _tv_right.setLayoutParams(params);
    }
    @DrawableRes public int getIconLeft() {
        return iconLeft;
    }
    public void setIconLeft(@DrawableRes int iconLeft) {
        this.iconLeft = iconLeft;
        _iv_left.setVisibility(VISIBLE);
        _iv_left.setImageResource(iconLeft);
    }
    @DrawableRes public int getIconRight() {
        return iconRight;
    }
    public void setIconRight(@DrawableRes int iconRight) {
        this.iconRight = iconRight;
        _iv_right.setVisibility(VISIBLE);
        _iv_right.setImageResource(iconRight);
    }
    public int getIconSizeLeft() {
        return iconSizeLeft;
    }
    public void setIconSizeLeft(int iconSizeLeft) {
        this.iconSizeLeft = iconSizeLeft;
        ViewGroup.LayoutParams params = _iv_left.getLayoutParams();
        params.width = iconSizeLeft;
        params.height = iconSizeLeft;
        _iv_left.setLayoutParams(params);
    }
    public int getIconSizeRight() {
        return iconSizeRight;
    }
    public void setIconSizeRight(int iconSizeRight) {
        this.iconSizeRight = iconSizeRight;
        ViewGroup.LayoutParams params = _iv_right.getLayoutParams();
        params.width = iconSizeRight;
        params.height = iconSizeRight;
        _iv_right.setLayoutParams(params);
    }

    public int getTextColorLeft() {
        return textColorLeft;
    }
    public void setTextColorLeft(int textColorLeft) {
        this.textColorLeft = textColorLeft;
        _tv_left.setTextColor(textColorLeft);
    }
    public int getTextColorRight() {
        return textColorRight;
    }
    public void setTextColorRight(int textColorRight) {
        this.textColorRight = textColorRight;
        _tv_right.setTextColor(textColorRight);
    }
    public String getTextLeft() {
        return textLeft;
    }
    public void setTextLeft(String textLeft) {
        this.textLeft = textLeft;
        _tv_left.setVisibility(VISIBLE);
        _tv_left.setText(textLeft);
    }
    public String getTextRight() {
        return textRight;
    }
    public void setTextRight(String textRight) {
        this.textRight = textRight;
        _tv_right.setVisibility(VISIBLE);
        _tv_right.setText(textRight);
    }
    public int getTextSizeLeft() {
        return textSizeLeft;
    }
    public void setTextSizeLeft(int textSizeLeft) {
        this.textSizeLeft = textSizeLeft;
        _tv_left.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeLeft);
    }
    public int getTextSizeRight() {
        return textSizeRight;
    }
    public void setTextSizeRight(int textSizeRight) {
        this.textSizeRight = textSizeRight;
        _tv_right.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeRight);
    }

    public void setText(String text){
        setTextLeft(text);
    }
    public void setIcon(@DrawableRes int drawableRes){
        setIconLeft(drawableRes);
    }

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
        View layout = inflate(ctx, R.layout.l_lineitem, null);
        addView(layout);
        this.layout = layout;
        _iv_left = (ImageView)findViewById(R.id._iv_left);
        _tv_left = (TextView)findViewById(R.id._tv_left);
        _iv_right = (ImageView)findViewById(R.id._iv_right);
        _tv_right = (TextView)findViewById(R.id._tv_right);

        TypedArray ta = ctx.obtainStyledAttributes(attrs, R.styleable.LineItem);

        gapLeft = ta.getDimensionPixelSize(R.styleable.LineItem_gapLeft, NOTHING_SET); if(gapLeft != NOTHING_SET) setGapLeft(gapLeft);
        gapRight = ta.getDimensionPixelSize(R.styleable.LineItem_gapRight, NOTHING_SET); if(gapRight != NOTHING_SET) setGapRight(gapRight);

        paddingHorizontal = ta.getDimensionPixelSize(R.styleable.LineItem_paddingHorizontal, NOTHING_SET); if(paddingHorizontal != NOTHING_SET) setPaddingHorizontal(paddingHorizontal);
        paddingVertical = ta.getDimensionPixelSize(R.styleable.LineItem_paddingVertical, NOTHING_SET); if(paddingVertical != NOTHING_SET) setPaddingVertical(paddingVertical);

        iconLeft = ta.getResourceId(R.styleable.LineItem_iconLeft, NOTHING_SET); if(iconLeft != NOTHING_SET) setIconLeft(iconLeft);
        iconRight = ta.getResourceId(R.styleable.LineItem_iconRight, NOTHING_SET); if(iconRight != NOTHING_SET) setIconRight(iconRight);
        iconSizeLeft = ta.getDimensionPixelSize(R.styleable.LineItem_iconSizeLeft, NOTHING_SET); if(iconSizeLeft != NOTHING_SET) setIconSizeLeft(iconSizeLeft);
        iconSizeRight = ta.getDimensionPixelSize(R.styleable.LineItem_iconSizeRight, NOTHING_SET); if(iconSizeRight != NOTHING_SET) setIconSizeRight(iconSizeRight);


        textLeft = ta.getString(R.styleable.LineItem_textLeft); if(!TextUtils.isEmpty(textLeft)) setTextLeft(textLeft);
        textRight = ta.getString(R.styleable.LineItem_textRight); if(!TextUtils.isEmpty(textRight)) setTextRight(textRight);

        textColorLeft = ta.getColor(R.styleable.LineItem_textColorLeft, NOTHING_SET); if(textColorLeft != NOTHING_SET) setTextColorLeft(textColorLeft);
        textColorRight = ta.getColor(R.styleable.LineItem_textColorRight, NOTHING_SET); if(textColorRight != NOTHING_SET) setTextColorRight(textColorRight);
        textSizeLeft = ta.getDimensionPixelSize(R.styleable.LineItem_textSizeLeft, NOTHING_SET); if(textSizeLeft != NOTHING_SET) setTextSizeLeft(textSizeLeft);
        textSizeRight = ta.getDimensionPixelSize(R.styleable.LineItem_textSizeRight, NOTHING_SET); if(textSizeRight != NOTHING_SET) setTextSizeRight(textSizeRight);

        ta.recycle();
    }
    
}
