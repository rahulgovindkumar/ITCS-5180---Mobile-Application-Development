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
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Update#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Update extends Fragment {

    DataServices.Account account;
    IListener mListener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        void updateListener(DataServices.Account account);
    }

    public Update(DataServices.Account account) {
        // Required empty public constructor
        this.account = account;
    }

    // TODO: Rename and change types and number of parameters
    public static Update newInstance(DataServices.Account account) {
        Update fragment = new Update(account);
        Bundle args = new Bundle();
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

    TextView emailText;
    EditText inputName, inputPassword;
    Button submitButton, cancelButton;
    String nameValue, passwordValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        emailText = view.findViewById(R.id.textView_Email);
        inputName = view.findViewById(R.id.editText_Name_Update);
        inputPassword = view.findViewById(R.id.editText_Password_Update);
        submitButton = view.findViewById(R.id.button_Submit_Update);
        cancelButton = view.findViewById(R.id.button_Cancel_Update);

        emailText.setText(account.getEmail());
        inputName.setText(account.getName());
        inputPassword.setText(account.getPassword());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameValue = inputName.getText().toString();
                passwordValue = inputPassword.getText().toString();

                DataServices.AccountRequestTask task = DataServices.update(account, nameValue, passwordValue);

                if (task.isSuccessful()){
                    account = task.getAccount();
                    mListener.updateListener(account);

                } else {
                    Toast.makeText(getActivity(), task.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.updateListener(account);
            }
        });
        return view;
    }
}