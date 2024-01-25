package com.ecs.offers.CustomClasses;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.Properties;

public class CustomButton extends Button {

	private Typeface tfFont = null;
	Properties p;

	public CustomButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		applyFont();
	}

	public CustomButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		applyFont();
	}

	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		applyFont();
	}

	public void applyFont() {
		tfFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial.ttf");
		setTypeface(tfFont);
	}
}
