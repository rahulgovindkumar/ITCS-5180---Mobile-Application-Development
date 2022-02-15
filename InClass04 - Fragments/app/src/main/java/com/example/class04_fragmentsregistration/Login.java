/*Class 04
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */

package com.example.class04_fragmentsregistration;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    iListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof iListener){
            mListener = (iListener) context;
        } else {
            throw new RuntimeException(context.toString() + getString(R.string.errorMessageIlistner));
        }
    }

    public interface iListener {
        void getAccountData(DataServices.Account account);
    }

    EditText loginEmail, loginPassword;
    Button loginButton, createNewButton;
    String inputEmail, inputPassword;


    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        loginEmail = view.findViewById(R.id.editText_Email_Login);
        loginPassword = view.findViewById(R.id.editText_Password_Login);
        createNewButton = view.findViewById(R.id.button_CreateNewAccount_Login);
        loginButton = view.findViewById(R.id.button_Login);

        createNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.getAccountData(null);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputEmail = loginEmail.getText().toString();
                inputPassword = loginPassword.getText().toString();
                DataServices.AccountRequestTask task = DataServices.login(inputEmail, inputPassword);
                if (task.isSuccessful()){
                    mListener.getAccountData(task.getAccount());
                } else {
                    Toast.makeText(getActivity(), task.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}