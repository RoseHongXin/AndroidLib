package hx.components;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.util.List;
import hx.lib.R;
import hx.widget.adapterview.VhBase;
import hx.widget.adapterview.recyclerview.ApBase;
import hx.widget.adapterview.recyclerview.XRvLoader;
import rx.Observable;

/**
 * Created by Rose on 3/1/2017.
 */

public abstract class FBaseRv<Ap extends ApBase<Vh, T>, Vh extends VhBase<T>, T> extends FBase {

    protected XRecyclerView _rv_;

    protected XRvLoader mRvLoader;

    public abstract Observable<List<T>> getApi(int page);
    public abstract Ap getAdapter();

    @Override
    public int sGetLayoutRes() {
        return R.layout.f_base_rv;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _rv_ = (XRecyclerView)view.findViewById(R.id._rv_);
        if(mRvLoader == null){
            Ap mAdapter = getAdapter();
            mRvLoader = new XRvLoader<Ap, Vh, T>().init(getActivity(), _rv_, mAdapter, this::getApi);
        }
        sRegisterRefreshCb(() -> mRvLoader.doRefresh());
    }
}
