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

    protected SwipeRefreshContainer _sr_container;
    protected SwipeRefreshLoader mSwipeRefreshLoader;
    private InitCallback mCb;

    public static FSwipeRefresh newInstance(InitCallback cb) {
        if(cb == null){
            throw new NullPointerException("Must specify a InitCallback");
        }
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
        mSwipeRefreshLoader = mCb.getSwipeRefreshLoader(_sr_container, (RecyclerView)_sr_container.getTargetView());
        sRegisterRefreshCb(() -> mSwipeRefreshLoader.doRefresh());
    }

    public interface InitCallback{
        SwipeRefreshLoader getSwipeRefreshLoader(SwipeRefreshContainer _sr_container, RecyclerView _rv);
    }
}
