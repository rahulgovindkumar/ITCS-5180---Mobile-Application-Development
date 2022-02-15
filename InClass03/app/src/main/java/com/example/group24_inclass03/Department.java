/*Class 03
        Grouping1-Group27 Grouping2-24
        Name: Rahul Govindkumar



        */
package com.example.group24_inclass03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Department extends AppCompatActivity {

    RadioGroup radioDeptOption;
    RadioButton radioButtonDeptSelected;
    RadioButton defaultDeptOption;
    final static  public String DEPT_SELECTED = "DEPT_SELECTED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        radioDeptOption=findViewById(R.id.radioGroup_DeptOptions);

        defaultDeptOption = findViewById(R.id.radioButton_ComputerScience);
        defaultDeptOption.setChecked(true);



        findViewById(R.id.button_Cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();

            }
        });

        findViewById(R.id.button_DeptSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get selected radio button from radioGroup
                int selectedId = radioDeptOption.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButtonDeptSelected = (RadioButton) findViewById(selectedId);

                String deptOptionValue = radioButtonDeptSelected.getText().toString();

                Intent intent = new Intent();

                intent.putExtra(DEPT_SELECTED, deptOptionValue);
                setResult(RESULT_OK, intent);



                finish();

            }
        });

    }
}