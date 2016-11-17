package hx.ui.widgets.adapterview;

import hx.req.bean.Pager;
import rx.Observable;

/**
 * Created by Rose on 11/17/2016.
 */

@Deprecated
public interface IReqHandler1<T> {
    Observable<Pager<T>> getReqApi(int page);
    void onRefresh();
}
