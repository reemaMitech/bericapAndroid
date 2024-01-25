package com.ecs.library;
/**
 * Class Name       :  <b>ThemeSingleton.java<b/>
 * Purpose          :  ThemeSingleton is java class for.
 * Developed By     :  <b>@raghu_android</b>
 * Created Date     :  <b>09.09.2015</b>
 * <p/>
 * Modified Purpose :
 * Modified By      :  <b>@Raghu_android</b>
 * Modified Date    :  <b>00.00.2015</b>
 * Modified Reason  :
 * <p/>
 */
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;


public class ThemeSingleton {

    private static ThemeSingleton singleton;

    public static ThemeSingleton get(boolean createIfNull) {
        if (singleton == null && createIfNull)
            singleton = new ThemeSingleton();
        return singleton;
    }

    public static ThemeSingleton get() {
        return get(true);
    }

    public boolean darkTheme = false;
    @ColorInt
    public int titleColor = 0;
    @ColorInt
    public int contentColor = 0;
    @ColorInt
    public int positiveColor = 0;
    @ColorInt
    public int neutralColor = 0;
    @ColorInt
    public int negativeColor = 0;
    @ColorInt
    public int widgetColor = 0;
    @ColorInt
    public int itemColor = 0;
    public Drawable icon = null;
    @ColorInt
    public int backgroundColor = 0;
    @ColorInt
    public int dividerColor = 0;

    @DrawableRes
    public int listSelector = 0;
    @DrawableRes
    public int btnSelectorStacked = 0;
    @DrawableRes
    public int btnSelectorPositive = 0;
    @DrawableRes
    public int btnSelectorNeutral = 0;
    @DrawableRes
    public int btnSelectorNegative = 0;

    public GravityEnum titleGravity = GravityEnum.START;
    public GravityEnum contentGravity = GravityEnum.START;
    public GravityEnum btnStackedGravity = GravityEnum.END;
    public GravityEnum itemsGravity = GravityEnum.START;
    public GravityEnum buttonsGravity = GravityEnum.START;
}