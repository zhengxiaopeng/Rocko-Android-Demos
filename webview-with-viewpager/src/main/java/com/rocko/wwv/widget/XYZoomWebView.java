package com.rocko.wwv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by rocko on 15-9-29.
 */
public class XYZoomWebView extends WebView {

	public XYZoomWebView(Context context) {
		this(context, null);
	}

	public XYZoomWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean canScrollHorizontally(int direction) {
		return super.canScrollHorizontally(direction);
	}
}
