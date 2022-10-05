/*Assignment 2
        Group27
        1 Name: Rahul Govindkumar
  */

package com.example.group27_inclass02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText userInput;
    RadioGroup radioGroupOption;
    TextView priceResult;
    int checked;
    Button clearButton;
    RadioButton defaultClick;







    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        findViewById(R.id.button_Calculate).setOnClickListener(this);
        findViewById(R.id.button_Clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userInput = findViewById(R.id.editTextText_TicketPrice);
                userInput.setText("");
                //radioGroupOption= findViewById(R.id.radioGroup_Discount);
                //radioGroupOption.clearCheck();
                defaultClick=findViewById(R.id.radioButton_Option1);
                defaultClick.setChecked(true);

                priceResult=findViewById(R.id.textView_Result);
                priceResult.setText("");

            }
        });




    }

    @Override
    public void onClick(View view) {

        userInput=findViewById(R.id.editTextText_TicketPrice);
        priceResult=findViewById(R.id.textView_Result);
        radioGroupOption=findViewById(R.id.radioGroup_Discount);



        //Log.d("demo","Userinput");

        try {
            double userinput=Double.parseDouble(userInput.getText().toString());


            checked =radioGroupOption.getCheckedRadioButtonId();

            double result;

            if(checked==R.id.radioButton_Option1){

                result =userinput - userinput*0.05;
                //priceResult.setText(String.valueOf(result));
                priceResult.setText(String.format("%.2f",result));


            }else if(checked==R.id.radioButton_Option2){

                result =userinput - userinput*0.10;
                //priceResult.setText(String.valueOf(result));
                priceResult.setText(String.format("%.2f",result));


            }else if(checked==R.id.radioButton_Option3){
                result =userinput - userinput*0.15;
                //priceResult.setText(String.valueOf(result));
                priceResult.setText(String.format("%.2f",result));

            }else if(checked==R.id.radioButton_Option4){
                result =userinput - userinput*0.20;
                //priceResult.setText(String.valueOf(result));
                priceResult.setText(String.format("%.2f",result));

            }else if(checked==R.id.radioButton_Option5){

                result =userinput - userinput*0.50;
                //priceResult.setText(String.valueOf(result));
                priceResult.setText(String.format("%.2f",result));

            }





        }catch (Exception e){

            Toast.makeText(this, "Please Enter a valid Number - "+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }




    }



}