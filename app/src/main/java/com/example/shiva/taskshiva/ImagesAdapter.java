package com.example.shiva.taskshiva;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Shiva on 7/5/2017.
 */

public class ImagesAdapter extends RecyclerView.ViewHolder implements View.OnClickListener
{

	public TextView countryName;
	public ImageView countryPhoto;

	public ImagesAdapter(View itemView)
	{
		super(itemView);
		itemView.setOnClickListener(this);
		countryName = (TextView) itemView.findViewById(R.id.country_name);
		countryPhoto = (ImageView) itemView.findViewById(R.id.country_photo);
	}

	@Override
	public void onClick(View view)
	{
		Toast.makeText(view.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
	}
}
