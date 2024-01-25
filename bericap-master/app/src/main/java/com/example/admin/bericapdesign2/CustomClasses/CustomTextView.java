package com.example.admin.bericapdesign2.CustomClasses;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Properties;

public class CustomTextView extends TextView {

	private Typeface tfFont = null;
	Properties p;

	public CustomTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		tfFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/CALIBRI.TTF");
	}

	public void setProperyKey(String sKey) {
		setText(p.getProperty(sKey));
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		// TODO Auto-generated method stub
		super.setText(text, type);
	}

	@Override
	public void setGravity(int gravity) {
		// TODO Auto-generated method stub
		super.setGravity(gravity);
	}

	@Override
	public void setTypeface(Typeface tf, int style) {
		// TODO Auto-generated method stub
		tf = tfFont;
		if (style == Typeface.BOLD) {
			super.setTypeface(tf, Typeface.BOLD);
		} else if (style == Typeface.ITALIC) {
			super.setTypeface(tf, Typeface.ITALIC);
		} else if (style == Typeface.BOLD_ITALIC) {
			super.setTypeface(tf, Typeface.BOLD_ITALIC);
		} else {
			super.setTypeface(tf, Typeface.NORMAL);
		}
	}
}