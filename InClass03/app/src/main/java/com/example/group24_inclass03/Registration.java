/*Class 03
        Grouping1-Group27 Grouping2-24
        Name: Rahul Govindkumar



        */

package com.example.group24_inclass03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    final String TAG = "demo";
    final static  public int REQ_CODE = 100;
    final static  public String  USER_KEY = "User";
    String errorMessage;

    TextView selectDepartment;
    EditText enterName;
    EditText enterEmail;
    EditText enterID;


    String enteredName;
    String enteredEmail;
    String enteredID;
    String selectedDepartment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        selectDepartment = findViewById(R.id.textView_DeptResult);
        enterName = findViewById(R.id.editTextText_Name);
        enterEmail = findViewById(R.id.editTextText_Email);
        enterID = findViewById(R.id.editTextText_ID);


        findViewById(R.id.button_Submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enteredName = enterName.getText().toString();
                enteredEmail = enterEmail.getText().toString();
                enteredID = enterID.getText().toString();
                selectedDepartment = selectDepartment.getText().toString();

                Pattern emailPattern = Patterns.EMAIL_ADDRESS;




                errorMessage = getString(R.string.error_Message);

                if(enteredName.isEmpty() || enteredEmail.isEmpty() || enteredID.isEmpty() || selectedDepartment =="" ){



                    errorMessage = errorMessage.concat(" Missing Values for ");
                    if(enteredName.isEmpty()){
                        errorMessage = errorMessage.concat(" "+ getString(R.string.textview_Name));
                    }
                    if( enteredEmail.isEmpty()){
                        errorMessage = errorMessage.concat(" "+ getString(R.string.textview_Email));
                    }
                    if(enteredID.isEmpty()){
                        errorMessage = errorMessage.concat(" "+ getString(R.string.textview_ID));
                    }
                    if(selectedDepartment ==""){
                        errorMessage = errorMessage.concat(" "+ getString(R.string.textview_Dept));
                    }

                    //errorMessage = getString(R.string.error_Message);
                    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
                else
                {

                    if (emailPattern.matcher(enteredEmail).matches()){

                        Intent intent = new Intent( Registration.this, Profile.class);
                        intent.putExtra(USER_KEY, new ProfileUser(enteredName, enteredEmail,enteredID,selectedDepartment));
                        startActivity(intent);


                    }else{


                        errorMessage = errorMessage.concat(" "+ getString(R.string.error_MessageEmail));
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();



                    }



                }







            }
        });



        findViewById(R.id.button_Select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, Department.class);
                startActivityForResult(intent,REQ_CODE);



            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE){

            if(resultCode == RESULT_OK){
                Log.d(TAG, "onActivityResult: RESULT Ok");

                if( data != null && data.hasExtra(Department.DEPT_SELECTED)){
                    String deptSelected = data.getStringExtra(Department.DEPT_SELECTED);
                    Log.d(TAG, "onActivityResult: " + deptSelected);
                    selectDepartment.setText(deptSelected);
                }

            }else if(resultCode == RESULT_CANCELED){

                Log.d(TAG, "onActivityResult:Result Cancelled");

            }

        }




    }

    public boolean Validation(){


        return true;

    }
}