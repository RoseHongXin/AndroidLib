package hx.ui.widgets.adapterview;

import hx.req.bean.Pager;
import rx.Observable;

/**
 * Created by Rose on 10/13/2016.
 */

public interface IReqApi2<T> {

    Observable<Pager<T>> get(int page);
}
