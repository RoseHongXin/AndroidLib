package hx.ui.widgets.adapterview;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.IntegerRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rose on 16-8-12.
 *
 * Based on item position as the viewType
 *
 */

public abstract class ApBase2Nothing extends RecyclerView.Adapter<VhBase> {

    final String TAG = "--ApBase2Nothing--";

    protected Activity act;
    protected RecyclerView rv;
    private List<I2Data> datas = new ArrayList<I2Data>();
    private List<ApVhBridge> bridges = new ArrayList<ApVhBridge>();
    private int itemCount;

//    protected abstract <T extends I2Data> VhBase2<T> getVh(Activity act, int position);
    protected abstract ApVhBridge getVh(Activity act, int position);
    protected abstract <T extends I2Data> void bind(VhBase2<T> holder, T data, int position);
    /*@CallSuper protected <T extends I2Data> void bind(VhBase2<T> holder, T data, int position){
        holder.bind(data, position);
    }*/

     protected ApBase2Nothing(Activity act, RecyclerView rv){
         this.act = act;
         this.rv = rv;
//         this.datas = datas;
//         this.holders = holders;
    }

    @Override
    public VhBase onCreateViewHolder(ViewGroup parent, int viewType) {
        ApVhBridge bridge = getVh(act, viewType);
        if(bridges.isEmpty()) bridges.add(bridge);
        else{
            if(bridges.size() > viewType) bridges.add(bridge);
            else bridges.set(viewType, bridge);
        }
        return bridge.getHolder();
    }

    @Override
    public void onBindViewHolder(VhBase holder, int position) {
        ApVhBridge bridge = bridges.get(position);
        bridge.bind(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return bridges.size();
    }

    public <T, Vh extends VhBase<T>> void  addData(T data, Vh holder){
        ApVhBridge<Vh, T> bridge = new ApVhBridge<Vh, T>();
        bridge.setData(data);
        bridge.setHolder(holder);
        bridges.add(bridge);
    }
}
