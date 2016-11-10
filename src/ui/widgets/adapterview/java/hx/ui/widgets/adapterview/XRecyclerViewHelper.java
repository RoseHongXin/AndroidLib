package hx.ui.widgets.adapterview;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.lang.reflect.Field;

import hx.req.bean.Pager;
import rx.Observable;

/**
 * Created by rose on 16-8-12.
 */


public class XRecyclerViewHelper<Ap extends ApBase<Vh, T>, Vh extends VhBase<T> , T> {

    final int REFRESH_TIME_THRESHOLD = 256;
    XRecyclerView _rv;
    Ap adapter;
    IReqObservableApi<T> reqCallback;
    HandlerCallback<T> handlerCallback;
    HandlerCallback2<T> handlerCallback2;
    Activity act;
    XRecyclerView.LoadingListener listener;
    ArrowRefreshHeader refreshHeader;

    public XRecyclerViewHelper init(){
        this.listener = new XRecyclerView.LoadingListener() {
            volatile int curPage = 1;
            @Override
            public void onRefresh() {
                if(handlerCallback != null) handlerCallback.onRefresh();
                (handlerCallback == null ?  reqCallback.get(1) : handlerCallback.getReqObservableApi(1))
                        //because the RetrofitBase request, has takeWhile stream,
                        //if the onErrorReturn override the original method, if it would jump over the takeWhile stream flow ?
//                        .onErrorReturn(throwable -> null)
                        .doOnCompleted(() -> {
                            _rv.postDelayed(() -> {
                                _rv.refreshComplete();
                            }, REFRESH_TIME_THRESHOLD);

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
                (handlerCallback == null ?  reqCallback.get(curPage + 1) : handlerCallback.getReqObservableApi(curPage + 1))
                        .doOnCompleted(() -> _rv.loadMoreComplete())
                        .subscribe(res -> {
                            if(res != null && !res.list.isEmpty()) {
                                ++curPage;
                                adapter.addData(res.list);
                            }
                        });
            }
        };
        _rv.setLoadingListener(listener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(act);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _rv.setLayoutManager(layoutManager);
        _rv.setAdapter(adapter);
        try {
            Field rh = _rv.getClass().getDeclaredField("mRefreshHeader");
            rh.setAccessible(true);
            refreshHeader = (ArrowRefreshHeader)rh.get(_rv);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return this;
    }

    public XRecyclerViewHelper init(Activity act, XRecyclerView _rv, Ap adapter, HandlerCallback<T> handlerCallback){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
        this.handlerCallback = handlerCallback;
        return init();
    }
    public XRecyclerViewHelper init(Activity act, XRecyclerView _rv, Ap adapter, IReqObservableApi<T> reqCallback){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
        this.reqCallback = reqCallback;
        return init();
    }
    public XRecyclerViewHelper init(Activity act, XRecyclerView _rv, Ap adapter, HandlerCallback2<T> reqCallback){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
        this.handlerCallback2 = reqCallback;
        init();
        this.listener = new XRecyclerView.LoadingListener() {
            volatile int curPage = 1;
            @Override
            public void onRefresh() {
                handlerCallback2.getReqObservableApi(1)
                        .doOnCompleted(() -> {
                            _rv.refreshComplete();
                            handlerCallback2.onReqFinished();
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
                handlerCallback2.getReqObservableApi(curPage + 1)
                        .doOnCompleted(() -> {
                            _rv.loadMoreComplete();
                            handlerCallback2.onReqFinished();
                        })
                        .subscribe(res -> {
                            if(res != null && !res.list.isEmpty()) {
                                ++curPage;
                                adapter.addData(res.list);
                            }
                        });
            }
        };
        _rv.setLoadingListener(listener);
        return this;
    }
    public void doRefresh(){
        refreshHeader.setState(ArrowRefreshHeader.STATE_REFRESHING);
        refreshHeader.setVisiableHeight(refreshHeader.mMeasuredHeight);
        _rv.postDelayed(() -> {
            listener.onRefresh();
        }, REFRESH_TIME_THRESHOLD);
    }

    public interface IReqObservableApi<T>{
        Observable<Pager<T>> get(int page);
    }

    public interface HandlerCallback<T>{
        Observable<Pager<T>> getReqObservableApi(int page);
        void onRefresh();
    }

    public interface HandlerCallback2<T>{
        Observable<Pager<T>> getReqObservableApi(int page);
        void onReqFinished();
    }
}
