package com.example.weatherapp;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
;
public interface weatherapi {
    @GET("weather")
    Call<example> getweather(@Query("q")String cityname, @Query("appid")String apikey);

   // @GET("forecast")
   // Observable<WeatherForecastResult> getweather(@Query("lat") String lat,
                                                                     //    @Query("lon") String lng,
                                                                       //  @Query("appid") String appid,
                                                                       //  @Query("units") String unit);


}
