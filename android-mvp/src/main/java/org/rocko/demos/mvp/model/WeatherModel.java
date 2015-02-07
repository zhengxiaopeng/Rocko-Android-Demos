package org.rocko.demos.mvp.model;

import org.rocko.demos.mvp.presenter.OnWeatherListener;

/**
 * Created by Administrator on 2015/2/6.
 * 天气Model接口
 */
public interface WeatherModel {
    void loadWeather(String cityNO, OnWeatherListener listener);
}
