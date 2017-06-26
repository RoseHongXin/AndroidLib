package hx.components;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import hx.lib.R;
import hx.view.swiperefresh.SwipeRefreshContainer;
import hx.widget.adapterview.swiperefresh.SwipeRefreshLoader;

/**
 * Created by Rose on 3/1/2017.
 */

public class FSwipeRefresh extends FBase {

    private SwipeRefreshContainer _sr_container;
    private RecyclerView _sr_target;
    protected SwipeRefreshLoader mSwipeRefreshLoader;
    private InitCallback mCb;

    public static FSwipeRefresh newInstance(InitCallback cb) {
        if(cb == null) throw new NullPointerException("Must specify a InitCallback");
        FSwipeRefresh fragment = new FSwipeRefresh();
        fragment.mCb = cb;
        return fragment;
    }

    @Override
    public int sGetLayoutRes() {
        return R.layout.l_swipe_refresh_rv;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _sr_container = (SwipeRefreshContainer)view.findViewById(R.id._sr_container);
        init();
    }

    private void init(){
        if(mCb == null || _sr_container == null) return;
        _sr_target = (RecyclerView) _sr_container.getTargetView();
        mSwipeRefreshLoader = mCb.getSwipeRefreshLoader(_sr_container, _sr_target);
        sRegisterRefreshCb(() -> mSwipeRefreshLoader.doRefresh());
    }

    protected void sSetInitCallback(InitCallback cb){
        if(cb == null) throw new NullPointerException("Must specify a InitCallback");
        this.mCb = cb;
        init();
    }

    public interface InitCallback{
        SwipeRefreshLoader getSwipeRefreshLoader(SwipeRefreshContainer _sr_container, RecyclerView _rv);
    }
}
