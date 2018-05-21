package com.myapplicationdev.android.p05_ndpsongsinsertsong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {

    Spinner spinner;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<Song> songs;
    Button btnFiveStars;

    ArrayList<Integer> years;
    ArrayAdapter<Integer> aaYears;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        lv = (ListView) findViewById(R.id.lv);
        btnFiveStars = findViewById(R.id.buttonFiveStar);
        spinner = findViewById(R.id.spinner);

        DBHelper db = new DBHelper(SongListActivity.this);

        songs = db.getAllSongs();
        years = db.getYears();

        aaYears = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item, years);
        spinner.setAdapter(aaYears);

        aa = new SongsArrayAdapter(SongListActivity.this, R.layout.row, songs);
        lv.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int year = years.get(position);
                DBHelper dbh = new DBHelper(SongListActivity.this);
                songs.clear();
                songs.addAll(dbh.getSongOfYear(year));
                aa.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Intent i = new Intent(SongListActivity.this, EditActivity.class);
                Song data = songs.get(position);
                i.putExtra("data", data);
                startActivityForResult(i, 9);
            }
        });

        btnFiveStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SongListActivity.this);
                songs.clear();
                songs.addAll(dbh.getSongFiveStar());
                aa.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 9){
            DBHelper db = new DBHelper(SongListActivity.this);
            songs.clear();
            songs.addAll(db.getAllSongs());
            aa.notifyDataSetChanged();
        }
    }
}
