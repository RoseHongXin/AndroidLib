package hx.ui.widgets.adapterview;

import hx.req.bean.Pager;
import rx.Observable;

/**
 * Created by Rose on 10/13/2016.
 *
 * get one page data, without paging.
 *
 */

public interface IReq<T> {
    Observable<Pager<T>> get();
}
