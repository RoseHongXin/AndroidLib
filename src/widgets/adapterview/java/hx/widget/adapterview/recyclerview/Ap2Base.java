package hx.widget.adapterview.recyclerview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hx.widget.adapterview.ISFAp2Base;
import hx.widget.adapterview.VhBase;

/**
 * Created by rose on 16-8-12.
 *
 * Based on item position as the viewType
 *
 */

public abstract class Ap2Base extends RecyclerView.Adapter<VhBase> {

    final String TAG = "--Ap2Base--";

    protected Activity act;
    protected RecyclerView _rv;
    private List<ISFAp2Base> data = new ArrayList<>();

    protected abstract VhBase getVh(Activity act, ISFAp2Base data, int position);
    protected abstract void bind(VhBase holder, ISFAp2Base data, int position);

     protected Ap2Base(Activity act, RecyclerView rv){
         this.act = act;
         this._rv = rv;
    }

    @Override
    public VhBase onCreateViewHolder(ViewGroup parent, int viewType) {
        return getVh(act, data.get(viewType), viewType);
    }

    @Override
    public void onBindViewHolder(VhBase holder, int position) {
        bind(holder, data.get(position), position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public <T extends ISFAp2Base> void  setData(List<T> d){
        data = new ArrayList<>();
        data.addAll(d);
        notifyDataSetChanged();
    }
    public <T extends ISFAp2Base> void  addData(List<T> d){
        if(data == null) data = new ArrayList<>();
        data.addAll(d);
        notifyDataSetChanged();
    }
    public void addData(ISFAp2Base obj){
        if(data == null) data = new ArrayList<>();
        data.add(obj);
        notifyDataSetChanged();
    }
    public List getData(){
        return data;
    }
    
}
