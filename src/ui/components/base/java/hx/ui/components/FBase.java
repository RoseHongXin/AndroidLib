package hx.ui.components;

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
    int DEFAULT_EXPIRE = 3 * 60 * 1000;

    int expire;
    boolean isVisibleToUser = false;
    int pageVisibleCount = 1;
    long pageLastValidTimeMillis = 0;
    IFraRefreshListener fraRefreshListener;

    @IdRes final int ID_SRL = 1;

    SwipeRefreshLayout _srl = null;
    IRefreshCallback refreshCallback;
    
    @LayoutRes public abstract int _getLayoutRes();
    public abstract IRefreshCallback _registerRefreshCallback();
    //acting more like boolean, if null, then there would be no refresh widget.
    public abstract View _getTitleBar();
    //public abstract void onVisible();
    //should use abstract method.
    public void _registerFraRefreshListener(IFraRefreshListener listener){
        this.fraRefreshListener = listener;
        this.expire = DEFAULT_EXPIRE;
    }
    public void _registerFraRefreshListener(IFraRefreshListener listener, int expire){
        this.fraRefreshListener = listener;
        /*if(expire <= 0) this.expire = DEFAULT_EXPIRE;
        else this.expire = expire;*/
        this.expire = expire;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(_getLayoutRes(), container, false);
        View titleBar = _getTitleBar();
        refreshCallback = _registerRefreshCallback();
        if(refreshCallback != null){
            _srl = new SwipeRefreshLayout(getContext());
            _srl.setId(ID_SRL);
            _srl.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            _srl.addView(layout);
            _srl.setOnRefreshListener(() -> refreshCallback.onRefresh());
            layout = _srl;
        }
        if(titleBar != null){
            LinearLayout content = new LinearLayout(getContext());
            content.setOrientation(LinearLayout.VERTICAL);
            content.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            content.addView(titleBar);
            content.addView(layout);
            layout = content;
        }
        return layout;
    }

    private boolean ifNeedRefreshPage(){
        if(isVisibleToUser && fraRefreshListener != null) {
            if (pageVisibleCount == 1) {
                pageLastValidTimeMillis = System.currentTimeMillis();
                ++pageVisibleCount;
                return true;
            } else {
                long cur = System.currentTimeMillis();
                if((cur - pageLastValidTimeMillis) > expire){
                    pageVisibleCount = 0;
                    pageLastValidTimeMillis = cur;
                    return true;
                }
                ++pageVisibleCount;
            }
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(ifNeedRefreshPage()) {
            fraRefreshListener.onPageRefresh();
            /*this.getView().postDelayed(() -> {
                fraRefreshListener.onPageRefresh();
            }, 1000);*/
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        if(_srl != null && _srl.isRefreshing()) _stopRefresh();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if(ifNeedRefreshPage()) fraRefreshListener.onPageRefresh();
    }

    protected void _stopRefresh(){
        if(_srl != null) _srl.setRefreshing(false);
    }
    protected void _refresh(){
        _srl.setProgressViewOffset(false, 0, 100);
        if(refreshCallback != null){
            _srl.setRefreshing(true);
            refreshCallback.onRefresh();
        }

        /*getActivity().runOnUiThread(() -> {
            if(refreshCallback != null){
                _srl.setRefreshing(true);
                refreshCallback.onRefresh();
            }
        });
*/
        /*_srl.post(() -> {
            if(refreshCallback != null){
                _srl.setRefreshing(true);
                refreshCallback.onRefresh();
            }
        });*/
    }
}
