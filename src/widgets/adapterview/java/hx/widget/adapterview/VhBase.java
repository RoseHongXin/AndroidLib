package hx.widget.adapterview;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by rose on 16-9-9.
 */
public class VhBase<T> extends RecyclerView.ViewHolder {

    protected Activity mAct;
    protected T data;
    protected int position;

    public VhBase(Activity act, @LayoutRes int layoutRes){
        this(act.getLayoutInflater().inflate(layoutRes, null));
        this.mAct = act;
    }
    private VhBase(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @CallSuper
    public void bind(T data, int position){
        this.data = data;
        this.position = position;
    }

    public T getData(){
        return data;
    }
}
