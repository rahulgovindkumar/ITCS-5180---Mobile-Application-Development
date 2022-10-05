
package com.example.group18_hw02;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.group18_hw02.databinding.FragmentSortBinding;

public class SortFragment extends Fragment {

    FragmentSortBinding binding;
    LinearLayoutManager layoutManager;
    iListener mListener;
    RecyclerViewFragmentSortAdapter adapter;

    public SortFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSortBinding.inflate(inflater,container,false);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new RecyclerViewFragmentSortAdapter(mListener);

        binding.recyclerViewFragmentSort.setHasFixedSize(true);
        binding.recyclerViewFragmentSort.setLayoutManager(layoutManager);
        binding.recyclerViewFragmentSort.setAdapter(adapter);


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
        void fetchSortingAttributesFromRecyclerViewFragmentSortAdapter(String attribute, Boolean criteria);
    }

}