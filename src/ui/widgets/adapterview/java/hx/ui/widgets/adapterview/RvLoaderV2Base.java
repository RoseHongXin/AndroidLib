package hx.ui.widgets.adapterview;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.lang.reflect.Field;

/**
 * Created by rose on 16-8-12.
 */


public abstract class RvLoaderV2Base<Rv extends RecyclerView, Ap extends ApBase<Vh, T>, Vh extends VhBase<T>, T> {

    //request waiting header or footer show and dismiss time threshold.
    final int TOAST_GLANCE = 256;

//    Rv _rv;
//    Ap adapter;
    Activity act;

    public RvLoaderV2Base(Activity act){
        this.act = act;
    }


    protected RvLoaderV2Base init(Rv _rv, Ap adapter){
        LinearLayoutManager layoutManager = new LinearLayoutManager(act);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _rv.setLayoutManager(layoutManager);
        _rv.setAdapter(adapter);
        return this;
    }

    protected abstract RvLoaderV2Base init(Rv _rv, Ap adapter, IReqApi2<T> reqApi);
    protected abstract RvLoaderV2Base init(Rv _rv, Ap adapter, IReqHandler<T> reqHandler);
//    public RvLoaderV2Base init(Activity act, Rv _rv, Ap adapter, IReqHandler1<T> reqHandler1);
//    public RvLoaderV2Base init(Activity act, Rv _rv, Ap adapter, IReqHandler2<T> reqHandler2);
}
