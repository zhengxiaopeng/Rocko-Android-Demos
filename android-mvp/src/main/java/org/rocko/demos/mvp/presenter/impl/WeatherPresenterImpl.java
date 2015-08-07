package org.rocko.demos.mvp.presenter.impl;

import org.rocko.demos.mvp.model.WeatherModel;
import org.rocko.demos.mvp.model.entity.Weather;
import org.rocko.demos.mvp.model.impl.WeatherModelImpl;
import org.rocko.demos.mvp.presenter.OnWeatherListener;
import org.rocko.demos.mvp.presenter.WeatherPresenter;
import org.rocko.demos.mvp.ui.view.WeatherView;

/**
 * Created by Administrator on 2015/2/6.
 * 天气 Prestener实现
 */
public class WeatherPresenterImpl implements WeatherPresenter, OnWeatherListener {
	/*Presenter作为中间层，持有View和Model的引用*/
	private WeatherView weatherView;
	private WeatherModel weatherModel;

	public WeatherPresenterImpl(WeatherView weatherView) {
		this.weatherView = weatherView;
		weatherModel = new WeatherModelImpl();
	}

	@Override
	public void getWeather(String cityNO) {
		weatherView.showLoading();
		weatherModel.loadWeather(cityNO, this);
	}

	@Override
	public void onSuccess(Weather weather) {
		weatherView.hideLoading();
		weatherView.setWeatherInfo(weather);
	}

	@Override
	public void onError() {
		weatherView.hideLoading();
		weatherView.showError();
	}
}
