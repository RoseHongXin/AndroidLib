package hx.widgets.adapterview;

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

    protected Activity act;
    protected T data;
    protected int position;

    public VhBase(Activity act, @LayoutRes int layoutRes){
        this(act.getLayoutInflater().inflate(layoutRes, null));
        this.act = act;
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
}
