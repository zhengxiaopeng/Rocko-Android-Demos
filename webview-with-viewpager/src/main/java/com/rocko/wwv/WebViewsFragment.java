package com.rocko.wwv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.rocko.wwv.widget.HorizontalSlideWebView;

/**
 * @author: Rocko
 */
public class WebViewsFragment extends Fragment {

	private static final String TAG = WebViewsFragment.class.getSimpleName();

	protected HorizontalSlideWebView mWebView;

	private int index;

	public WebViewsFragment() {

	}

	public WebViewsFragment(int index) {
		this.index = index;
	}

	public static WebViewsFragment newInstance(int index) {
		return new WebViewsFragment(index);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View contentView = inflater.inflate(R.layout.webview_horizontalslide, container, false);
		initWidget(contentView);
		initData();
		return contentView;
	}

	private void initWidget(View contentView) {
		mWebView = (HorizontalSlideWebView) contentView.findViewById(R.id.webview_horizontal);
		setWebViewDefault(mWebView);
		switch (index) {
			case 1:
				mWebView.setTopContentHeightPercent(1f/3);
				mWebView.setSlideGalleryHeightPercent(1f/3);
				break;
			case 2:
				mWebView.setTopContentHeightPercent(1f/7);
				mWebView.setSlideGalleryHeightPercent(2.8f/7);
				break;
			case 3:
				mWebView.setTopContentHeightPercent(1f/7);
				mWebView.setSlideGalleryHeightPercent(2.8f/7);
				break;
		}

	}

	private void initData() {
		String url = "";
		switch (index) {
			case 1:
				url = "http://i.ifeng.com/";
				break;
			case 2:
				url = "http://i.ifeng.com/";
				break;
			case 3:
				url = "http://i.ifeng.com/";
				break;
		}
		mWebView.loadUrl(url);
	}

	/**
	 * WebView 默认设置
	 */
	private void setWebViewDefault(WebView webView) {
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
	}
}
