package com.example.shiva.taskshiva;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Shiva on 7/5/2017.
 */

public class SolventRecyclerViewAdapter extends RecyclerView.Adapter<SolventViewHolders>
{

	private List<ItemObjects> itemList;
	private Context context;

	public SolventRecyclerViewAdapter(Context context, List<ItemObjects> itemList)
	{
		this.itemList = itemList;
		this.context = context;
	}

	@Override
	public SolventViewHolders onCreateViewHolder(ViewGroup parent, int viewType)
	{

		View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, null);
		SolventViewHolders rcv = new SolventViewHolders(layoutView);
		return rcv;
	}

	@Override
	public void onBindViewHolder(SolventViewHolders holder, int position)
	{
		holder.countryName.setText(itemList.get(position).getName());
		if (itemList.get(position).getPhotoUrl() != null)
		{
			//http://www.queness.com/resources/images/png/apple_ex.png
//			Picasso.with(this.context).load("http://vansgn.com/brandvale/wp-content/uploads/2012/06/Angry-Birds-290x290.png").into(holder.countryPhoto);
			Picasso.with(this.context).load(itemList.get(position).getPhotoUrl()).into(holder.countryPhoto);

		}

		Log.e("url", itemList.get(position).getPhotoUrl() + "");
//		holder.countryPhoto.setImageResource(Integer.parseInt(itemList.get(position).getPhotoUrl()));
	}

	@Override
	public int getItemCount()
	{
		return this.itemList.size();
	}


}
