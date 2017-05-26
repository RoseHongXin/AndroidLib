package hx.components;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import hx.lib.R;

/**
 * Created by rose on 16-8-12.
 */

public abstract class ABase extends AppCompatActivity {

    private ViewGroup mLayout;

    @LayoutRes
    public abstract int sGetLayoutRes();
    public abstract View sRequireTb();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(getLayoutRes());

        View layout = getLayoutInflater().inflate(sGetLayoutRes(), (ViewGroup) findViewById(android.R.id.content), false);
//        View layout = getLayoutInflater().inflate(sGetLayoutRes(), null);

        View _tb = sRequireTb();
        if(_tb != null){
            //setSupportActionBar(_tb);
            LinearLayout content = new LinearLayout(getApplicationContext());
            content.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            content.setOrientation(LinearLayout.VERTICAL);

            /*TypedValue typedValue = new TypedValue();
            getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            int color = typedValue.data;
            _tb.setBackgroundColor(color);*/

            content.addView(_tb);
            content.addView(layout);
            setContentView(content);
            mLayout = content;
        }else{
            setContentView(layout);
            mLayout = (ViewGroup) layout;
        }
        ButterKnife.bind(this);
    }

    public ViewGroup sGetLayout(){
        return mLayout;
    }

}
