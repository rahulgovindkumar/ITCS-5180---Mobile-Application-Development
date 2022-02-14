
package com.example.weatherapplication;

import java.util.List;

public class CurrentWeather {
    public List<Weather> weather;
    public Main main;
    public Wind wind;
    public Clouds clouds;
    String dt_txt;


    public class Weather{
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public class Main{
        public double temp;
        public double temp_min;
        public double temp_max;
        public int humidity;
    }

    public class Wind{
        public double speed;
        public int deg;
    }

    public class Clouds{
        public int all;
    }

}