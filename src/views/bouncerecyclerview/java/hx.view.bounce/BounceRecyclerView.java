package hx.view.bounce;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/6/23 0023.
 */

public class BounceRecyclerView extends RecyclerView {

    private final int INVALIDE_POINTER_ID = -1;
    private final float DEFAULT_DRAG_RATIO = 0.5f;

//    private int mParentPaddingLeft, mParentPaddingTop, mParentPaddingRight, mParentPaddingBottom;
    private int mMarginLeft, mMarginTop, mMarginRight, mMarginBottom;

    private int mBounceDuration;
    private float mDragRatio = DEFAULT_DRAG_RATIO;

    private int mCurPaddingTop;
    private int mCurPaddingBottom;

    private float mLastX;
    private float mLastY;
    private float mInitDownX;
    private float mInitDownY;
    private int mActivePointerId;

    private Scroller mReleaseScroller;
    private ReleaseRunnable mReleaseRunnable;

    public BounceRecyclerView(Context context) {
        this(context, null);
    }

    public BounceRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BounceRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context ctx, @Nullable AttributeSet attrs, int defStyle){
        mBounceDuration = 2 * 1000;
        mCurPaddingTop = 0;
        mReleaseScroller = new Scroller(ctx);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mMarginLeft = mlp.leftMargin;
        mMarginTop = mlp.topMargin;
        mMarginRight = mlp.rightMargin;
        mMarginBottom = mlp.bottomMargin;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                mInitDownY = mLastY = getY();
                mActivePointerId = e.getPointerId(0);
                if(!mReleaseScroller.isFinished()) mReleaseScroller.forceFinished(true);
                break;
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                mActivePointerId = e.getPointerId(0);
                break;
            case MotionEvent.ACTION_MOVE:
                if(ifReadyLayout()){
                    float y = e.getY();
                    float yDiff = (y - mLastY) * mDragRatio;
                    int top = (int)(getTop() + yDiff + 0.5f);
                    layout(getLeft(), top, getRight(), top + getMeasuredHeight());
                    mLastY = y;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mActivePointerId = INVALIDE_POINTER_ID;
                mReleaseScroller.startScroll((int)getX(), (int)getY(), 0, (int)(getY() - mLastY + 0.5f), mBounceDuration);
                break;
        }
        return super.onTouchEvent(e);
    }

    private boolean ifReadyLayout(){
        int paddingTop = getPaddingTop();
        if(paddingTop >= 0) return true;
        return ViewCompat.canScrollVertically(this, 1) || this.getScrollY() < 0;
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()){
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(e);
    }*/



    private class ReleaseRunnable implements Runnable{
        @Override
        public void run() {
            int yDiff = 0;
            mReleaseScroller.startScroll(0, mCurPaddingTop, 0, yDiff, mBounceDuration);
        }
    }

}
