package hx.components;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by rose on 16-8-11.
 */

public abstract class FBase extends Fragment {

    //default threshold if this page need to refresh.
    final int PAGE_DEFAULT_EXPIRE = 3 * 60 * 1000;
    final int PAGE_NO_EXPIRE = -1;

    int mExpire;
    boolean mIsVisibleToUser = false;
    int mPageVisibleCount = 1;
    long mPageLastValidTimeMillis = 0;
    IRefreshCallback mListener;

    @LayoutRes public abstract int _getLayoutRes();

    public void _registerFraRefreshListener(IRefreshCallback listener){
        this.mListener = listener;
        this.mExpire = PAGE_DEFAULT_EXPIRE;
    }
    public void _registerFraRefreshListener(IRefreshCallback listener, int expire){
        this.mListener = listener;
        if(mExpire <= 0) this.mExpire = PAGE_DEFAULT_EXPIRE;
        else this.mExpire = expire;
    }
    public void _registLoadListener(IRefreshCallback listener){
        this.mPageLastValidTimeMillis = PAGE_NO_EXPIRE;
        this.mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(_getLayoutRes(), container, false);
    }

    private boolean ifNeedRefreshPage(){
        if(mIsVisibleToUser && mListener != null) {
            if(mPageLastValidTimeMillis == PAGE_NO_EXPIRE){
                return true;
            }
            else if (mPageVisibleCount == 1) {
                mPageLastValidTimeMillis = System.currentTimeMillis();
                ++mPageVisibleCount;
                return true;
            } else {
                long cur = System.currentTimeMillis();
                if((cur - mPageLastValidTimeMillis) > mExpire){
                    mPageVisibleCount = 0;
                    mPageLastValidTimeMillis = cur;
                    return true;
                }
                ++mPageVisibleCount;
            }
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(ifNeedRefreshPage()) mListener.onRefresh();
    }

    @Override
    public void setUserVisibleHint(boolean mIsVisibleToUser) {
        super.setUserVisibleHint(mIsVisibleToUser);
        this.mIsVisibleToUser = mIsVisibleToUser;
        if(ifNeedRefreshPage()) mListener.onRefresh();
    }
}
