package com.test.oddle.weatherapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.test.oddle.weatherapp.Model.City;
import com.test.oddle.weatherapp.Utils.SqliteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achyut on 2/25/2016.
 */
public class CityDataSource {

    private SQLiteDatabase database;
    private SqliteHelper dbHelper;
    private String[] allColumns = { SqliteHelper.COLUMN_ID,
            SqliteHelper.COLUMN_CITY, SqliteHelper.COLUMN_CODE };

    public CityDataSource(Context context) {
        dbHelper = new SqliteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createCity(String cityName, String countryCode) {
        ContentValues values = new ContentValues();

        values.put(SqliteHelper.COLUMN_CITY, cityName);
        values.put(SqliteHelper.COLUMN_CODE, countryCode);

        database.insert(SqliteHelper.TABLE_WEATHER, null,
                values);
        close();
    }

    public void deleteCity(String cityName) {
        System.out.println("City deleted with name: " + cityName);
        database.delete(SqliteHelper.TABLE_WEATHER, SqliteHelper.COLUMN_CITY
                + " = ?", new String[]{cityName});
        close();
    }

    public List<City> getAllCities() {
        List<City>cities = new ArrayList<City>();
        String selectQuery = "SELECT  * FROM " + SqliteHelper.TABLE_WEATHER;

        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                City city = cursorToCity(cursor);
                cities.add(city);
                //cursor.moveToNext();
            } while (cursor.moveToNext());
        }
        // make sure to close the cursor
        cursor.close();
        return cities;
    }

    private City cursorToCity(Cursor cursor) {
        City city = new City();
        city.setCity(cursor.getString(1));
        city.setCountry_code(cursor.getString(2));
        return city;
    }

}
