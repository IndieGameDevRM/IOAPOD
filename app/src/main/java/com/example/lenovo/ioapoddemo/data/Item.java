package com.example.lenovo.ioapoddemo.data;

import org.json.JSONObject;

/**
 * Created by lenovo on 2/21/2018.
 */

public class Item implements JSONPopulator {
    private Condition cond;
    @Override
    public void populator(JSONObject data) {
        cond=new Condition();
        cond.populator(data.optJSONObject("condition"));
    }
    public Condition getcondition(){
        return cond;
    }
}
