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
		mWebView.setTopContentHeightPercent(0.33f);
		mWebView.setSlideGalleryHeightPercent(0.33f);
		setWebViewDefault(mWebView);
	}

	private void initData() {
		mWebView.loadUrl("http://3g.163.com/touch/");
	}

	/**
	 * WebView 默认设置
	 */
	private void setWebViewDefault(WebView webView) {
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
	}
}
