package hx.ui.widgets.adapterview;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.reflect.Field;

/**
 * Created by rose on 16-8-12.
 */

@Deprecated
public class RvLoaderV2<Ap extends ApBase<Vh, T>, Vh extends VhBase<T> , T> {

    final int REFRESH_TIME_THRESHOLD = 256;

    XRecyclerView _rv;
    Ap adapter;
    Activity act;
    XRecyclerView.LoadingListener listener;
    ArrowRefreshHeader refreshHeader;

    public RvLoaderV2 init(){
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

    public RvLoaderV2 init(Activity act, XRecyclerView _rv, Ap adapter, IReqApi2<T> reqApi){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
//        this.reqApi = reqApi;
        XRecyclerView.LoadingListener listener = new XRecyclerView.LoadingListener() {
            volatile int curPage = 1;
            @Override
            public void onRefresh() {
                reqApi.get(1)
                        .doOnCompleted(() -> _rv.postDelayed(_rv::refreshComplete, REFRESH_TIME_THRESHOLD))
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
                        .doOnCompleted(() -> _rv.postDelayed(_rv::loadMoreComplete, REFRESH_TIME_THRESHOLD))
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
    public RvLoaderV2 init(Activity act, XRecyclerView _rv, Ap adapter, IReqHandler<T> reqHandler){
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
                        .doOnCompleted(() -> _rv.postDelayed(_rv::refreshComplete, REFRESH_TIME_THRESHOLD))
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
                        .doOnCompleted(() -> _rv.postDelayed(_rv::loadMoreComplete, REFRESH_TIME_THRESHOLD))
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

    public RvLoaderV2 init(Activity act, XRecyclerView _rv, Ap adapter, IReqHandler2<T> reqHandler2){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
//        this.reqHandler2 = reqHandler2;
        XRecyclerView.LoadingListener listener = new XRecyclerView.LoadingListener() {
            volatile int curPage = 1;
            @Override
            public void onRefresh() {
                reqHandler2.getReqApi(1)
                        .doOnCompleted(() -> {
                            _rv.postDelayed(_rv::refreshComplete, REFRESH_TIME_THRESHOLD);
                            reqHandler2.onComplete();
                        })
                        .subscribe(res -> {
                            if(res != null){
                                curPage = 1;
                                adapter.setData(res.list);
                            }
                        });
            }
            @Override
            public void onLoadMore() {
                reqHandler2.getReqApi(curPage + 1)
                        .doOnCompleted(() -> {
                            _rv.postDelayed(_rv::loadMoreComplete, REFRESH_TIME_THRESHOLD);
                            reqHandler2.onComplete();
                        })
                        .subscribe(res -> {
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

   /* private <T> void refreshFinish(Pager<T> pager){

    }
    private <T> void loadMoreFinish(Pager<T> pager){
        if(pager != null && !pager.list.isEmpty()) {
            ++curPage;
            adapter.addData(pager.list);
        }
    }*/

    public void doRefresh(){
        refreshHeader.setState(ArrowRefreshHeader.STATE_REFRESHING);
        refreshHeader.setVisiableHeight(refreshHeader.mMeasuredHeight);
        _rv.postDelayed(() -> {
            listener.onRefresh();
        }, REFRESH_TIME_THRESHOLD);
    }
}
