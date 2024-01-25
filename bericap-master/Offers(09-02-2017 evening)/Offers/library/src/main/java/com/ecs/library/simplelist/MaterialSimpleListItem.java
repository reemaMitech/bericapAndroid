package com.ecs.library.simplelist;
/**
 * Class Name       :  <b>.java<b/>
 * Purpose          :
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
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;


public class MaterialSimpleListItem {

    private Builder mBuilder;

    private MaterialSimpleListItem(Builder builder) {
        mBuilder = builder;
    }

    public Drawable getIcon() {
        return mBuilder.mIcon;
    }

    public CharSequence getContent() {
        return mBuilder.mContent;
    }

    public static class Builder {

        private Context mContext;
        protected Drawable mIcon;
        protected CharSequence mContent;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder icon(Drawable icon) {
            this.mIcon = icon;
            return this;
        }

        public Builder icon(@DrawableRes int iconRes) {
            return icon(ContextCompat.getDrawable(mContext, iconRes));
        }

        public Builder content(CharSequence content) {
            this.mContent = content;
            return this;
        }

        public Builder content(@StringRes int contentRes) {
            return content(mContext.getString(contentRes));
        }

        public MaterialSimpleListItem build() {
            return new MaterialSimpleListItem(this);
        }
    }

    @Override
    public String toString() {
        if (getContent() != null)
            return getContent().toString();
        else return "(no content)";
    }
}