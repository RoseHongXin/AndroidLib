package hx.components;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by rose on 16-8-11.
 */

public abstract class FBase extends Fragment {

    @LayoutRes public abstract int _getLayoutRes();
    public abstract View _getTitleBar();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(_getLayoutRes(), container, false);
        View titleBar = _getTitleBar();
        if(titleBar != null){
            LinearLayout content = new LinearLayout(getContext());
            content.setOrientation(LinearLayout.VERTICAL);
            content.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            content.addView(titleBar);
            content.addView(layout);
            layout = content;
        }
        return layout;
    }
}
