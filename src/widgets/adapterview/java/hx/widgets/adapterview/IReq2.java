package hx.widgets.adapterview;

import hx.req.bean.Pager;
import rx.Observable;

/**
 * Created by Rose on 10/13/2016.
 *
 * paging.
 *
 */

public interface IReq2<T> {

    Observable<Pager<T>> get(int page);

}
