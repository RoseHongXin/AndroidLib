package hx.ui.widgets.adapterview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rose on 16-8-12.
 *
 * Based on item position as the viewType
 *
 */

public abstract class ApBase2 extends RecyclerView.Adapter<VhBase> {

    final String TAG = "--ApBase2--";

    protected Activity act;
    protected RecyclerView rv;
    private List<Object> data = new ArrayList<Object>();

    protected abstract VhBase getVh(Activity act, int position);
    protected abstract void bind(VhBase holder, Object data, int position);

     protected ApBase2(Activity act, RecyclerView rv){
         this.act = act;
         this.rv = rv;
    }

    @Override
    public VhBase onCreateViewHolder(ViewGroup parent, int viewType) {
        return getVh(act, viewType);
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

    public void setData(List<Object> d){
        /*if(data == null) data = new ArrayList<T>();
        else data.clear();*/
        data = new ArrayList<>();
        data.addAll(d);
        notifyDataSetChanged();
    }
    public void addData(List<Object> d){
        if(data == null) data = new ArrayList<Object>();
        data.addAll(d);
        notifyDataSetChanged();
    }
    public void addData(Object obj){
        if(data == null) data = new ArrayList<Object>();
        data.add(obj);
        notifyDataSetChanged();
    }
    public List<Object> getData(){
        return data;
    }
    
}
