package hx.warehouse;

import android.content.Context;
import android.content.SharedPreferences;

import hx.util.widgets.Jackson;

public class SpUtil {

    static private SharedPreferences mSp;

    public SpUtil(Context ctx){
        init(ctx);
    }

    public static void init(Context ctx){
        mSp = ctx.getSharedPreferences(ctx.getPackageName(), Context.MODE_PRIVATE);
    }

    public static String get(String key){
        if(mSp == null) return "";
        return mSp.getString(key, "");
    }

    public static void set(String key, String data){
        if(mSp != null) mSp.edit().putString(key, data).apply();
        /*if(mSp != null){
            mSp.edit().putString(key, data).apply();
            return true;
        }
        return false;*/
    }
    public static <T> void set(String key, T t){
        String data = Jackson.toStr(t);
        if(mSp != null) mSp.edit().putString(key, data).apply();
    }

    public void clear(String key) {
        if (mSp != null && mSp.contains(key)) {
            mSp.edit().putString(key, "").apply();
        }
    }

}
