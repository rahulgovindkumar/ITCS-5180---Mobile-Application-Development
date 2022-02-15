/*HW 05
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */


package com.example.HW05_forumfirebase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inclass08_forumfirebase.R;
import com.example.inclass08_forumfirebase.databinding.FragmentRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;
    FirebaseAuth mAuth;
    final String TAG = "Demo";

    public RegisterFragment() {
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
        binding = FragmentRegisterBinding.inflate(inflater, container, false);;

        mAuth = FirebaseAuth.getInstance();

        binding.buttonSubmitRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.editTextTextEmailAddress.getText().toString();
                String password = binding.editTextTextPassword.getText().toString();
                String name = binding.editTextTextPersonName.getText().toString();

                if(email.isEmpty() || password.isEmpty() || name.isEmpty()) {
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
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

//                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                                                .setDisplayName(name)
//                                                .build();
//                                        user.updateProfile(profileUpdates);

                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(name)
                                                .setPhotoUri(Uri.parse(""))
                                                .build();

                                        user.updateProfile(profileUpdates)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.d(TAG, "User profile updated.");
                                                            getParentFragmentManager().beginTransaction()
                                                                    .replace(R.id.main_ContainerView, new ForumsFragment())
                                                                    .commit();
                                                        } else {
                                                            Log.d(TAG, "onComplete: Task failed");
                                                        }
                                                    }
                                                });

//                                        getParentFragmentManager().beginTransaction()
//                                                .replace(R.id.main_ContainerView, new ForumsFragment())
//                                                .commit();
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

        binding.buttonCancelRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.main_ContainerView, new LoginFragment())
                        .commit();
            }
        });

        return binding.getRoot();


    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setTitle(getString(R.string.CreateNewAccount));    }
}