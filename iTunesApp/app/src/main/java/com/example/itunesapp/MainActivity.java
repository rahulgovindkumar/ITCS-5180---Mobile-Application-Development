
package com.example.itunesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.itunesapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AppCategoriesFragment.iListenerAppCategory, AppAdapter.iListenerAppAdapter {

    ActivityMainBinding binding;
    DataServices.App appList;

    final String TAG = "Demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_ContainerView, new AppCategoriesFragment(appList), "AppCategoriesFragment")
                .commit();
        setTitle(getString(R.string.appCategoriesTitle));
    }

    @Override
    public void receiveAppCategory(String categoryName) {
        setTitle(categoryName);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_ContainerView, new AppsListFragment(categoryName), "AppsListFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showAppDetailsFragment(DataServices.App appDetails) {
        setTitle(getString(R.string.Appdetailtitle));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_ContainerView, new AppDetailsFragment(appDetails), "AppDetailsFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(getString(R.string.appCategoriesTitle));
    }
}