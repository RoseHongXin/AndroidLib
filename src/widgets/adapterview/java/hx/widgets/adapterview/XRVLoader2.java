package hx.widgets.adapterview;

import android.app.Activity;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import hx.req.IReqConstant;

/**
 * Created by rose on 16-8-12.
 */

public class XRVLoader2 extends RvLoaderBaseAp2 {

    XRecyclerView _rv;

    public XRVLoader2(Activity act, XRecyclerView _rv, Ap2Base adapter) {
        super(act, _rv, adapter);
        this._rv = _rv;
    }

    @Override
    protected RvLoaderBaseAp2 init(IReq3 reqHandler) {
        XRecyclerView.LoadingListener listener = new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                reqHandler.getReqTrigger(true)
                        .doOnCompleted(() -> {
                            _rv.postDelayed(_rv::refreshComplete, IReqConstant.REFRESH_TIME_THRESHOLD);
                        })
                        .subscribe();
            }
            @Override
            public void onLoadMore() {
                reqHandler.getReqTrigger(false)
                        .doOnCompleted(() -> {
                            _rv.postDelayed(_rv::loadMoreComplete, IReqConstant.REFRESH_TIME_THRESHOLD);
                        })
                        .subscribe();
            }
        };
        _rv.setLoadingListener(listener);
        return this;
    }
}
