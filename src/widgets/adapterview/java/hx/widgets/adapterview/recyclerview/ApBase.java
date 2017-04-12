package hx.widgets.adapterview.recyclerview;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hx.widgets.adapterview.IItemClickListener;
import hx.widgets.adapterview.VhBase;

/**
 * Created by rose on 16-8-12.
 */

public abstract class ApBase<Vh extends VhBase<T>, T> extends RecyclerView.Adapter<Vh> {

    final String TAG = "--ApBase--";

    private List<T> mDatas = new ArrayList<T>();
    protected Activity act;
    protected RecyclerView rv;
    protected IItemClickListener<T> mClickListener;

    public abstract Vh getVh(Activity act);
    @CallSuper protected void bind(Vh holder, T data, int position){
        holder.bind(data, position);
    }
    protected void  registerItemClick(IItemClickListener<T> clickListener){
        this.mClickListener = clickListener;
    }

     protected ApBase(Activity act, RecyclerView rv){
        this.act = act;
        this.rv = rv;
    }

    @Override
    public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
        Vh holder = getVh(act);
//        viewType 未做额外处理，此处只作为position用。
//        if(mClickListener != null) holder.itemView.setOnClickListener(view -> mClickListener.onClick(mDatas.get(viewType), viewType));
        return holder;
    }

    @Override
    public void onBindViewHolder(Vh holder, int position) {
        bind(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setData(List<T> datas){
        if(mDatas == null) mDatas = new ArrayList<T>();
        else mDatas.clear();
        mDatas = new ArrayList<>();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void addData(List<T> datas){
        if(mDatas == null) mDatas = new ArrayList<T>();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }
    public List<T> getData(){
        return mDatas;
    }

}
