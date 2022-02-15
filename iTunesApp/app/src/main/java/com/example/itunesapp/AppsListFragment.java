
package com.example.itunesapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itunesapp.databinding.FragmentAppCategoriesBinding;
import com.example.itunesapp.databinding.FragmentAppsListBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppsListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_categoryName = "param1";
    AppAdapter adapter;

    // TODO: Rename and change types of parameters
    private String categoryName;

    public AppsListFragment(String categoryName) {
        // Required empty public constructor
        this.categoryName = categoryName;
    }

    public static AppsListFragment newInstance(String categoryName) {
        AppsListFragment fragment = new AppsListFragment(categoryName);
        Bundle args = new Bundle();
        args.putString(categoryName, categoryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryName = getArguments().getString(ARG_categoryName);
        }
    }

    FragmentAppsListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAppsListBinding.inflate(inflater,container,false);
        adapter = new AppAdapter(getContext(), R.layout.top_paid_app_list, DataServices.getAppsByCategory(categoryName));
        binding.viewListAppList.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().setTitle(getString(R.string.appCategoriesTitle));
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(categoryName);
    }
}