package hx.toolkit;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * Created by Rose on 3/29/2017.
 */

public class OSUtils {

    public static int getVersionCode(Context ctx){
        try {
            PackageInfo pi = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            return pi.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getVersionName(Context ctx){
        try {
            PackageInfo pi= ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            return pi.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "unknown";
        }
    }

}
