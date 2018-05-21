package com.myapplicationdev.android.p05_ndpsongsinsertsong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextTitle, editTextSingers, editTextYear;
    Button buttonInsert, buttonShowList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextSingers = findViewById(R.id.editTextSingers);
        editTextYear = findViewById(R.id.editTextYear);

        buttonInsert = findViewById(R.id.buttonInsert);
        buttonShowList = findViewById(R.id.buttonShowList);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString().trim();
                String singers = editTextSingers.getText().toString().trim();
                String yearString = editTextYear.getText().toString().trim();
                int year = Integer.parseInt(yearString);

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

                DBHelper db = new DBHelper(MainActivity.this);

                long row_affected = db.insertSong(title,singers,year,star);

                db.close();

                if (row_affected != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        buttonShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SongListActivity.class);
                startActivity(i);
            }
        });
    }
}
