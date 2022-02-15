
package com.example.itunesapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.itunesapp.databinding.FragmentAppDetailsBinding;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppDetailsFragment extends Fragment {

    private static final String ARG_APP_DETAILS = "param1";

    private DataServices.App appDetails;
    ArrayAdapter<String> adapter;

    public AppDetailsFragment(DataServices.App appDetails) {
        this.appDetails = appDetails;
    }

    public static AppDetailsFragment newInstance(DataServices.App appDetails) {
        AppDetailsFragment fragment = new AppDetailsFragment(appDetails);
        Bundle args = new Bundle();
        args.putSerializable(ARG_APP_DETAILS, appDetails);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            appDetails = (DataServices.App) getArguments().getSerializable(ARG_APP_DETAILS);
        }
    }

    FragmentAppDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAppDetailsBinding.inflate(inflater,container,false);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, appDetails.genres);

        binding.viewListGenreAppDetail.setAdapter(adapter);
        binding.textViewAppNameAppDetail.setText(appDetails.name);
        binding.textViewArtistNameAppDetail.setText(appDetails.artistName);
        binding.textViewReleaseDateAppDetail.setText(appDetails.releaseDate);


        return binding.getRoot();
    }


}