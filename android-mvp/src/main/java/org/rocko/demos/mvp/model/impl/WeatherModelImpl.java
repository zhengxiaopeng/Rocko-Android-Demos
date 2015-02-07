package org.rocko.demos.mvp.model.impl;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.rocko.demos.mvp.model.WeatherModel;
import org.rocko.demos.mvp.model.entity.Weather;
import org.rocko.demos.mvp.presenter.OnWeatherListener;
import org.rocko.demos.mvp.util.volley.VolleyRequest;

/**
 * Created by Administrator on 2015/2/6.
 * 天气Model实现
 */
public class WeatherModelImpl implements WeatherModel {
    @Override
    public void loadWeather(String cityNO, final OnWeatherListener listener) {
        /*数据层操作*/
        VolleyRequest.newInstance().newGsonRequest("http://www.weather.com.cn/data/sk/" + cityNO + ".html",
                Weather.class, new Response.Listener<Weather>() {
                    @Override
                    public void onResponse(Weather weather) {
                        if (weather != null) {
                            listener.onSuccess(weather);
                        } else {
                            listener.onError();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError();
                    }
                });
    }
}
