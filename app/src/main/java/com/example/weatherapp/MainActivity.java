package com.example.weatherapp;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
EditText edit;
TextView text;
Button  about;
TextView texts,text2;
 String url ="api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}" ;
// Type collectionType= new TypeToken<Collection<yourJson>>
    //Call<ArrayList<Place>>getNearbyPlaces" +
        // "(@Query("latitude")String latitude,@Query("longitude")String longitude)";
 //String url1="api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={API key}";
 String apikey="4afa8c05ca8c27b8e2921461124691a0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit=findViewById(R.id.edit);
        text = findViewById(R.id.text);
        about=findViewById(R.id.about);
        texts=findViewById(R.id.texts);
    }
    public void getweather(View v){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherapi myapi=retrofit.create(weatherapi.class);
        Call<example> examplecall=myapi.getweather(edit.getText().toString().trim(),apikey);
        examplecall.enqueue(new Callback<example>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<example>call, Response<example>response) {
                if (response.code() == 404) {
                    Toast.makeText(MainActivity.this, "Please enter a valid city", Toast.LENGTH_LONG).show();
                } else if (!(response.isSuccessful())) {
                    Toast.makeText(MainActivity.this, response.code()+" ", Toast.LENGTH_LONG).show();
                    return;
                } else {

                   example mydata = response.body();
                    //example1 mydata= response.body();
                    assert mydata != null;
                    Main main = mydata.getMain();
                    Wind wind=mydata.getWind();
                    Sys sys= mydata.getSys();
                  //  Weather weather= mydata.getWeather();
                    Double temp = main.getTemp();
                    Double pressure=main.getPressure();
                    Integer humidity=main.getHumidity();
                    Double tempmin=main.getTempMin();
                    Double tempmax=main.getTempMax();
                    Double speed=wind.getSpeed();
                    long rise=sys.getSunrise();
                    long set=sys.getSunset();
                    Instant instant = Instant.ofEpochSecond(rise-18000);
                    Instant instant1 = Instant.ofEpochSecond(set-18000);

                    //ZoneId z=ZoneId.of("America/Texas");
                    //ZonedDateTime z=instant.atZone(z);
                    //String description= weather.getDescription();
                    Double temperature =(double)((1.8*(temp - 273.15))+32);
                    String pattern="#.###";
                    Double mintemp=(double)((1.8*(tempmin - 273.15))+32);
                    Double maxtemp= (double)((1.8*(tempmax - 273.15))+32);
                    DecimalFormat decimalFormat=new DecimalFormat(pattern);
                    String formatteddouble=decimalFormat.format(temperature);
                    String formatteddouble1=decimalFormat.format(pressure);
                    String formatteddouble2=decimalFormat.format(humidity);
                    String formatteddouble3=decimalFormat.format(mintemp);
                    String formatteddouble4=decimalFormat.format(maxtemp);
                    //SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH-mm:ssZ");

                    text.setText(String.valueOf("Current temp: "+(formatteddouble) + "° F"+"\nPressure:"+
                            (formatteddouble1)+" hPa\n humidity:"+formatteddouble2+" % \n"+"min temp:"
                            +formatteddouble3+"° F \nmax temp:"+formatteddouble4+"° F\nsunrise: "+instant+"\nsunset: "+instant1+"\nwind speed:"+(speed)+"mph"));
                  //texts.setText(String.valueOf(speed)+"mph");
                // text2.setText("sunrise is at"+String.valueOf((instant)));

                }
            }




            @Override
           public void onFailure(Call<example> call, Throwable t) { Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
            //@Override
          //  public void onResponse(Call<example> call, Response<example> response) {

           // }
        });

    }
}