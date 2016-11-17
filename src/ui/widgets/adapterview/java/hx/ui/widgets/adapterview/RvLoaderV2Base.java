package hx.ui.widgets.adapterview;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.lang.reflect.Field;

/**
 * Created by rose on 16-8-12.
 */


public class RvLoaderV2Base<Rv extends RecyclerView, Ap extends ApBase<Vh, T>, Vh extends VhBase<T>, T> {

    //request waiting header or footer show and dismiss time threshold.
    final int TOAST_GLANCE = 256;

    Rv _rv;
    Ap adapter;
    Activity act;

    public RvLoaderV2Base(Activity act, Rv _rv, Ap adapter){
        this.act = act;
        this._rv = _rv;
        this.adapter = adapter;
        LinearLayoutManager layoutManager = new LinearLayoutManager(act);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _rv.setLayoutManager(layoutManager);
        _rv.setAdapter(adapter);
    }

    protected RvLoaderV2Base init(IReqApi2<T> reqApi){
        return this;
    }
    protected RvLoaderV2Base init(IReqHandler<T> reqHandler){
        return this;
    }
//    public RvLoaderV2Base init(Activity act, Rv _rv, Ap adapter, IReqHandler1<T> reqHandler1);
//    public RvLoaderV2Base init(Activity act, Rv _rv, Ap adapter, IReqHandler2<T> reqHandler2);
}
