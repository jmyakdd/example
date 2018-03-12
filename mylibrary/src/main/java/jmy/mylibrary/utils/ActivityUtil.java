package jmy.mylibrary.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by admin on 2016/10/9.
 * activity管理类
 */
public class ActivityUtil {
    private static Stack<Activity> list = new Stack<>();

    public static void addActivity(Activity activity) {
        list.add(activity);
    }

    public static void removeActivity(Activity activity) {
        list.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : list) {
            if (activity.isFinishing()) {

            } else {
                activity.finish();
            }
        }
        list.clear();
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null && list.contains(activity)) {
            list.remove(activity);
            activity.finish();
        }
    }

    public static void finishAllExcept(Class activityClass) {
        for (Activity activity : list) {
            if (activity.getClass().isAssignableFrom(activityClass)) {
                continue;
            }
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}