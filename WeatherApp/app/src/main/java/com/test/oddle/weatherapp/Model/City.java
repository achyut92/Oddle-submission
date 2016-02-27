package com.test.oddle.weatherapp.Model;

/**
 * Created by Achyut on 2/24/2016.
 */
public class City {

    private String city,country_code;
    private double latitude,longitude;

    public City(String city, String country_code, double latitude, double longitude) {
        this.city = city;
        this.country_code = country_code;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public City() {

    }

    public City(String cityName, String country_code) {
        this.city = cityName;
        this.country_code = country_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
