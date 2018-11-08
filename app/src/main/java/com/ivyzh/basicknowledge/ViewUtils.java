package com.ivyzh.basicknowledge;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.ivyzh.basicknowledge.annotation.ViewById;

import java.lang.reflect.Field;

/**
 * Created by Ivy on 2018/11/8.
 */

public class ViewUtils {
    final static String TAG = "ViewUtils";

    public static void bind(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            ViewById annotation = f.getAnnotation(ViewById.class);
            if (annotation != null) {
                Log.e(TAG, f.getName() + "有注解");

                View v = activity.findViewById(annotation.value());
                f.setAccessible(true);
                try {
                    f.set(activity, v);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e(TAG, f.getName() + "没有注解");
            }
        }

    }

    public static void unBind(Activity activity) {

    }
}
