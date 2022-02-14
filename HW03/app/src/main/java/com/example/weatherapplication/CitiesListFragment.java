
package com.example.weatherapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.weatherapplication.databinding.FragmentCitiesListBinding;

public class CitiesListFragment extends Fragment {

    FragmentCitiesListBinding binding;
    ArrayAdapter<Data.City> adapter;
    iListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCitiesListBinding.inflate(inflater, container, false);

        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, Data.cities);
        binding.listViewCityList.setAdapter(adapter);
        binding.listViewCityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Data.City city = Data.cities.get(i);
                mListener.goToCurrentWeather(city);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof iListener) {
            mListener = (iListener) context;
        }
    }

    public interface iListener {
        void goToCurrentWeather(Data.City city);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.CitiesList));
    }
}