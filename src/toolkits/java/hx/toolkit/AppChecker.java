package hx.toolkit;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class AppChecker {

     public static boolean isActForeground(Activity act) {
         ActivityManager am = (ActivityManager) act.getSystemService(Context.ACTIVITY_SERVICE);
         if(!isAppForeground(act, am)) return false;
         int taskId = act.getTaskId();
         String actName = act.getClass().getName();
         String packageName = act.getPackageName();
         if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.M) {
             List<ActivityManager.AppTask> tasks = am.getAppTasks();
             for(ActivityManager.AppTask task : tasks) {
                 ActivityManager.RecentTaskInfo info = task.getTaskInfo();
                 if(info.affiliatedTaskId == taskId && info.topActivity.getClassName().equals(actName) && info.topActivity.getPackageName().equals(packageName)) return true;
             }
         }else {
             List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
             if(tasks != null && !tasks.isEmpty() && tasks.get(0).topActivity.getClassName().equals(actName)) return true;
         }
        return false;
    }
    public static boolean isAppForeground(Context ctx){
        if (ctx == null) return false;
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        return isAppForeground(ctx, am);
    }
    private static boolean isAppForeground(Context ctx, ActivityManager activityManager){
        if (ctx == null) return false;
        List<ActivityManager.RunningAppProcessInfo> processes = activityManager.getRunningAppProcesses();
        if(processes == null || processes.isEmpty()) return false;
        String packageName = ctx.getPackageName();
        for(ActivityManager.RunningAppProcessInfo process : processes){
            if(process.processName.equals(packageName) && process.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) return true;
        }
        return false;
    }


}
