package hx.ui.widgets.adapterview;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by rose on 16-8-12.
 */


public class RvLoaderWithAp2<Ap extends Ap2Base> {

    RecyclerView _rv;
    Ap adapter;
    Activity act;

    private RvLoaderWithAp2 init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(act);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _rv.setLayoutManager(layoutManager);
        _rv.setAdapter(adapter);
        return this;

    }

    public RvLoaderWithAp2 init(Activity act, RecyclerView _rv, Ap adapter){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
        return init();
    }
    public RvLoaderWithAp2 init(Activity act, RecyclerView _rv, Ap adapter, List data){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
        this.adapter.addData(data);
        return init();
    }

    public RvLoaderWithAp2 init(Activity act, RecyclerView _rv, Ap adapter, IReqApi<ISFAp2Base> reqCallback){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
        init();
        reqCallback.get()
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
