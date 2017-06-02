package hx.widget.adapterview.recyclerview;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import hx.widget.adapterview.VhBase;

/**
 * Created by rose on 16-8-12.
 */

public class RvLoader<Ap extends ApBase<Vh, T>, Vh extends VhBase<T> , T> {


    private RecyclerView _rv;
    private Ap mAdapter;
    private Activity mAct;


    private RvLoader init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(mAct);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _rv.setLayoutManager(layoutManager);
        _rv.setAdapter(mAdapter);
        return this;
    }

    public RvLoader load(Activity mAct, RecyclerView _rv, Ap adapter){
        this.mAct = mAct;
        this._rv = _rv;
        this.mAdapter = adapter;
        return init();
    }
}
