package com.example.weatherapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
EditText edit;
TextView text;
Button  about;
 String url ="api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
 String apikey="4afa8c05ca8c27b8e2921461124691a0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit=findViewById(R.id.edit);
        text = findViewById(R.id.text);
        about=findViewById(R.id.about);
    }
    public void getweather(View v){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherapi myapi=retrofit.create(weatherapi.class);
        Call<example> examplecall=myapi.getweather(edit.getText().toString().trim(),apikey);
        examplecall.enqueue(new Callback<example>() {
            @Override
            public void onResponse(Call<example> call, Response<example> response) {
                if (response.code() == 404) {
                    Toast.makeText(MainActivity.this, "Please enter a valid city", Toast.LENGTH_LONG).show();
                } else if (!(response.isSuccessful())) {
                    Toast.makeText(MainActivity.this, response.code()+" ", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    example mydata = response.body();
                    Main main = mydata.getMain();
                    Double temp = main.getTemp();
                    Integer temperature = (int) (temp - 273.15);
                    text.setText(String.valueOf(temperature) + "° Celsius");
                }
            }

            @Override
            public void onFailure(Call<example> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
}