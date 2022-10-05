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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    final String TAG = "Demo";

    DataServices.Account account;

    TextView displayName;
    Button editProfileButton, logOutButton;

    iListener mListener;

   /* public void updateAccount(DataServices.AccountFragment account){
        this.account= account;
    }*/

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
        void getAccountData_Account(DataServices.Account account);
    }


    public AccountFragment(DataServices.Account account) {
        this.account =account;

    }

    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(DataServices.Account account) {
        AccountFragment fragment = new AccountFragment(account);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void updateAccount ( DataServices.Account account){
        this.account = account;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        displayName = view.findViewById(R.id.textView_AccountName);
        displayName.setText(account.getName());


        editProfileButton = view.findViewById(R.id.button_EditProfile);
        logOutButton = view.findViewById(R.id.button_Logout_Account);

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.getAccountData_Account(account);
            }
        });
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.getAccountData_Account(null);
                Log.d(TAG, "In Logout: button ");
            }
        });
        return view;
    }
}