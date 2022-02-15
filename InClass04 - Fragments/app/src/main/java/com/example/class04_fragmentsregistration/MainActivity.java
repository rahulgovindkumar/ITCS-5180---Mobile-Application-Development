/*Class 04
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */

package com.example.class04_fragmentsregistration;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Register.IListener, Login.iListener, AccountFragment.iListener, Update.IListener {

    final String TAG = "Demo";
    DataServices.Account account;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new Login(), "Login")
                .commit();
        setTitle(getString(R.string.LoginTitle));
        Log.d(TAG, "In Login: onCreate ");
    }

    @Override
    public void storeNewAccount(DataServices.Account account) {
//        Log.d(TAG, "storeNewAccount: " + account.getName() + account.getEmail() + account.getPassword());
        if (account != null) {

            while(getSupportFragmentManager().getBackStackEntryCount()>0){
                getSupportFragmentManager().popBackStackImmediate();
            }

            this.account = account;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, new AccountFragment(account),"AccountFragment")
                    .commit();
            setTitle(getString(R.string.AccountTitle));
            Log.d(TAG, "In AccountFragment: storeNewAccount ");
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, new Login(), "Login")
                    .commit();
            setTitle(getString(R.string.LoginTitle));
            Log.d(TAG, "In Login: storeNewAccount ");

        }
    }


    @Override
    public void getAccountData_Account(DataServices.Account account) {
        //Log.d(TAG, "In Login: Before getAccountData_Account " + (account==null));

        if (account == null) {
            this.account = null;

            while(getSupportFragmentManager().getBackStackEntryCount()>0){
                getSupportFragmentManager().popBackStackImmediate();
            }


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, new Login(), "Login")
                    .commit();
            Log.d(TAG, "In Login: getAccountData_Account ");
            setTitle(getString(R.string.LoginTitle));

        } else {

            while(getSupportFragmentManager().getBackStackEntryCount()>0){
                getSupportFragmentManager().popBackStackImmediate();
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, Update.newInstance(account),"Update")
                    .addToBackStack(null)
                    .commit();
            Log.d(TAG, "In Update: getAccountData_Account");

            setTitle(getString(R.string.updateTitle));



        }
    }

    @Override
    public void getAccountData(DataServices.Account account) {
        if (account == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, new Register(), "Register")
                    .commit();
            setTitle(getString(R.string.RegisterTitle));
            Log.d(TAG, "In Register: getAccountData");
        } else {
            while(getSupportFragmentManager().getBackStackEntryCount()>0){
                getSupportFragmentManager().popBackStackImmediate();
            }
            this.account = account;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, new AccountFragment(account), "AccountFragment")
                    .addToBackStack(null)
                    .commit();
            setTitle(getString(R.string.AccountTitle));
            Log.d(TAG, "In AccountFragment: getAccountData");

        }
    }

    @Override
    public void updateListener(DataServices.Account account) {
        AccountFragment accountFragment = (AccountFragment) getSupportFragmentManager().findFragmentByTag("AccountFragment");
        if (account != null){
            this.account = account;
            accountFragment.updateAccount(account);
        }
        getSupportFragmentManager().popBackStack();
        setTitle(getString(R.string.AccountTitle));
        Log.d(TAG, "In AccountFragment: updateListener");
    }
}