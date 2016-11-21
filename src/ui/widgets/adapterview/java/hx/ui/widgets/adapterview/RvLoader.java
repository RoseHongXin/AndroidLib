package hx.ui.widgets.adapterview;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.List;

/**
 * Created by rose on 16-8-12.
 */


public class RvLoader<Ap extends ApBase<Vh, T>, Vh extends VhBase<T>, T> {

    RecyclerView _rv;
    Ap adapter;
    Activity act;

    private RvLoader init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(act);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _rv.setLayoutManager(layoutManager);
        _rv.setAdapter(adapter);
        return this;

    }
    public RvLoader init(Activity act, RecyclerView _rv, Ap adapter, List<T> data){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
        this.adapter.addData(data);
        return init();
    }

    public RvLoader init(Activity act, RecyclerView _rv, Ap adapter, IReq<T> reqApi){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
        init();
        reqApi.get()
                .subscribe(res -> {
                    if(res == null || res.list.isEmpty()){
                        _rv.setVisibility(View.GONE);
                    }
                    else{
                        adapter.setData(res.list);
                    }
                });
        return this;
    }
}
