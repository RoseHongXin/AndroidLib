package hx.ui.widgets.adapterview;

/**
 * Created by Rose on 11/17/2016.
 */
public class ApVhBridge<Vh extends VhBase<T>, T> {

    Vh holder;
    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public VhBase<T> getHolder() {
        return holder;
    }

    public void setHolder(Vh holder) {
        this.holder = holder;
    }

    public void bind(int position){
        holder.bind(data, position);
    }
}
