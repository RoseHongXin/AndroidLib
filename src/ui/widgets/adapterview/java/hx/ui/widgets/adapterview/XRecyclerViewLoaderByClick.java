package hx.ui.widgets.adapterview;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by Rose on 10/13/2016.
 */
public class XRecyclerViewLoaderByClick<Ap extends ApBase<Vh, T>, Vh extends RecyclerView.ViewHolder, T> {

    XRecyclerView _rv;
    Ap adapter;
    IReqObservableApi<T> reqCallback;
    Activity act;
    View footer;

    private XRecyclerViewLoaderByClick init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(act);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _rv.setLoadingMoreEnabled(false);
        _rv.setPullRefreshEnabled(false);
        _rv.setLayoutManager(layoutManager);
        _rv.setAdapter(adapter);
        //_rv.setVisibility(View.VISIBLE);
        /*footer.setOnClickListener(new View.OnClickListener() {
            int curPage = 0;
            @Override
            public void onClick(View view) {
                reqCallback.get(curPage + 1)
                        .subscribe(res -> {
                            if(res == null || res.list.isEmpty()){
                                if(curPage == 0) _rv.setVisibility(View.GONE);
                                else _rv.addFootView(null);
                            }
                            else{
                                _rv.addFootView(footer);
                                adapter.setData(res.list);
                                ++curPage;
                            }
                        });
            }
        });
        _rv.addFootView(footer);
        footer.callOnClick();*/
        reqCallback.get(1)
                .subscribe(res -> {
                    if(res == null || res.list.isEmpty()){
                     _rv.setVisibility(View.GONE);
                    }
                    else{
                        _rv.addFootView(footer);
                        adapter.setData(res.list);
                    }
                });
        return this;
    }

    public XRecyclerViewLoaderByClick load(Activity act, XRecyclerView _rv, Ap adapter, @LayoutRes int footerId, IReqObservableApi<T> reqCallback){
        this.act  = act;
        this._rv = _rv;
        this.adapter = adapter;
        this.reqCallback = reqCallback;
        this.footer = act.getLayoutInflater().inflate(footerId, null);
        return init();
    }

}
