package hx.ui.widgets;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by rose on 16-8-23.
 */
public class VpCustomize {

    public static void config(ViewPager _vp){
        /*try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            //Scroller could be Customized;
            //Scroller scroller = new Scroller(_vp.getContext(), new LinearInterpolator());
            FixedSpeedScroller scroller = new FixedSpeedScroller(_vp.getContext(), new LinearInterpolator());
            field.set(_vp, scroller);
        } catch (Exception e) {
        }*/
        //_vp.setOnTouchListener((v, event) -> true);
//        _vp.clearAnimation();
    }

    private static class FixedSpeedScroller extends Scroller {

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, 100);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, 100);
        }
    }
    
}
