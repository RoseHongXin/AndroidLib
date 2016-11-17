package hx.ui.widgets.adapterview;

import hx.req.bean.Pager;
import rx.Observable;

/**
 * Created by Rose on 11/17/2016.
 */
public interface IReqHandler<T> {
    Observable<Pager<T>> getReqApi(int page);
    void onRefresh();
    void onComplete();
}
