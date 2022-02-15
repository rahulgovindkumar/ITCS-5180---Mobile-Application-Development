/*HW07
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */


package com.example.HW07_forumfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.HW07_forumfirebase.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Intent intent = new Intent(this, MapsActivity.class);
//
//        startActivity(intent);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_ContainerView, new LoginFragment())
                    .commit();

        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_ContainerView, new HistoryFragment())
                    .commit();
        }
    }

    public void updateUser(FirebaseUser u) {
        this.user = u;
    }
}