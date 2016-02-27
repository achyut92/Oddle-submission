package com.test.oddle.weatherapp.APICalls;

import android.os.AsyncTask;

import com.test.oddle.weatherapp.Model.City;
import com.test.oddle.weatherapp.Utils.Constants;
import com.test.oddle.weatherapp.Utils.ParserHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Achyut on 2/24/2016.
 */
public class CityList extends AsyncTask<Void,Void,ArrayList<City>> {

    private String cityName = null;
    public CityList(String cityName) {
        this.cityName = cityName;
    }

    @Override
    protected ArrayList<City> doInBackground(Void... params) {
        ArrayList<City> cityList = new ArrayList<City>();
        try {
            String temp=cityName.replace(" ", "%20");
            URL js = new URL(Constants.URL_AUTOCOMPLETE_ACCU+temp+Constants.ACCU_KEY);
            URLConnection jc = js.openConnection();

            String line = ParserHelper.readStream(jc.getInputStream());
            JSONArray jsonArray = new JSONArray(line);

            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                cityList.add(new City(jsonObject.getString("LocalizedName"),jsonObject.getJSONObject("AdministrativeArea").getString("ID")));
            }

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return cityList;
    }

    @Override
    protected void onPostExecute(ArrayList<City> cities) {
        super.onPostExecute(cities);
    }

}
