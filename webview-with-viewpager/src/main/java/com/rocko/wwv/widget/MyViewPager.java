package com.rocko.wwv.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rocko on 15-9-28.
 */
public class MyViewPager extends ViewPager {

	public MyViewPager(Context context) {
		this(context, null);
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {

		if (v instanceof HorizontalSlideWebView) {
			return v.canScrollHorizontally(dx);
		} else if (v instanceof XYZoomWebView) {
			return v.canScrollHorizontally(dx);
		} else {
			return super.canScroll(v, checkV, dx, x, y);
		}
	}
}
