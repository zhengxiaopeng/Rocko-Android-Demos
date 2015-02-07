package org.rocko.demos.mvp.ui.view;

import org.rocko.demos.mvp.model.entity.Weather;

/**
 * Created by Administrator on 2015/2/7.
 */
public interface WeatherView {
    void showLoading();

    void hideLoading();

    void showError();

    void setWeatherInfo(Weather weather);
}
