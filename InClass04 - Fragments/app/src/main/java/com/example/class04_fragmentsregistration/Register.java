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

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Register extends Fragment {

    EditText registerName, registerEmail, registerPassword;
    Button registerSubmit, registerCancel;
    protected String inputName, inputEmail, inputPassword;
    DataServices.Account account;
    IListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof IListener){
            mListener = (IListener) context;
        } else {
            throw new RuntimeException(context.toString() + getString(R.string.errorMessageIlistner));
        }
    }

    public interface IListener{
        void storeNewAccount(DataServices.Account account);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Register() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Register.
     */
    // TODO: Rename and change types and number of parameters
    public static Register newInstance(String param1, String param2) {
        Register fragment = new Register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Pattern emailPattern = Patterns.EMAIL_ADDRESS;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        registerName = view.findViewById(R.id.editText_Name_Register);
        registerEmail = view.findViewById(R.id.editText_Email_Register);
        registerPassword = view.findViewById(R.id.editText_Password_Register);
        registerSubmit = view.findViewById(R.id.buttonSubmit_Register);
        registerCancel = view.findViewById(R.id.buttonCancel_Register);

        DataServices ds = new DataServices();

        registerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputName = registerName.getText().toString();
                inputEmail = registerEmail.getText().toString();
                inputPassword = registerPassword.getText().toString();



                    DataServices.AccountRequestTask task = DataServices.register(inputName, inputEmail, inputPassword);
                    if (task.isSuccessful()){
                        DataServices.Account account = task.getAccount();
                        mListener.storeNewAccount(account);
                    } else {
                        Toast.makeText(getActivity(), task.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }




            }
        });

        registerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.storeNewAccount(null);
            }
        });

        return view;
    }
}