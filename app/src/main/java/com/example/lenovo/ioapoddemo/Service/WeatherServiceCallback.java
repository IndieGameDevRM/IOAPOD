package com.example.lenovo.ioapoddemo.Service;

import com.example.lenovo.ioapoddemo.data.Channel;

/**
 * Created by lenovo on 2/21/2018.
 */

public interface WeatherServiceCallback {
    void servicesuccess(Channel channel);
    void servicefailed(Exception exception);
}
