package hx.ui.widgets.adapterview;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by rose on 16-9-9.
 */
public class VhBase extends RecyclerView.ViewHolder{

    Activity act;

    public VhBase(Activity act, @LayoutRes int layoutRes){
        this(act.getLayoutInflater().inflate(layoutRes, null));
        this.act = act;
    }

    public VhBase(View itemView) {
        super(itemView);
    }
}
