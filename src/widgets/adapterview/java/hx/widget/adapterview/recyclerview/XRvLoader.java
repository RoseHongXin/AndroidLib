package hx.widget.adapterview.recyclerview;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.reflect.Field;
import hx.widget.adapterview.IReq2;
import hx.widget.adapterview.VhBase;
import static hx.widget.adapterview.IConstants.*;

/**
 * Created by rose on 16-8-12.
 */

public class XRvLoader<Ap extends ApBase<Vh, T>, Vh extends VhBase<T> , T> {


    private XRecyclerView _rv;
    private Ap mAdapter;
    private Activity mAct;
    private XRecyclerView.LoadingListener mListener;
    private ArrowRefreshHeader mRefreshHeader;
    private boolean mLoadMoreEnabled = true;

    public XRvLoader init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(mAct);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _rv.setLayoutManager(layoutManager);
        _rv.setAdapter(mAdapter);
        refreshSet();
        return this;
    }

    private void registerListener(XRecyclerView.LoadingListener listener){
        if(listener != null) {
            this.mListener = listener;
            _rv.setLoadingListener(mListener);
        }
    }
    
    private void refreshSet(){
        try {
            Field rh = _rv.getClass().getDeclaredField("mRefreshHeader");
            rh.setAccessible(true);
            mRefreshHeader = (ArrowRefreshHeader)rh.get(_rv);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public XRvLoader init(Activity mAct, XRecyclerView _rv, Ap adapter, IReq2<T> reqApi){
        this.mAct = mAct;
        this._rv = _rv;
        this.mAdapter = adapter;
//        this.reqApi = reqApi;
        XRecyclerView.LoadingListener mListener = new XRecyclerView.LoadingListener() {
            volatile int curPage = 1;
            @Override
            public void onRefresh() {
                reqApi.get(1)
                        .doOnCompleted(() -> _rv.postDelayed(_rv::refreshComplete, REFRESH_THRESHOLD))
                        .subscribe(res -> {
                            if(res != null){
                                curPage = 1;
                                mAdapter.setData(res);
                            }
                        });
            }
            @Override
            public void onLoadMore() {
                reqApi.get(curPage + 1)
                        .doOnCompleted(() -> _rv.postDelayed(_rv::loadMoreComplete, REFRESH_THRESHOLD))
                        .subscribe(res -> {
                            if(res != null && !res.isEmpty()) {
                                ++curPage;
                                mAdapter.addData(res);
                            }
                        });
            }
        };
        registerListener(mListener);
        return init();
    }

    public void loadMoreEnable(boolean enable){
        this.mLoadMoreEnabled = enable;
        _rv.setLoadingMoreEnabled(enable);
    }

    public void doRefresh(){
        mRefreshHeader.setState(ArrowRefreshHeader.STATE_REFRESHING);
        mRefreshHeader.setVisiableHeight(mRefreshHeader.mMeasuredHeight);
        _rv.postDelayed(() -> {
            mListener.onRefresh();
        }, REFRESH_THRESHOLD);
    }
}
