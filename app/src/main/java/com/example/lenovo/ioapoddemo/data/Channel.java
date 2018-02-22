package com.example.lenovo.ioapoddemo.data;

import org.json.JSONObject;

/**
 * Created by lenovo on 2/21/2018.
 */

public class Channel implements JSONPopulator {
    private Units units;
    private Item items;

    @Override
    public void populator(JSONObject data) {
        units=new Units(data.optJSONObject("units"));
        items=new Item();
        items.populator(data.optJSONObject("item"));
    }
    public Units getUnits(){
        return units;
    }
    public Item getItem(){
        return items;
    }
}
