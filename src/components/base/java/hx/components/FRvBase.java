package hx.components;

import android.view.View;

/**
 * Created by Rose on 3/1/2017.
 */

public class FRvBase extends FBase {


    @Override
    public int _getLayoutRes() {
        return R.layout.f_rv_base;
    }

    @Override
    public View _getTitleBar() {
        return null;
    }
}
