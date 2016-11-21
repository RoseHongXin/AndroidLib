package hx.ui.widgets.adapterview;

import hx.req.bean.Pager;
import rx.Observable;

/**
 * Created by Rose on 11/17/2016.
 *
 * with basic paging request. and extra refresh callback and complete callback when paging api request finished.
 *
 */
public interface IReq4<T> {
    Observable<Pager<T>> getReqApi(int page);
    void onRefresh();
    void onComplete();
}
