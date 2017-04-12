package hx.widgets.adapterview.recyclerview;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import hx.widgets.adapterview.VhBase;

/**
 * Created by rose on 16-8-12.
 */

@Deprecated
public class RvLoaderBase<Rv extends RecyclerView, Ap extends ApBase<Vh, T>, Vh extends VhBase<T>, T> {

    Rv _rv;
    Ap adapter;
    Activity act;

    public RvLoaderBase(Activity act, Rv _rv, Ap adapter){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
        LinearLayoutManager layoutManager = new LinearLayoutManager(act);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _rv.setLayoutManager(layoutManager);
        _rv.setAdapter(adapter);
    }

    /*protected RvLoaderBase init(IReq2<T> reqApi){
        return this;
    }
    protected RvLoaderBase init(IReq4<T> reqHandler){
        return this;
    }*/
}
