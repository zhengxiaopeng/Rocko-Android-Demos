package com.rocko.wwv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by rocko on 15-9-28.
 */
public class HorizontalSlideWebView extends WebView {

	public HorizontalSlideWebView(Context context) {
		this(context, null);
	}

	public HorizontalSlideWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean canScrollHorizontally(int direction) {
		return super.canScrollHorizontally(direction);
	}
}
