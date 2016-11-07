package hx.ui.components;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import hx.global.R;

/**
 * Created by rose on 16-8-12.
 */

public abstract class ABase extends AppCompatActivity {

    SwipeRefreshLayout _srl;
    IRefreshCallback refreshCallback;

    @LayoutRes
    public abstract int _getLayoutRes();
    public abstract IRefreshCallback _registerRefreshCallback();
    //acting more like boolean, if null, then there would be no refresh widget.
    public abstract Toolbar _askForToolbar();

    public SwipeRefreshLayout getSwipeRefreshLayout(){
        return _srl;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(getLayoutRes());

        View layout = getLayoutInflater().inflate(_getLayoutRes(), (ViewGroup) findViewById(android.R.id.content), false);
//        View layout = getLayoutInflater().inflate(_getLayoutRes(), null);

        refreshCallback = _registerRefreshCallback();
        if(refreshCallback != null){
            _srl = new SwipeRefreshLayout(getApplicationContext());
            _srl.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            _srl.addView(layout);
            _srl.setOnRefreshListener(() -> refreshCallback.onRefresh());
            layout = _srl;
        }

        Toolbar _tb = _askForToolbar();
        if(_tb != null){
            //setSupportActionBar(_tb);
            LinearLayout content = new LinearLayout(getApplicationContext());
            content.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            content.setOrientation(LinearLayout.VERTICAL);

            TypedValue typedValue = new TypedValue();
            getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            int color = typedValue.data;
            _tb.setBackgroundColor(color);
            content.addView(_tb);
            content.addView(layout);
            setContentView(content);
        }else{
            setContentView(layout);
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(_srl != null && _srl.isRefreshing()) _stopRefresh();
    }

    protected void _disableRefresh(){
        _srl.setEnabled(false);
        //_srl.setRefreshing(false);
    }
    protected void _retoreRefresh(){
        _srl.setEnabled(true);
    }
    protected void _stopRefresh(){
        if(_srl != null) _srl.setRefreshing(false);
    }
    protected void _refresh(){
        //if(_srl != null) _srl.setRefreshing(true);
        //_srl.setProgressViewOffset(false, 0, 100);
        //_srl.setRefreshing(true);
        if(_srl == null) return;
        _srl.post(() -> {
            if(refreshCallback != null){
                _srl.setRefreshing(true);
                refreshCallback.onRefresh();
            }
        });
    }

}
