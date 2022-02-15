
package com.example.itunesapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.itunesapp.databinding.FragmentAppCategoriesBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppCategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppCategoriesFragment extends Fragment {

//    HashMap<String, ArrayList<DataServices.App>> hashAppList = DataServices.getAppCategories();

    DataServices.App appList;

    ArrayAdapter<String> adapter;

    ArrayList<String> appCategoryStringList = DataServices.getAppCategories();

    public AppCategoriesFragment(DataServices.App appList) {
        // Required empty public constructor
        this.appList=appList;
        }



    // TODO: Rename and change types and number of parameters
    public static AppCategoriesFragment newInstance(DataServices.App appList) {
        AppCategoriesFragment fragment = new AppCategoriesFragment(appList);
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    FragmentAppCategoriesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAppCategoriesBinding.inflate(inflater,container,false);


        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, DataServices.getAppCategories());
        binding.viewListAppCategory.setAdapter(adapter);

        binding.viewListAppCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mListener.receiveAppCategory(appCategoryStringList.get(i));
            }
        });


        return binding.getRoot();
    }

    iListenerAppCategory mListener;

    public interface iListenerAppCategory {
        void receiveAppCategory(String categoryName);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof iListenerAppCategory) {
            mListener = (iListenerAppCategory) context;
        }
    }
}