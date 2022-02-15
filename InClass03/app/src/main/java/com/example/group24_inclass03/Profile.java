/*Class 03
        Grouping1-Group27 Grouping2-24
        Name: Rahul Govindkumar



        */

package com.example.group24_inclass03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView selectDepartment;
    TextView enterName;
    TextView enterEmail;
    TextView enterID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        selectDepartment = findViewById(R.id.textView_ProfileDept);

        enterName = findViewById(R.id.textView_ProfileName);
        enterEmail = findViewById(R.id.textView_ProfileEmail);
        enterID = findViewById(R.id.textView_ProfileID);

        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(Registration.USER_KEY)){

            ProfileUser user = getIntent().getParcelableExtra(Registration.USER_KEY);

            enterName.setText(user.name);
            enterEmail.setText(user.email);
            enterID.setText(user.id);
            selectDepartment.setText(user.department);
        }



    }
}