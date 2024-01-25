package com.ecs.offers.CustomClasses;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.Properties;

public class CustomEditText extends EditText {

    private Typeface tfFont = null;
    Properties p;

    public CustomEditText(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        applyFont();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        applyFont();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        applyFont();
    }

    public void applyFont() {
        tfFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial.ttf");
        setTypeface(tfFont);
    }
}
