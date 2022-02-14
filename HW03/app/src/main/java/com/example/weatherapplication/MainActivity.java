
package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.weatherapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements CitiesListFragment.iListener, CurrentWeatherFragment.iListener {

    ActivityMainBinding binding;
    CitiesListFragment citiesListFragment = new CitiesListFragment();
    CurrentWeatherFragment currentWeatherFragment = new CurrentWeatherFragment();
    WeatherForecastFragment weatherForecastFragment = new WeatherForecastFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainContainerView, citiesListFragment, "citiesListFragment")
                .commit();
    }

    @Override
    public void goToCurrentWeather(Data.City city) {
        currentWeatherFragment.updateCity(city);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainerView, currentWeatherFragment, "currentWeatherFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoForecast(Data.City city) {
        weatherForecastFragment.updateCity(city);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainerView, weatherForecastFragment, "weatherForecastFragment")
                .addToBackStack(null)
                .commit();
    }
}