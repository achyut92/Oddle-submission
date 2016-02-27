package com.test.oddle.weatherapp.APICalls;

import android.os.AsyncTask;

import com.test.oddle.weatherapp.Model.Weather;
import com.test.oddle.weatherapp.Utils.ParserHelper;

import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Achyut on 2/24/2016.
 */
public class WeatherDetails extends AsyncTask<String,Void,Weather> {
    @Override
    protected Weather doInBackground(String... params) {
        Weather weather = null;
        try{
            URL js = new URL(params[0]);
            URLConnection jc = js.openConnection();

            String line = ParserHelper.readStream(jc.getInputStream());

            JSONObject jsonObject = new JSONObject(line);
            JSONObject observation = jsonObject.getJSONObject("current_observation");

            String date_time = observation.getString("local_time_rfc822");
            String[] new_date = date_time.split(" ");
            Date time = toDate(new_date[4]);

            weather = new Weather(observation.getString("weather"),observation.getString("temp_f"),observation.getString("temp_c"),time);
        }catch (Exception e){
            e.printStackTrace();

        }
        return weather;
    }

    private Date toDate(String city_time) {

        Date local_time = null;
        int hr;
        try {
            local_time = new SimpleDateFormat("HH:mm:ss").parse(city_time);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return local_time;
    }

    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);
    }
}
