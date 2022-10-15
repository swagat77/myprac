package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;
import  com.google.gson.annotations.Expose;
public class example {
    @SerializedName("main")
    @Expose
    Main main;
    @SerializedName("wind")
    @Expose
    Wind wind;
    @SerializedName("sys")
    @Expose
    Sys sys;



    public Wind getWind(){
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main= main;
    }
    public Sys getSys(){
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }
}

