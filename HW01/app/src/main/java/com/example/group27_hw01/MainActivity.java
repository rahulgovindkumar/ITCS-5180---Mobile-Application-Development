
package com.example.group27_hw01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar customTip;
    Button clearButton;
    RadioGroup tipOption;
    RadioGroup splitOptions;
    RadioButton defaultTipOption;
    RadioButton defaultSplitOption;
    EditText billTotal;
    TextView customTipValue;
    TextView tipAmount;
    TextView totalAmount;
    TextView totalAmountPerPerson;
    int checked;
    int splitChecked;
    double tipAmountValue;
    double totalAmountValue;
    double totalAmountValuePerPerson;
    double userBillTotal;
    double customSeekValue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipOption = findViewById(R.id.radioGroup_Tip);
        splitOptions = findViewById((R.id.radioGroup_SplitBy));

        billTotal = findViewById(R.id.editText_EnterBillTotal);

        customTipValue=findViewById(R.id.textView_CustomTipValue);
        tipAmount = findViewById(R.id.textView_DollarTipAmount);
        totalAmount = findViewById(R.id.textView_DollarTotalAmount);
        totalAmountPerPerson = findViewById(R.id.textView_TotalPerPersonAmount);

        clearButton = findViewById(R.id.button_Clear);

        customTip=findViewById(R.id.seekBar_CustomTip);


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billTotal.setText("");

                defaultTipOption = findViewById(R.id.radioButton_Tip10);
                defaultTipOption.setChecked(true);

                defaultSplitOption = findViewById(R.id.radioButton_SplitByOne);
                defaultSplitOption.setChecked(true);

                tipAmount.setText(String.valueOf("$0.0"));
                totalAmount.setText(String.valueOf("$0.0"));
                totalAmountPerPerson.setText(String.valueOf("$0.0"));

                customTip.setProgress(40);

            }
        });




        customTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                customTipValue.setText(String.valueOf(progress+"%"));


                if(billTotal.getText().toString().isEmpty()){
                    //Toast.makeText(getApplicationContext(), "Please Enter a Valid Number ", Toast.LENGTH_SHORT).show();
                    tipAmount.setText(String.valueOf("$0.0"));
                    totalAmount.setText(String.valueOf("$0.0"));
                    totalAmountPerPerson.setText(String.valueOf("$0.0"));
                }
                else{
                    //To calculate Tip and Split Amount
                    tipCalculation();

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        billTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                tipAmount.setText(String.valueOf("$0.0"));
                totalAmount.setText(String.valueOf("$0.0"));
                totalAmountPerPerson.setText(String.valueOf("$0.0"));

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                try{


                    if(billTotal.getText().toString().isEmpty()){
                        //Toast.makeText(getApplicationContext(), "Please Enter a Valid Number ", Toast.LENGTH_SHORT).show();
                        tipAmount.setText(String.valueOf("$0.0"));
                        totalAmount.setText(String.valueOf("$0.0"));
                        totalAmountPerPerson.setText(String.valueOf("$0.0"));


                    }
                    else{
                        //Get Entered Bill amount
                        userBillTotal=Double.parseDouble(billTotal.getText().toString());

                        //To calculate Tip and Split Amount
                        tipCalculation();

                    }




                }catch (NumberFormatException e){

                    String errorMessage = getString(R.string.error_Message);
                    Toast.makeText(getApplicationContext(), errorMessage+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    tipAmount.setText(String.valueOf("$0.0"));
                    totalAmount.setText(String.valueOf("$0.0"));
                    totalAmountPerPerson.setText(String.valueOf("$0.0"));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });

        tipOption.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {

                //To Check if the Bill Amount is Empty
                if(billTotal.getText().toString().isEmpty()){
                    //Toast.makeText(getApplicationContext(), "Please Enter a Valid Number ", Toast.LENGTH_SHORT).show();
                    tipAmount.setText(String.valueOf("$0.0"));
                    totalAmount.setText(String.valueOf("$0.0"));
                    totalAmountPerPerson.setText(String.valueOf("$0.0"));


                }
                else{

                    //To calculate Tip and Split Amount
                    tipCalculation();
                }
            }
        });


        splitOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(RadioGroup radioGroup, int splitCheckedID) {

                if(billTotal.getText().toString().isEmpty()){
                    //Toast.makeText(getApplicationContext(), "Please Enter a Valid Number ", Toast.LENGTH_SHORT).show();
                    tipAmount.setText(String.valueOf("$0.0"));
                    totalAmount.setText(String.valueOf("$0.0"));
                    totalAmountPerPerson.setText(String.valueOf("$0.0"));
                }
                else{

                    //To calculate Split Amount
                    splitCalculation();

                }
            }
        });






    }


    public void splitCalculation(){

        //To Get the Selected Radio option from Split
        splitChecked =splitOptions.getCheckedRadioButtonId();

        if (splitChecked== R.id.radioButton_SplitByOne){

            totalAmountValuePerPerson =totalAmountValue/1;

           // totalAmountPerPerson.setText(String.valueOf("$"+totalAmountValuePerPerson));
            totalAmountPerPerson.setText(String.format("$"+"%.2f",totalAmountValuePerPerson));



        } else if (splitChecked== R.id.radioButton_SplitByTwo){

            totalAmountValuePerPerson =totalAmountValue/2;
            //totalAmountPerPerson.setText(String.valueOf("$"+totalAmountValuePerPerson));
            totalAmountPerPerson.setText(String.format("$"+"%.2f",totalAmountValuePerPerson));


        }else if (splitChecked== R.id.radioButton_SplitByThree){

            totalAmountValuePerPerson =totalAmountValue/3;
            //totalAmountPerPerson.setText(String.valueOf("$"+totalAmountValuePerPerson));
            totalAmountPerPerson.setText(String.format("$"+"%.2f",totalAmountValuePerPerson));


        }else if (splitChecked== R.id.radioButton_SplitByFour){

            totalAmountValuePerPerson =totalAmountValue/4;
            //totalAmountPerPerson.setText(String.valueOf("$"+totalAmountValuePerPerson));
            totalAmountPerPerson.setText(String.format("$"+"%.2f",totalAmountValuePerPerson));


        }


    }

    public void tipCalculation(){

        //To Get the Selected Radio option from Tip
        checked =tipOption.getCheckedRadioButtonId();

        if (checked== R.id.radioButton_Tip10){

            tipAmountValue= userBillTotal*0.10;
            //tipAmount.setText(String.valueOf("$"+tipAmountValue));
            tipAmount.setText(String.format("$"+"%.2f",tipAmountValue));


            totalAmountValue =userBillTotal+tipAmountValue;
            //totalAmount.setText(String.valueOf("$"+totalAmountValue));
            totalAmount.setText(String.format("$"+"%.2f",totalAmountValue));

            splitCalculation();


        } else if (checked== R.id.radioButton_Tip15){

            tipAmountValue= userBillTotal*0.15;
            //tipAmount.setText(String.valueOf("$"+tipAmountValue));
            tipAmount.setText(String.format("$"+"%.2f",tipAmountValue));

            totalAmountValue =userBillTotal+tipAmountValue;
            //totalAmount.setText(String.valueOf("$"+totalAmountValue));
            totalAmount.setText(String.format("$"+"%.2f",totalAmountValue));

            splitCalculation();


        }else if (checked== R.id.radioButton_Tip18){

            tipAmountValue= userBillTotal*0.18;
            //tipAmount.setText(String.valueOf("$"+tipAmountValue));
            tipAmount.setText(String.format("$"+"%.2f",tipAmountValue));

            totalAmountValue =userBillTotal+tipAmountValue;
            //totalAmount.setText(String.valueOf("$"+totalAmountValue));
            totalAmount.setText(String.format("$"+"%.2f",totalAmountValue));

            splitCalculation();


        }else if (checked== R.id.radioButton_TipCustom){

            customSeekValue=customTip.getProgress();
            tipAmountValue= userBillTotal*(customSeekValue/100);
            //tipAmount.setText(String.valueOf("$"+tipAmountValue));
            tipAmount.setText(String.format("$"+"%.2f",tipAmountValue));

            totalAmountValue =userBillTotal+tipAmountValue;
            //totalAmount.setText(String.valueOf("$"+totalAmountValue));
            totalAmount.setText(String.format("$"+"%.2f",totalAmountValue));

            splitCalculation();


        }

    }
}