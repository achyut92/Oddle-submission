package com.test.oddle.weatherapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.test.oddle.weatherapp.DAO.CityDataSource;
import com.test.oddle.weatherapp.Model.City;
import com.test.oddle.weatherapp.Model.Weather;
import com.test.oddle.weatherapp.Utils.WeatherElements;

import java.util.Iterator;
import java.util.List;

public class WeatherInfo extends FragmentActivity implements View.OnClickListener {

    private TextView city_name,condition,temperature;
    private ImageView image,add,remove;
    private CityDataSource dataSource;
    private City cityObj;
    private String city;
    private Weather weather;
    private LinearLayout container;
    private List<City> values;
    private RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather_info);
        container = (LinearLayout)findViewById(R.id.weather_widget);


        dataSource = new CityDataSource(this);
        dataSource.open();

         values = dataSource.getAllCities();
        /*if(values.size()>3){
            values = values.subList(0,3);
        }*/


        initializeWidgets();


        Gson gson = new Gson();
        Intent fromMain = getIntent();
        final String city_obj = fromMain.getStringExtra("cityObj");
        cityObj= gson.fromJson(city_obj, City.class);
        String[] cityArray = cityObj.getCity().split(",");
        city = cityArray[0];

        weather = WeatherElements.getWeatherDetails(city, cityObj.getCountry_code());


        Iterator iterator =  values.iterator();
        while (iterator.hasNext()){
            City city1 = (City)iterator.next();
            if (city1.getCity().equals(cityObj.getCity())){
                add.setVisibility(View.GONE);
                remove.setVisibility(View.VISIBLE);
            }

        }


        String weatherCondition = weather.getCondition();
        WeatherElements.setImage(weatherCondition, image);



        city_name.setText(city);
        condition.setText(weatherCondition);
        temperature.setText(weather.getTemp_c() + "C/" + weather.getTemp_f() + "F");

        if ((weather.getLocal_time()).after(WeatherElements.format("07:00:00"))&&weather.getLocal_time().before(WeatherElements.format("19:00:00"))) {
            mainLayout.setBackgroundColor(getResources().getColor(R.color.skyBlue));
        }else {
            mainLayout.setBackgroundColor(getResources().getColor(R.color.darkBlue));
        }


        fragments();



        remove.setOnClickListener(this);
        add.setOnClickListener(this);

    }



    public void fragments(){
        FragmentManager fm = getSupportFragmentManager();

        for (int i=0;i<values.size();i++){
            FrameLayout fl = new FrameLayout(getApplicationContext());
            fl.setId(i+11);
            fl.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            container.addView(fl);
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.add(fl.getId(), WeatherFragment.newInstance(values.get(i))).commit();
        }
    }

    private void initializeWidgets() {
        mainLayout = (RelativeLayout)findViewById(R.id.main);
        city_name = (TextView)findViewById(R.id.city_tv);
        condition = (TextView)findViewById(R.id.weather_tv);
        temperature = (TextView)findViewById(R.id.temperature_tv);
        image = (ImageView)findViewById(R.id.imageView);
        add = (ImageView)findViewById(R.id.add_btn);
        remove = (ImageView)findViewById(R.id.remove_btn);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.add_btn:
                dataSource.createCity(cityObj.getCity(), cityObj.getCountry_code());
                finish();
                Toast.makeText(getApplicationContext(), "City Added to your List", Toast.LENGTH_LONG).show();
                startActivity(getIntent());
                break;
            case R.id.remove_btn:
                values.remove(cityObj.getCity());
                dataSource.deleteCity(city);
                finish();
                Toast.makeText(getApplicationContext(), "City Deleted from your List", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;

        }
    }
}
