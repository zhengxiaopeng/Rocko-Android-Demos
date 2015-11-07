/**
 * Copyright 2015 Rocko(rocko.xyz) <rocko.zxp@gmail.com>
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rocko.wwv.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * ViewPager 里嵌套 WebView
 * @author: Rocko
 * @date 2015-9-29
 */
public class WebViewPager extends ViewPager {

	private static final String TAG = WebViewPager.class.getSimpleName();

	public WebViewPager(Context context) {
		this(context, null);
	}

	public WebViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		postDelayed(new Runnable() {
			@Override
			public void run() {
				requestDisallowInterceptTouchEvent(true);
				getParent().requestDisallowInterceptTouchEvent(true);
			}
		}, 3000);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev);
	}

		@Override
		protected boolean canScroll(View webView, boolean checkV, int dx, int x, int y) {
			if (webView instanceof HorizontalSlideWebView) {
//			Log.d(TAG, "dx: " + dx + " x:" + x + " y:" + y);
				return webView.canScrollHorizontally(y); // 不再兼容 API < 14
			} else {
				return super.canScroll(webView, checkV, dx, x, y);
			}
		}


	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}
}
