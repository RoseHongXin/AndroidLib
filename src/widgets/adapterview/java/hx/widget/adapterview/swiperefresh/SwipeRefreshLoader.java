package hx.widget.adapterview.swiperefresh;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import hx.view.swiperefresh.SwipeRefreshContainer;
import hx.widget.adapterview.IReq2;
import hx.widget.adapterview.VhBase;
import hx.widget.adapterview.recyclerview.ApBase;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/20 0020.
 */

public class SwipeRefreshLoader<Ap extends ApBase<Vh, T>, Vh extends VhBase<T>, T> {

    private SwipeRefreshContainer _src;
//    private SwipeRefreshHeaderView _sr_header;
//    private SwipeLoadMoreFooterView _sr_footer;
    private RecyclerView _sr_target;
    private Ap mAdapter;
    private IReq2<T> mApi;
    private int mFistPageIdx = 1;
    private int mCurPage = mFistPageIdx;
    private boolean mLoadMoreEnable = true;

    public SwipeRefreshLoader(SwipeRefreshContainer _src){
        this(_src, true);
    }
    public SwipeRefreshLoader(SwipeRefreshContainer _src, boolean loadMoreEnable){
        this._src = _src;
        this.mLoadMoreEnable = loadMoreEnable;
        _src.setLoadMoreEnabled(mLoadMoreEnable);
        _sr_target = (RecyclerView) _src.getTargetView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_src.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _sr_target.setLayoutManager(linearLayoutManager);
    }
    public SwipeRefreshLoader api(IReq2<T> api){
        this.mApi = api;
        return this;
    }
    public SwipeRefreshLoader create(Ap adapter, IReq2<T> api){
        this.mAdapter = adapter;
        this.mApi = api;
        return create();
    }
    public SwipeRefreshLoader create(IReq2<T> api){
        this.mApi = api;
        return create();
    }
    public SwipeRefreshLoader create(Ap adapter, int idx, IReq2<T> api){
        this.mAdapter = adapter;
        this.mFistPageIdx = idx;
        this.mApi = api;
        return create();
    }
    private SwipeRefreshLoader create(){
        _sr_target.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !ViewCompat.canScrollVertically(recyclerView, 1)) _src.setLoadingMore(true);
            }
        });
        _sr_target.setAdapter(mAdapter);
        _src.setOnRefreshListener(() -> {
            mApi.get(1)
                    .doOnCompleted(() -> _src.setRefreshing(false))
                    .takeWhile(this::dataIsReady)
                    .subscribe(datas -> {
                        mCurPage = 1;
                        mAdapter.setData(datas);
                    });
        });
        _src.setOnLoadMoreListener(() -> {
            if(!mLoadMoreEnable){ _src.setLoadingMore(false); return; }
            mApi.get(mCurPage + 1)
                    .doOnCompleted(() -> _src.setLoadingMore(false))
                    .takeWhile(this::dataIsReady)
                    .subscribe(datas -> {
                        ++mCurPage;
                        mAdapter.addData(datas);
                    });
        });
        return this;
    }

    private boolean dataIsReady(List<T> datas){
        if(datas == null || datas.isEmpty()) {
            if(mLoadMoreEnable) _src.setLoadMoreEnabled(false);
            return false;
        }
        else {
            if(mLoadMoreEnable) _src.setLoadingMore(true);
        }
        return true;
    }

    public SwipeRefreshLoader doRefresh(){
        _src.setRefreshing(true);
        return this;
    }

}
