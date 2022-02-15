package com.example.group18_hw02;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.group18_hw02.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements UsersFragment.iListener, FilterByStateFragment.iListener, SortFragment.iListener {

    ActivityMainBinding binding;
    UsersFragment usersFragment = new UsersFragment();
    final String TAG = "Demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_ContainerView, usersFragment, "UsersFragment")
                .commit();
        setTitle(getString(R.string.UsersHeader));
    }

    @Override
    public void openStateFilterFragmentFromUsersFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_ContainerView, new FilterByStateFragment(), "FilterByStateFragment")
                .addToBackStack(null)
                .commit();
        setTitle(getString(R.string.FilterByStateHeader));
    }

    @Override
    public void openSortFragmentFromUsersFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_ContainerView, new SortFragment(), "SortFragment")
                .addToBackStack(null)
                .commit();
        setTitle(getString(R.string.SortHeader));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void fetchSelectedStateFromFilterByStateFragment(String stateNameOption) {
        usersFragment.filterByStateUserListUsersFragment(stateNameOption);
        getSupportFragmentManager().popBackStack();
//        Log.d(TAG, "fetchSelectedStateFromFilterByStateFragment: " + stateNameOption);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void fetchSortingAttributesFromRecyclerViewFragmentSortAdapter(String attribute, Boolean criteria) {
        usersFragment.sortBySelectedCriteria(attribute, criteria);
        getSupportFragmentManager().popBackStack();
    }
}