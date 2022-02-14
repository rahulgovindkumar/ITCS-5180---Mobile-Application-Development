
package com.example.weatherapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapplication.databinding.FragmentCurrentWeatherBinding;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CurrentWeatherFragment extends Fragment {

    Data.City city;
    FragmentCurrentWeatherBinding binding;
    Gson gson = new Gson();
    iListener mListener;

    public void updateCity(Data.City city){
        this.city = city;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false);
        binding.buttonCheckForecastCurrentWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoForecast(city);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.CurrentWeather));
        binding.textViewCityTitleCurrentWeather.setText(city.toString());
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q="+city.toString()+"&appid=04ca163b509dc657d281ebeded59288a&units=imperial")
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                CurrentWeather cw = gson.fromJson(response.body().charStream(), CurrentWeather.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateWeatherDetails(cw);
                    }
                });
            }
        });
    }

    private void updateWeatherDetails(CurrentWeather cw) {
        Picasso.get().load("http://openweathermap.org/img/wn/"+ cw.weather.get(0).icon +".png").into(binding.imageView2);
        binding.textViewTempValueCurrentWeather.setText(cw.main.temp + " F");
        binding.textViewTempMaxValueCurrentWeather.setText(cw.main.temp_max + " F");
        binding.textViewTempMinValueCurrentWeather.setText(cw.main.temp_min + " F");
        binding.textViewDescpValueCurrentWeather.setText(cw.weather.get(0).description);
        binding.textViewHumidityValueCurrentWeather.setText(cw.main.humidity + " %");
        binding.textViewWindSpeedValueCurrentWeather.setText(cw.wind.speed + " miles/hr");
        binding.textViewWindDegreeValueCurrentWeather.setText(cw.wind.deg + " degrees");
        binding.textViewCloudinessValueCurrentWeather.setText(cw.clouds.all + " %");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof iListener) {
            mListener = (iListener) context;
        }
    }

    public interface iListener {
        public void gotoForecast(Data.City city);
    }
}