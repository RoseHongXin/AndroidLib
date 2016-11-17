package hx.ui.widgets.adapterview;

import hx.req.bean.Pager;
import rx.Observable;

/**
 * Created by Rose on 10/13/2016.
 */

public interface IReqApi<T> {

    Observable<Pager<T>> get();
}
