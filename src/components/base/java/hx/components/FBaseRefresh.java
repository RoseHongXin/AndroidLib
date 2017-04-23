package hx.components;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rose on 16-8-11.
 */

public abstract class FBaseRefresh extends FBase {

    @IdRes final int ID_SRL = 1;

    SwipeRefreshLayout _srl = null;
    
    public abstract IRefreshCb sRegisterRefreshCb();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(sGetLayoutRes(), container, false);
        mRefreshCb = sRegisterRefreshCb();
        if(mRefreshCb != null){
            _srl = new SwipeRefreshLayout(getContext());
            _srl.setId(ID_SRL);
            _srl.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            _srl.addView(layout);
            _srl.setOnRefreshListener(() -> mRefreshCb.onRefresh());
            layout = _srl;
        }
        return layout;
    }

    @Override
    public void onStop(){
        super.onStop();
        if(_srl != null && _srl.isRefreshing()) sStopRefresh();
    }

    protected void sStopRefresh(){
        if(_srl != null) _srl.setRefreshing(false);
    }
    protected void sRefresh(){
        _srl.setProgressViewOffset(false, 0, 100);
        if(mRefreshCb != null){
            _srl.setRefreshing(true);
            mRefreshCb.onRefresh();
        }

        /*getActivity().runOnUiThread(() -> {
            if(mRefreshCb != null){
                _srl.setRefreshing(true);
                mRefreshCb.onRefresh();
            }
        });
        _srl.post(() -> {
            if(mRefreshCb != null){
                _srl.setRefreshing(true);
                mRefreshCb.onRefresh();
            }
        });*/
    }
}
