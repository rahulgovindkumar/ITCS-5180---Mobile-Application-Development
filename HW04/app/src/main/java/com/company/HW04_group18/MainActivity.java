/*HW 04
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */

package com.company.HW04_group18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.company.HW04_group18.R;
import com.company.HW04_group18.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements LoginFragment.iListener, RegisterFragment.iListener, PostsFragment.iListener, CreatePostFragment.iListener {

    ActivityMainBinding binding;
    LoginFragment loginFragment = new LoginFragment();
    PostsFragment postsFragment = new PostsFragment();
    RegisterFragment registerFragment = new RegisterFragment();
    CreatePostFragment createPostFragment = new CreatePostFragment();
    UserDetails user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (isUserSaved()){
            gotoPosts();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mainContainerView, loginFragment, "loginFragment")
                    .commit();
        }
    }

    @Override
    public void updateUserDetails(UserDetails user) {
        this.user = user;
    }

    @Override
    public void gotoPosts() {
        postsFragment.updateUserDetails(user);
        postsFragment.updatePageNumberAndRefesh(1);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainerView, postsFragment, "postsFragment")
                .commit();
    }

    @Override
    public void cancelNewPost() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToLogin() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainerView, loginFragment, "loginFragment")
                .commit();
    }

    @Override
    public void goToCreateNewAccount() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainerView, registerFragment, "registerFragment")
                .commit();
    }


    @Override
    public void goToCreatePost() {
        createPostFragment.updateUserDetails(user);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainerView, createPostFragment, "createPostFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void userLogout() {
        user = null;
        postsFragment.updateUserDetails(null);
        createPostFragment.updateUserDetails(null);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String userKey = getResources().getString(R.string.userKey);
        sharedPref.edit().remove(userKey).apply();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainerView, loginFragment, "loginFragment")
                .commit();
    }

    protected boolean isUserSaved() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String userKey = getResources().getString(R.string.userKey);
        String userString = sharedPref.getString(userKey, "");
        if (userString.length() > 0) {
            UserDetails user = new UserDetails(userString);
            this.user = user;
            return true;
        }
        return false;
    }
}