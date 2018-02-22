package com.example.lenovo.ioapoddemo.data;

import org.json.JSONObject;

/**
 * Created by lenovo on 2/21/2018.
 */

public class Condition implements JSONPopulator {
    private int code;
    private int temperature;
    private String description;
    @Override
    public void populator(JSONObject data) {
        code=data.optInt("code");
        temperature=data.optInt("temp");
        description=data.optString("text");

    }
    public int getCode(){
        return code;
    }
    public int getTemperature(){
        return  temperature;
    }

    public String getDescription() {
        return description;
    }
}
