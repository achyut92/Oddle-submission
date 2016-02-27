package com.test.oddle.weatherapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.test.oddle.weatherapp.Adapter.SuggestionAdapter;
import com.test.oddle.weatherapp.Model.City;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final AutoCompleteTextView cityName = (AutoCompleteTextView)findViewById(R.id.city);
        final SuggestionAdapter suggestionAdapter = new SuggestionAdapter(this,cityName.getText().toString());
        cityName.setDropDownBackgroundResource(R.drawable.color);
        cityName.setAdapter(suggestionAdapter);


        cityName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<City> cityList = suggestionAdapter.getCity_suggestion();
                City city = cityList.get(position);
                Gson gson = new Gson();
                Intent i = new Intent(getApplicationContext(),WeatherInfo.class);
                i.putExtra("cityObj",gson.toJson(city));
                startActivity(i);
                cityName.setText("");
            }
        });
       
    }


}
