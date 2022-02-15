/*HW 05
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */


package com.example.HW05_forumfirebase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inclass08_forumfirebase.R;
import com.example.inclass08_forumfirebase.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        mAuth = FirebaseAuth.getInstance();



        binding.buttonLoginLoginFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.editTextEmailAddressLoginFragment.getText().toString();
                String password = binding.editTextPasswordLoginFragment.getText().toString();

                if(email.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Error!")
                            .setMessage("Please make sure that none of the fields are empty!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                    builder.show();
                } else  {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        getParentFragmentManager().beginTransaction()
                                                .replace(R.id.main_ContainerView, new ForumsFragment())
                                                .commit();
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                        builder.setTitle("Login error!")
                                                .setMessage(task.getException().getMessage())
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                    }
                                                });
                                        builder.show();
                                    }
                                }
                            });
                }
            }
        });

        binding.buttonCreateNewAccountLoginFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mListener.gotoRegisterFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.main_ContainerView, new RegisterFragment())
                        .commit();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.Login));
    }
}