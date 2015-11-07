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
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.webkit.WebView;

import com.rocko.wwv.R;


/**
 * 在水平方向上有滑动事件的 WebView，目前只支持一处有 slide。
 *
 * @author: Rocko
 * @date 2015-09-29
 */
public class HorizontalSlideWebView extends WebView {

	private static final String TAG = HorizontalSlideWebView.class.getSimpleName();

	/**
	 * 控件自身高度
	 */
	private float mHeight;

	private float mTopContentHeightPercent = 0.00f;

	private float mGalleryHeightPercent = 0.30f;
	/**
	 * 顶部滑动区域高度
	 */
	private float mSlideGalleryHeight;
	/**
	 * 滑动区域顶部内容所占页面高度，没有则为 0
	 */
	private float mTopContentHeight;

	/**
	 * 当前滑动区域可见高度
	 */
	private float currentVisiableGalleryHeight;

	public HorizontalSlideWebView(Context context) {
		this(context, null);
	}

	public HorizontalSlideWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	private void init(AttributeSet attrs) {
		TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HorizontalSlideWebView);
		mGalleryHeightPercent = typedArray.getFloat(R.styleable.HorizontalSlideWebView_slide_content_height_percent, mGalleryHeightPercent);
		mTopContentHeightPercent = typedArray.getFloat(R.styleable.HorizontalSlideWebView_top_content_height_percent, mTopContentHeightPercent);
		typedArray.recycle();
		getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				getViewTreeObserver().removeGlobalOnLayoutListener(this); // First
				mHeight = getHeight();
				mSlideGalleryHeight = mHeight * mGalleryHeightPercent;
				mTopContentHeight = mHeight * mTopContentHeightPercent;
				currentVisiableGalleryHeight = mSlideGalleryHeight;
				Log.d(TAG, "mSlideGalleryHeight: " + mSlideGalleryHeight + " mTopContentHeight； " + mTopContentHeight + " currentVisiableGalleryHeight: " + currentVisiableGalleryHeight);
			}
		});
	}

	/**
	 * 设置滑动区域顶部内容所占页面高度百分比，没有则为 0
	 */
	public void setTopContentHeightPercent(float mTopContentHeightPercent) {
		this.mTopContentHeightPercent = mTopContentHeightPercent;
		this.mTopContentHeight = mHeight * mTopContentHeightPercent;
	}

	/**
	 * 设置顶部滑动区域高度百分比
	 */
	public void setSlideGalleryHeightPercent(float mGalleryHeightPercent) {
		this.mGalleryHeightPercent = mGalleryHeightPercent;
		this.mTopContentHeight = mHeight * mGalleryHeightPercent;
	}

	/**
	 * 设置滑动区域顶部内容所占页面高度，没有则为 0
	 */
	public void setTopContentHeight(float mTopContentHeight) {
		this.mTopContentHeight = mTopContentHeight;
	}

	/**
	 * 设置顶部滑动区域高度
	 */
	public void setSlideGalleryHeight(float mSlideGalleryHeight) {
		this.mSlideGalleryHeight = mSlideGalleryHeight;
	}

	@Override
	public boolean canScrollHorizontally(int direction) { // 不再兼容 API < 14
		int verticalScrollOffset = computeVerticalScrollOffset();
//		Log.d(TAG, "Offset: " + computeVerticalScrollOffset() + " Extent:" + computeVerticalScrollExtent() + " Range:" + computeVerticalScrollRange());
//		Log.d(TAG, "currentVisiableGalleryHeight: " + currentVisiableGalleryHeight);
//		Log.d(TAG, "direction: " + direction);
		if (verticalScrollOffset < mTopContentHeight) { // TopContent 还未完全偏移出去
			Log.i(TAG, "TopContent 可见");
			return (direction > mTopContentHeight - verticalScrollOffset) && (direction < mSlideGalleryHeight + verticalScrollOffset);
		}
		else { // TopContent 已经偏移出去，包括 mTopContentHeight为 0
			Log.i(TAG, "TopContent 不可见");
			if (verticalScrollOffset < mSlideGalleryHeight + mTopContentHeight) { // GalleryContent 可见
				Log.i(TAG, "GalleryContent 可见");
				currentVisiableGalleryHeight = mSlideGalleryHeight - (verticalScrollOffset - mTopContentHeight);
			}
			else { // GalleryContent 已经不可见
				Log.i(TAG, "GalleryContent 不可见");
				currentVisiableGalleryHeight = mSlideGalleryHeight; // 重置
			}
			return (verticalScrollOffset < mSlideGalleryHeight + mTopContentHeight) && direction < currentVisiableGalleryHeight;
		}
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		return true;
	}
}
