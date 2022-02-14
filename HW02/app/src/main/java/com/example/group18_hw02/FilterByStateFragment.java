
package com.example.group18_hw02;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.group18_hw02.databinding.FragmentFilterByStateBinding;
import com.example.group18_hw02.databinding.FragmentUsersBinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class FilterByStateFragment extends Fragment {

    FragmentFilterByStateBinding binding;
    ArrayAdapter<String> adapter;
    ArrayList<String> statesList = new ArrayList<String>();
    iListener mListener;

    public FilterByStateFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilterByStateBinding.inflate(inflater,container,false);
        ArrayList<DataServices.User> usersList = DataServices.getAllUsers();
        for (DataServices.User u: usersList) {
            if(u.state != null && u != null) {
                statesList.add(u.state);
            }
        }
        statesList.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        statesList = (ArrayList<String>) statesList.stream()
                .distinct()
                .collect(Collectors.toList());
        statesList.add(0, "All States");
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, statesList);
        binding.listviewFragmentFilterByState.setAdapter(adapter);
        binding.listviewFragmentFilterByState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mListener.fetchSelectedStateFromFilterByStateFragment(statesList.get(i));
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof iListener){
            mListener = (iListener) context;
        }
    }

    public  interface iListener {
        void fetchSelectedStateFromFilterByStateFragment(String stateNameOption);
    }
}