package com.example.lenovo.ioapoddemo;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.ioapoddemo.Service.WeatherServiceCallback;
import com.example.lenovo.ioapoddemo.Service.YahooWeatherService;
import com.example.lenovo.ioapoddemo.data.Channel;
import com.example.lenovo.ioapoddemo.data.Item;

import org.w3c.dom.Text;

public class WeatherActivity extends AppCompatActivity implements WeatherServiceCallback {
    private ImageView weatherimageview;
    private TextView weathertemp;
    private TextView weathercond;
    private TextView weatherloc;
    private YahooWeatherService service;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather);
        dialog=new ProgressDialog(WeatherActivity.this);
        weatherimageview=(ImageView)findViewById(R.id.weatherimg);
        weathertemp=(TextView)findViewById(R.id.weathertemp);
        weathercond=(TextView)findViewById(R.id.weathercondition);
        weatherloc=(TextView)findViewById(R.id.weatherlocation);
        service=new YahooWeatherService(this);

        dialog.setMessage("loading..");

        service.refreshWeather("India ,Faridabad");
    }

    @Override
    public void servicesuccess(Channel channel) {
        dialog.dismiss();
        int resourceid=getResources().getIdentifier("drawable/icon_"+channel.getItem().getcondition().getCode(),null,getPackageName());
        @SuppressWarnings("deprecation")
        Drawable WeatherIconDrawable=getResources().getDrawable(resourceid);
        weatherimageview.setImageDrawable(WeatherIconDrawable);
        Item item=channel.getItem();
        weatherloc.setText(service.getLocation());
        weathertemp.setText(item.getcondition().getTemperature()+"\u00B0 "+channel.getUnits().getTemperature());
        weathercond.setText(item.getcondition().getDescription());
    }

    @Override
    public void servicefailed(Exception exception) {
        dialog.dismiss();
        Toast.makeText(WeatherActivity.this,exception.getMessage(),Toast.LENGTH_LONG).show();

    }
}
