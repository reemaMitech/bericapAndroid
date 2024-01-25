package com.ecs.library;
/**
 * Class Name       :  <b>GravityEnum.java<b/>
 * Purpose          :  GravityEnum is enum.
 * Developed By     :  <b>@raghu_android</b>
 * Created Date     :  <b>09.09.2015</b>
 * <p/>
 * Modified Purpose :
 * Modified By      :  <b>@Raghu_android</b>
 * Modified Date    :  <b>00.00.2015</b>
 * Modified Reason  :
 * <p/>
 */
import android.os.Build;
import android.view.Gravity;
import android.view.View;

public enum GravityEnum {
    START, CENTER, END;

    private static final boolean HAS_RTL = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;

    public int getGravityInt() {
        switch (this) {
            case START:
                return HAS_RTL ? Gravity.START : Gravity.LEFT;
            case CENTER:
                return Gravity.CENTER_HORIZONTAL;
            case END:
                return HAS_RTL ? Gravity.END : Gravity.RIGHT;
            default:
                throw new IllegalStateException("Invalid gravity constant");
        }
    }

    public int getTextAlignment() {
        switch (this) {
            case CENTER:
                return View.TEXT_ALIGNMENT_CENTER;
            case END:
                return View.TEXT_ALIGNMENT_VIEW_END;
            default:
                return View.TEXT_ALIGNMENT_VIEW_START;
        }
    }
}