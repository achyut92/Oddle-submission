package com.test.oddle.weatherapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test.oddle.weatherapp.Model.City;
import com.test.oddle.weatherapp.Model.Weather;
import com.test.oddle.weatherapp.Utils.WeatherElements;

/**
 * Created by Achyut on 2/25/2016.
 */
public class WeatherFragment extends Fragment {

    private City city;
    private TextView cityName,condition;
    private ImageView imageView;

    public static WeatherFragment newInstance(City cityBundle){
        WeatherFragment f = new WeatherFragment();
        Bundle bundle = new Bundle();
        Gson gson1 = new Gson();
        bundle.putString("city", gson1.toJson(cityBundle));
        f.setArguments(bundle);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.weather_frag,container,false);



        Gson gson = new Gson();
        String cityStr = getArguments().getString("city");
        city = gson.fromJson(cityStr,City.class);
        String[] cityArray = city.getCity().split(",");
        String strCity = cityArray[0];

        cityName = (TextView)view.findViewById(R.id.city_tv);
        condition = (TextView)view.findViewById(R.id.weather_tv);
        imageView = (ImageView)view.findViewById(R.id.imageView);
        Weather weather = WeatherElements.getWeatherDetails(strCity,city.getCountry_code());
        cityName.setText(city.getCity());
        condition.setText(weather.getCondition());
        WeatherElements.setImage(weather.getCondition(),imageView);
        if ((weather.getLocal_time()).after(WeatherElements.format("07:00:00"))&&weather.getLocal_time().before(WeatherElements.format("19:00:00"))) {
            view.setBackgroundColor(getResources().getColor(R.color.skyBlue));
        }else {
            view.setBackgroundColor(getResources().getColor(R.color.darkBlue));
        }

        return view;

    }
}
