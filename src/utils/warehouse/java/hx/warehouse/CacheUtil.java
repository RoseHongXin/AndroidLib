package hx.warehouse;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hx.util.widgets.Jackson;

/**
 * Created by rose on 16-8-9.
 *
 *
 * use framework disklrucache and jackson
 *
 * for sort of caching stuff.
 *
 */
public class CacheUtil {

    final int VALUE_COUNT = 1;
    final int MAX_SIZE = VALUE_COUNT * 1024 * 1024;

    static final int IDX = 0;

    static DiskLruCache cache;

    static CacheUtil _this;

    private CacheUtil(Context ctx){
        int appVersion = 0;
        try {
            PackageInfo pi = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            appVersion = pi.versionCode;
            //appVersion = Build.VERSION.SDK_INT;
            //appVersion = android.os.Build.VERSION_CODES.
            cache = DiskLruCache.open(ctx.getCacheDir(), appVersion, VALUE_COUNT, MAX_SIZE);
            //KEY = ctx.getPackageName();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            cache = null;
        } catch (IOException e) {
            e.printStackTrace();
            cache = null;
        }
    }

    /**
     *
     * Call this method at the app entry.
     * get(...) return generic java bean class
     * getList(...) return List data.
     *
     *
     * */
    public static CacheUtil init(Context ctx){
        _this = new CacheUtil(ctx);
        return _this;
    }

    public static <T> boolean write(String key, T data){
        if(cache == null) return false;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String str = mapper.writeValueAsString(data);
            //OutputStream os = cache.edit(key).newOutputStream(IDX);
            DiskLruCache.Editor editor = cache.edit(key);
            editor.set(IDX, str);
            editor.commit();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static <T> T get(String key, Class<T> clz){
        T data = null;
        try {
            DiskLruCache.Snapshot snap = cache.get(key);
            if(snap == null) return null;
            String str = snap.getString(IDX);
            if(TextUtils.isEmpty(str)) return null;
//            ObjectMapper mapper = new ObjectMapper();
//            data = mapper.readValue(str, clz);
            data = Jackson.fromStr(str, clz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static <T> List<T> getList(String key, Class<T> clz){
        List<T> data = new ArrayList<T>();
        try {
            DiskLruCache.Snapshot snap = cache.get(key);
            if(snap == null) return data;       //empty array
            String str = snap.getString(IDX);
            if(TextUtils.isEmpty(str)) return data; //empty array
//            ObjectMapper mapper = new ObjectMapper();
//            data = mapper.readValue(str, new TypeReference<List<T>>(){});
            data = Jackson.fromStr2List(str, clz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(data == null) data = new ArrayList<T>();
        return data;
    }
}
