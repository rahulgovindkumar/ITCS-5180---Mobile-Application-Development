
package com.company.HW04_group18;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.HW04_group18.databinding.FragmentRegisterBinding;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;
    Gson gson = new Gson();
    iListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        binding.fragmentRegisterButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.fragmentRegisterEditTextEmailAddress.getText().toString();
                String password = binding.editTextTextPassword.getText().toString();
                String fullName = binding.fragmentRegisterEditTextFullName.getText().toString();
                if (email.length()<1 || password.length()<1 || fullName.length()<1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Error!")
                            .setMessage("Please make sure that none of the fields are empty.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                    builder.show();
                } else {
                    CrudOperations.createAccount(email, password, fullName, new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            UserDetails user = gson.fromJson(response.body().charStream(), UserDetails.class);
                            if (user.status.equals("ok")) {
                                mListener.updateUserDetails(user);
                                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString(getString(R.string.userKey), user.toString());
                                editor.apply();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mListener.gotoPosts();
                                    }
                                });
                            } else {
                                final String errorMessage = user.message;
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                        builder.setTitle("Error!")
                                                .setMessage(errorMessage)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                    }
                                                });
                                        builder.show();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

        binding.fragmentRegisterButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToLogin();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Create New Account");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof iListener) {
            mListener = (iListener) context;
        }
    }

    public void runListenerFunctions(UserDetails user) {
        mListener.updateUserDetails(user);
    }

    public interface iListener {
        public void updateUserDetails(UserDetails user);
        public void gotoPosts();
        public void goToLogin();
    }

}