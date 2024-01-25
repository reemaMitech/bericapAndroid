package com.ecs.library.util;
/**
 * Class Name       :  <b>TypefaceHelper.java<b/>
 * Purpose          :  TypefaceHelper is java class for.
 * Developed By     :  <b>@raghu_android</b>
 * Created Date     :  <b>09.09.2015</b>
 * <p/>
 * Modified Purpose :
 * Modified By      :  <b>@Raghu_android</b>
 * Modified Date    :  <b>00.00.2015</b>
 * Modified Reason  :
 * <p/>
 */
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.SimpleArrayMap;


public class TypefaceHelper {

    private static final SimpleArrayMap<String, Typeface> cache = new SimpleArrayMap<>();

    public static Typeface get(Context c, String name) {
        synchronized (cache) {
            if (!cache.containsKey(name)) {
                try {
                    Typeface t = Typeface.createFromAsset(
                            c.getAssets(), String.format("fonts/%s", name));
                    cache.put(name, t);
                    return t;
                } catch (RuntimeException e) {
                    return null;
                }
            }
            return cache.get(name);
        }
    }
}

