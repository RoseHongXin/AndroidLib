package hx.widget.adapterview.swiperefresh;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import hx.view.swiperefresh.SwipeRefreshContainer;
import hx.widget.adapterview.IReq2;
import hx.widget.adapterview.VhBase;
import hx.widget.adapterview.recyclerview.ApBase;

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

    private SwipeRefreshLoader(){}

    public static SwipeRefreshLoader obtain(){
        return new SwipeRefreshLoader();
    }

    public SwipeRefreshLoader container(SwipeRefreshContainer _src){
        this._src = _src;
        return this;
    }
    public SwipeRefreshLoader adapter(Ap adapter){
        this.mAdapter = adapter;
        return this;
    }
    public SwipeRefreshLoader api(IReq2<T> api){
        this.mApi = api;
        return this;
    }
    public SwipeRefreshLoader firstPageIdx(int idx){
        mFistPageIdx = idx;
        return this;
    }
    public SwipeRefreshLoader create(){
        init();
        return this;
    }



    private void init(){
        _sr_target = (RecyclerView) _src.getTargetView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_src.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _sr_target.setLayoutManager(linearLayoutManager);
        _sr_target.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !ViewCompat.canScrollVertically(recyclerView, 1)) _src.setLoadingMore(true);
            }
        });
        _src.setOnLoadMoreListener(() -> {
            mApi.get(1)
                    .doOnCompleted(() -> _src.setRefreshing(false))
                    .takeWhile(this::dataIsReady)
                    .subscribe(datas -> {
                        mCurPage = 1;
                        mAdapter.setData(datas);
                    });
        });
        _src.setOnRefreshListener(() -> {
            mApi.get(mCurPage + 1)
                    .doOnCompleted(() -> _src.setLoadingMore(false))
                    .takeWhile(this::dataIsReady)
                    .subscribe(datas -> {
                        ++mCurPage;
                        mAdapter.addData(datas);
                    });

        });
    }

    private boolean dataIsReady(List<T> datas){
        if(datas == null || datas.isEmpty()) {
            _src.setLoadMoreEnabled(false);
            return false;
        }
        else _src.setLoadingMore(true);
        return true;
    }

    public void doRefresh(){
        _src.setRefreshing(true);
    }

}
