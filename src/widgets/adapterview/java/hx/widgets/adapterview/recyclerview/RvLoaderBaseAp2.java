package hx.widgets.adapterview.recyclerview;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import hx.widgets.adapterview.IReq3;

/**
 * Created by rose on 16-8-12.
 */

@Deprecated
public abstract class RvLoaderBaseAp2<Rv extends RecyclerView, Ap extends Ap2Base> {

//    Rv _rv;
    Ap adapter;
    Activity act;

    public RvLoaderBaseAp2(Activity act, Rv _rv, Ap adapter){
        this.act = act;
        this.adapter = adapter;
        init(_rv);

    }
    private void init(Rv _rv){
        LinearLayoutManager layoutManager = new LinearLayoutManager(act);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _rv.setLayoutManager(layoutManager);
        _rv.setAdapter(adapter);
    }

//    protected abstract <T> RvLoaderBaseAp2 init(IReq2<Pager<T>> reqApi);
    protected abstract RvLoaderBaseAp2 init(IReq3 reqHandler);
}
