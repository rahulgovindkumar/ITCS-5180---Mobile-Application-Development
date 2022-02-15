
package com.example.weatherapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ForecastRecyclerViewAdapter extends RecyclerView.Adapter<ForecastRecyclerViewAdapter.ForecastViewHolder> {

    ArrayList<CurrentWeather> forecastList;

    ForecastRecyclerViewAdapter(ArrayList<CurrentWeather> forecastList) {
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        ForecastViewHolder forecastViewHolder = new ForecastViewHolder(view);
        return forecastViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        CurrentWeather forecastItem = forecastList.get(position);
        Picasso.get().load("http://openweathermap.org/img/wn/"+ forecastItem.weather.get(0).icon +".png").into(holder.weatherIcon);
        holder.description.setText(forecastItem.weather.get(0).description);
        holder.tempDetails.setText(forecastItem.main.temp + "F     Max: " + forecastItem.main.temp_max + "F     Min: " + forecastItem.main.temp_min + "F");
        holder.humidity.setText("Humidity: " + forecastItem.main.humidity + "%");
        holder.dateString.setText(forecastItem.dt_txt);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        TextView dateString, tempDetails, humidity, description;
        ImageView weatherIcon;
        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            dateString = itemView.findViewById(R.id.textView_dateTimeValue_weatherList);
            tempDetails = itemView.findViewById(R.id.textView_tempValues_weatherList);
            humidity = itemView.findViewById(R.id.textView_humidityValue_weatherList);
            description = itemView.findViewById(R.id.textView_skyStatusValue_weatherList);
            weatherIcon = itemView.findViewById(R.id.imageView_weatherIcon_weatherList);
        }
    }
}
