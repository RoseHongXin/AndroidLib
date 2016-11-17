package hx.ui.widgets.adapterview;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by rose on 16-9-9.
 */
public class VhBase2 extends RecyclerView.ViewHolder {

    protected Activity act;
    protected Object data;
    protected int position;

    public VhBase2(Activity act, @LayoutRes int layoutRes){
        this(act.getLayoutInflater().inflate(layoutRes, null));
        this.act = act;
    }
    private VhBase2(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @CallSuper
    public void bind(Object data, int position){
        this.data = data;
        this.position = position;
    }
}
