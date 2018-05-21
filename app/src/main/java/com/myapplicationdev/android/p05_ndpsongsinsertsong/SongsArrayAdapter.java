package com.myapplicationdev.android.p05_ndpsongsinsertsong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongsArrayAdapter extends ArrayAdapter<Song> {
	Context context;
	ArrayList<Song> songs;
	int resource;
	ImageView iv1, iv2, iv3, iv4, iv5;
	TextView tvTitle, tvSingers, tvYear;

	public SongsArrayAdapter(Context context, int resource, ArrayList<Song> notes) {
		super(context, resource, notes);
		this.context = context;
		this.songs = notes;
		this.resource = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(resource, parent, false);

		//Match the UI components with Java variables

		Song song = songs.get(position);

		String title = song.getTitle();
        String singers = song.getSingers();
        int year = song.getYear();
        int stars = song.getStars();
		tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        tvSingers = (TextView) rowView.findViewById(R.id.tvSingers);
        tvYear = (TextView) rowView.findViewById(R.id.tvYear);
		iv1 = (ImageView) rowView.findViewById(R.id.imageView1star);
        iv2 = (ImageView) rowView.findViewById(R.id.imageView2star);
        iv3 = (ImageView) rowView.findViewById(R.id.imageView3star);
        iv4 = (ImageView) rowView.findViewById(R.id.imageView4star);
        iv5 = (ImageView) rowView.findViewById(R.id.imageView5star);

        tvTitle.setText(title);
        tvSingers.setText(singers);
        tvYear.setText(""+year);


		//Check if the property for starts >= 5, if so, "light" up the stars
		if (stars >= 5) {
			iv5.setImageResource(android.R.drawable.btn_star_big_on);
			iv4.setImageResource(android.R.drawable.btn_star_big_on);
			iv3.setImageResource(android.R.drawable.btn_star_big_on);
			iv2.setImageResource(android.R.drawable.btn_star_big_on);
			iv1.setImageResource(android.R.drawable.btn_star_big_on);
		}else if(stars == 4){
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if(stars == 3){
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if(stars == 2){
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else{
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }

		return rowView;
	}



}
