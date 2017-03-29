package hx.components;

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

    @LayoutRes
    public abstract int _getLayoutRes();
    public abstract View _askForToolbar();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(getLayoutRes());

        View layout = getLayoutInflater().inflate(_getLayoutRes(), (ViewGroup) findViewById(android.R.id.content), false);
//        View layout = getLayoutInflater().inflate(_getLayoutRes(), null);

        View _tb = _askForToolbar();
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

}
