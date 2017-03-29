package hx.widgets.adapterview;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.reflect.Field;

import hx.req.IReqConstant;

/**
 * Created by rose on 16-8-12.
 */

public class XRVLoader<Ap extends ApBase<Vh, T>, Vh extends VhBase<T> , T> {

    XRecyclerView _rv;
    Ap adapter;
    Activity act;
    XRecyclerView.LoadingListener listener;
    ArrowRefreshHeader refreshHeader;

    public XRVLoader init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(act);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _rv.setLayoutManager(layoutManager);
        _rv.setAdapter(adapter);
        refreshSet();
        return this;
    }

    private void registerListener(XRecyclerView.LoadingListener listener){
        if(listener != null) {
            this.listener = listener;
            _rv.setLoadingListener(listener);
        }
    }
    
    private void refreshSet(){
        try {
            Field rh = _rv.getClass().getDeclaredField("mRefreshHeader");
            rh.setAccessible(true);
            refreshHeader = (ArrowRefreshHeader)rh.get(_rv);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public XRVLoader init(Activity act, XRecyclerView _rv, Ap adapter, IReq2<T> reqApi){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
//        this.reqApi = reqApi;
        XRecyclerView.LoadingListener listener = new XRecyclerView.LoadingListener() {
            volatile int curPage = 1;
            @Override
            public void onRefresh() {
                reqApi.get(1)
                        .doOnCompleted(() -> _rv.postDelayed(_rv::refreshComplete, IReqConstant.REFRESH_TIME_THRESHOLD))
                        .subscribe(res -> {
                            //api has handled this occasion. this's could be removed.
                            if(res != null){
                                curPage = 1;
                                adapter.setData(res.list);
                            }
                        });
            }
            @Override
            public void onLoadMore() {
                reqApi.get(curPage + 1)
                        .doOnCompleted(() -> _rv.postDelayed(_rv::loadMoreComplete, IReqConstant.REFRESH_TIME_THRESHOLD))
                        .subscribe(res -> {
                            //api has handled this occasion. this's could be removed.
                            if(res != null && !res.list.isEmpty()) {
                                ++curPage;
                                adapter.addData(res.list);
                            }
                        });
            }
        };
        registerListener(listener);
        return init();
    }
    public XRVLoader init(Activity act, XRecyclerView _rv, Ap adapter, IReq4<T> reqHandler){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
//        this.reqHandler = reqHandler;
        XRecyclerView.LoadingListener listener = new XRecyclerView.LoadingListener() {
            volatile int curPage = 1;
            @Override
            public void onRefresh() {
                reqHandler.onRefresh();
                reqHandler.getReqApi(1)
                        .doOnCompleted(() -> {
                            _rv.postDelayed(_rv::refreshComplete, IReqConstant.REFRESH_TIME_THRESHOLD);
                            reqHandler.onComplete();
                        })
                        .subscribe(res -> {
                            //api has handled this occasion. this's could be removed.
                            if(res != null){
                                curPage = 1;
                                adapter.setData(res.list);
                            }
                        });
            }
            @Override
            public void onLoadMore() {
                reqHandler.getReqApi(curPage + 1)
                        .doOnCompleted(() -> {
                            _rv.postDelayed(_rv::loadMoreComplete, IReqConstant.REFRESH_TIME_THRESHOLD);
                            reqHandler.onComplete();
                        })
                        .subscribe(res -> {
                            //api has handled this occasion. this's could be removed.
                            if(res != null && !res.list.isEmpty()) {
                                ++curPage;
                                adapter.addData(res.list);
                            }
                        });
            }
        };
        registerListener(listener);
        return init();
    }

    public void doRefresh(){
        refreshHeader.setState(ArrowRefreshHeader.STATE_REFRESHING);
        refreshHeader.setVisiableHeight(refreshHeader.mMeasuredHeight);
        _rv.postDelayed(() -> {
            listener.onRefresh();
        }, IReqConstant.REFRESH_TIME_THRESHOLD);
    }
}
