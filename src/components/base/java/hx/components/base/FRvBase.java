package hx.components.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hx.components.IRvRefreshListener;
import hx.lib.R;

/**
 * Created by Rose on 3/1/2017.
 */

public class FRvBase extends Fragment {

    int DEFAULT_EXPIRE = 3 * 60 * 1000;

    int expire;
    boolean isVisibleToUser = false;
    int pageVisibleCount = 1;
    long pageLastValidTimeMillis = 0;
    IRvRefreshListener fraRefreshListener;

    protected void _registerRvRefreshListener(IRvRefreshListener listener){
        this.fraRefreshListener = listener;
        this.expire = DEFAULT_EXPIRE;
    }
    protected void _registerRvRefreshListener(IRvRefreshListener listener, int expire){
        this.fraRefreshListener = listener;
        if(expire <= 0) this.expire = DEFAULT_EXPIRE;
        else this.expire = expire;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.f_rv_base, container, false);
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if(ifNeedRefreshPage()) fraRefreshListener.onPageRefresh();
    }
}
