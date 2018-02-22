package com.example.lenovo.ioapoddemo.Service;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.lenovo.ioapoddemo.data.Channel;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class YahooWeatherService  {
   private WeatherServiceCallback callback;
    private Exception e;
    private String _Location="India,Faridabad";
    public YahooWeatherService(WeatherServiceCallback _callback){
        this.callback=_callback;

    }



    public String getLocation(){
        return _Location;
    }
    public void refreshWeather(final String Location){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String YQL=String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")",Location);
                String endpoint=String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));
                try {
                    URL url=new URL(endpoint);
                    URLConnection connection=url.openConnection();
                    InputStream inputstream= connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(inputstream));
                    StringBuilder result=new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        //thats means it have some content to read
                        result.append(line);
                    }
                    return result.toString();
                }catch (Exception e1) {
                    e=e1;
                }
                return null;
            }
            @Override
            protected void onPostExecute(String s){
                try {

                    JSONObject data =new JSONObject(s);
                    JSONObject queryresult=data.optJSONObject("query");
                    int count=queryresult.optInt("count");

                    if(count==0){
                        callback.servicefailed(new LocationWeatherException("no Information Found for "+Location));
                    }
                    if(s==null && e!=null ){
                        callback.servicefailed(e);
                        return;
                    }
                    Channel channel=new Channel();
                    channel.populator(queryresult.optJSONObject("results").optJSONObject("channel"));
                    callback.servicesuccess(channel);
                } catch (JSONException e1) {
                    callback.servicefailed(e1);
                }
            }
        }.execute(Location);

    }
    public class LocationWeatherException extends Exception{
        public LocationWeatherException(String Detailmessage){
            super(Detailmessage);
        }
    }

}
