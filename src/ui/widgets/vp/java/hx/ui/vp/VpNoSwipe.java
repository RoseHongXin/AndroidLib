package hx.ui.vp;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Rose on 9/27/2016.
 */
public class VpNoSwipe extends ViewPager {

    boolean swipeScrollEnabled = false;

    public void setSwipeScrollEnabled(boolean enabled){
        this.swipeScrollEnabled = enabled;
    }

    public VpNoSwipe(Context context) {
        super(context);
    }

    public VpNoSwipe(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return swipeScrollEnabled && super.onTouchEvent(ev);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return swipeScrollEnabled && super.onInterceptTouchEvent(ev);
    }

    //去除页面切换时的滑动翻页效果
    /*@Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }*/
}
