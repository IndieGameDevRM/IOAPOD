package com.example.lenovo.ioapoddemo.data;

import org.json.JSONObject;

/**
 * Created by lenovo on 2/21/2018.
 */

public class Units implements JSONPopulator {
   private String temperature;
    public Units(JSONObject units) {
    }

    @Override
    public void populator(JSONObject data) {
    temperature=data.optString("temperature");

    }
    public String getTemperature(){
     return temperature;
    }
}
