package hx.ui.widgets.adapterview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rose on 16-8-12.
 */

public abstract class ApBase<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    List<T> data = new ArrayList<T>();

    public abstract VH getVH();
    public abstract void bind(VH holder, T data);
    public void bind(VH holder, T data, int position){
        bind(holder, data);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return getVH();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        bind(holder, data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<T> d){
        /*if(data == null) data = new ArrayList<T>();
        else data.clear();*/
        data = new ArrayList<>();
        data.addAll(d);
        notifyDataSetChanged();
    }

    public void addData(List<T> d){
        if(data == null) data = new ArrayList<T>();
        data.addAll(d);
        notifyDataSetChanged();
    }
    public List<T> getData(){
        return data;
    }

}
