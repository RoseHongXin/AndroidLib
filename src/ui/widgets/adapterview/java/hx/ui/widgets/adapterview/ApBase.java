package hx.ui.widgets.adapterview;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rose on 16-8-12.
 */

public abstract class ApBase<Vh extends VhBase<T>, T> extends RecyclerView.Adapter<Vh> {

    final String TAG = "--ApBase--";

    final int SCROLL_FLING_THRESHOLD = 4000;
    private boolean isScrolling = false;

    private List<T> data = new ArrayList<T>();
    protected Activity act;
    protected RecyclerView rv;

    public abstract Vh getVh(Activity act);
    @CallSuper protected void bind(Vh holder, T data, int position){
        holder.bind(data, position);
    }

     protected ApBase(Activity act, RecyclerView rv){
        this.act = act;
        this.rv = rv;
         /*rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
             @Override
             public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                 super.onScrollStateChanged(recyclerView, newState);
                 switch (newState){
                     case RecyclerView.SCROLL_STATE_IDLE:
                         isScrolling = false;
                         Log.d(TAG, "SCROLL_STATE_IDLE : loadData");
                         break;
//                     case RecyclerView.SCROLL_STATE_DRAGGING:
//                     case RecyclerView.SCROLL_STATE_SETTLING:
//                     default:
//                             isScrolling = true;
//                         break;
                 }
             }
//             @Override
//             public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                 super.onScrolled(recyclerView, dx, dy);
//             }
         });*/
         /*rv.setOnFlingListener(new RecyclerView.OnFlingListener() {
             @Override
             public boolean onFling(int velocityX, int velocityY) {
                 isScrolling = Math.abs(velocityY) > SCROLL_FLING_THRESHOLD;
                 Log.d(TAG, "SCROLL_FLING_THRESHOLD : " + isScrolling);
                 return isScrolling;
             }
         });*/
    }

    @Override
    public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
        return getVh(act);
    }

    @Override
    public void onBindViewHolder(Vh holder, int position) {
//        holder.itemView.post(() -> bind(holder, data.get(position), position));
//        if(!isScrolling){
//            holder.itemView.post(() -> bind(holder, data.get(position), position));
//        }
        bind(holder, data.get(position), position);
//        holder.bind(data.get(position), position);
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
