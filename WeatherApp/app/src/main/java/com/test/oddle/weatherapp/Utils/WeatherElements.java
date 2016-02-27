package com.test.oddle.weatherapp.Utils;

import android.widget.ImageView;

import com.test.oddle.weatherapp.APICalls.WeatherDetails;
import com.test.oddle.weatherapp.Model.Weather;
import com.test.oddle.weatherapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Achyut on 2/25/2016.
 */
public class WeatherElements {

    public static Weather getWeatherDetails(String city,String code) {

        String _city = city.replace(" ","_");
        String url = Constants.URL_CONDITION+code+"/"+_city+".json";
        Weather weather = null;


            try {
                weather = new WeatherDetails().execute(url).get();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        return weather;
    }

    public static void setImage(String weatherCondition, ImageView image) {
        if (weatherCondition.contains("Partly Cloudy")){
            image.setImageResource(R.drawable.cloud_1x);
        }else if (weatherCondition.contains("Mostly Cloudy")) {
             image.setImageResource(R.drawable.cloudy_1x);
        }else if(weatherCondition.contains("Overcast")){
            image.setImageResource(R.drawable.very_cloudy_1x);
        }else if (weatherCondition.contains("Clear")){
            image.setImageResource(R.drawable.sun_1x);
        }else if (weatherCondition.contains("Rain")){
           image.setImageResource(R.drawable.rain_1x);
        }else if (weatherCondition.contains("Haze")){
           image.setImageResource(R.drawable.fog_1x);
        }else if (weatherCondition.contains("Wind")){
           image.setImageResource(R.drawable.windy_1x);
        }else if (weatherCondition.contains("Snow")){
            image.setImageResource(R.drawable.snow_1x);
        }else if (weatherCondition.contains("Storm")){
           image.setImageResource(R.drawable.thunderstorm_1x);
        }else {
           image.setImageResource(R.drawable.sun_1x);
        }
    }

    public static Date format(String s) {
        Date _time = null ;
        try {
            _time  = new SimpleDateFormat("HH:mm:ss").parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return _time;
    }
}
