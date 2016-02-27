package com.test.oddle.weatherapp.Model;

import java.util.Date;

/**
 * Created by Achyut on 2/24/2016.
 */
public class Weather {

    private String condition;
    private String temp_f;
    private String temp_c;
    private Date local_time;

    public Weather(String condition, String temp_f, String temp_c,Date time) {
        this.condition = condition;
        this.temp_f = temp_f;
        this.temp_c = temp_c;
        this.local_time = time;
    }

    public Weather() {

    }

    public String getCondition() {
        return condition;
    }

    public String getTemp_f() {
        return temp_f;
    }

    public String getTemp_c() {
        return temp_c;
    }

    public Date getLocal_time() {
        return local_time;
    }
}
