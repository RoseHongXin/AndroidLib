package hx.widgets.adapterview.listview;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hx.widgets.adapterview.IItemClickListener;
import hx.widgets.adapterview.VhBase;

/**
 * Created by Rose on 4/6/2017.
 */

public abstract class ApBase<Vh extends VhBase<T>, T> extends BaseAdapter {

    final String TAG = "--ApBase--";

    private List<T> mDatas = new ArrayList<T>();
    protected Activity act;
    protected ListView lv;
    protected IItemClickListener<T> mClickListener;

    public abstract Vh getVh(Activity act);
    @CallSuper
    protected void bind(Vh holder, T data, int position){
        holder.bind(data, position);
    }
    protected void  registerItemClick(IItemClickListener<T> clickListener){
        this.mClickListener = clickListener;
    }

    protected ApBase(Activity act, ListView lv){
        this.act = act;
        this.lv = lv;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
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
