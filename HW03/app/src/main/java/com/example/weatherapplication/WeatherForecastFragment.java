
package com.example.weatherapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapplication.databinding.FragmentWeatherForecastBinding;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherForecastFragment extends Fragment {

    FragmentWeatherForecastBinding binding;
    Data.City city;
    ArrayList<CurrentWeather> forecastList = new ArrayList<CurrentWeather>();
    ForecastRecyclerViewAdapter adapter = new ForecastRecyclerViewAdapter(forecastList);
    LinearLayoutManager layoutManager;
    Gson gson = new Gson();
    final String TAG = "Demo";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWeatherForecastBinding.inflate(inflater, container, false);
        layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerViewForcastWeatherForecast.addItemDecoration(new DividerItemDecoration(binding.recyclerViewForcastWeatherForecast.getContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerViewForcastWeatherForecast.setHasFixedSize(true);
        binding.recyclerViewForcastWeatherForecast.setAdapter(adapter);
        binding.recyclerViewForcastWeatherForecast.setLayoutManager(layoutManager);
        return binding.getRoot();
    }

    public void updateCity(Data.City city) {
        this.city = city;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/forecast?q="+city.toString()+"&appid=04ca163b509dc657d281ebeded59288a&units=imperial")
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                forecastList = gson.fromJson(response.body().charStream(), Forecast.class).list;
                adapter.forecastList = forecastList;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateForecastList();
                    }
                });
            }
        });
    }

    private void updateForecastList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.WT_title));
        binding.textViewCityNameWeatherForecast.setText(city.toString());
    }
}