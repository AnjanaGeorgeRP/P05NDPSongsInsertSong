package com.myapplicationdev.android.p05_ndpsongsinsertsong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    TextView tvID;
    EditText editTextTitle, editTextSingers, editTextYear;
    Button btnUpdate, btnDelete, btnCancel;
    RadioButton rd1, rd2, rd3, rd4, rd5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        tvID = findViewById(R.id.tvid);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextSingers = findViewById(R.id.editTextSingers);
        editTextYear = findViewById(R.id.editTextYear);
        btnDelete = findViewById(R.id.buttonDelete);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnCancel = findViewById(R.id.buttonCancel);
        rd1 = findViewById(R.id.radio1);
        rd2 = findViewById(R.id.radio2);
        rd3 = findViewById(R.id.radio3);
        rd4 = findViewById(R.id.radio4);
        rd5 = findViewById(R.id.radio5);


        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        tvID.setText("ID: " + data.getId());
        editTextTitle.setText(data.getTitle());
        editTextSingers.setText(data.getSingers());
        editTextYear.setText(""+data.getYear());
        int selectedButtonId = data.getStars();
        if(selectedButtonId == 1){
            rd1.setChecked(true);
        }else if(selectedButtonId == 2){
            rd2.setChecked(true);
        }else if(selectedButtonId == 3){
            rd3.setChecked(true);
        }else if(selectedButtonId == 4){
            rd4.setChecked(true);
        }else{
            rd5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setTitle(editTextTitle.getText().toString().trim());
                data.setSingers(editTextSingers.getText().toString().trim());
                int year = Integer.parseInt(editTextYear.getText().toString().trim());
                data.setYear(year);
                RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupStars);
                int selectedButtonId = rg.getCheckedRadioButtonId();
                int star = 5;
                if(selectedButtonId == R.id.radio1){
                    star = 1;
                }else if(selectedButtonId == R.id.radio2){
                    star = 2;
                }else if(selectedButtonId == R.id.radio3){
                    star = 3;
                }else if(selectedButtonId == R.id.radio4){
                    star = 4;
                }
                data.setStars(star);
                dbh.updateSong(data);
                dbh.close();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(data.getId());
                dbh.close();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
