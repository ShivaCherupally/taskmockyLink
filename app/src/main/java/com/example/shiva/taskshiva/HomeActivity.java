package com.example.shiva.taskshiva;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shiva on 7/5/2017.
 */

public class HomeActivity extends AppCompatActivity
{

	private StaggeredGridLayoutManager gaggeredGridLayoutManager;
	RecyclerView mRecycler_view;

	private Map<String, String> mParams;
	List<ItemObjects> listViewItems;
	ProgressDialog pd;
	Context mContext;
	RequestQueue queue;
	String url;

	String response;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);

		pd = new ProgressDialog(HomeActivity.this);
		pd.setMessage("Please wait...");
		pd.setIndeterminate(false);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.show();

		mRecycler_view = (RecyclerView) findViewById(R.id.recycler_view);
		mRecycler_view.setHasFixedSize(true);
		mContext = HomeActivity.this;
		gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
		mRecycler_view.setLayoutManager(gaggeredGridLayoutManager);

		if (Utility.isOnline(mContext))
		{
			queue = Volley.newRequestQueue(this);
			url = "http://www.mocky.io/v2/595c894b110000b700098815";
		}
		else
		{
			Toast.makeText(mContext, "Please Check internet Connection", Toast.LENGTH_SHORT).show();
		}

		// prepare the Request
		JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
		                                                   new Response.Listener<JSONArray>()
		                                                   {
			                                                   @Override
			                                                   public void onResponse(final JSONArray jsonArray)
			                                                   {
				                                                   Log.d("Response", jsonArray.toString());
				                                                   try
				                                                   {
					                                                   listViewItems = new ArrayList<ItemObjects>();
					                                                   for (int i = 0; i < jsonArray.length(); i++)
					                                                   {
						                                                   listViewItems.add(new ItemObjects(String.valueOf(jsonArray.getJSONObject(i).getInt("id")),
						                                                                                     jsonArray.getJSONObject(i).getString("kids_url")));
					                                                   }
				                                                   }
				                                                   catch (Exception e)
				                                                   {
					                                                   e.printStackTrace();
				                                                   }
				                                                   if (listViewItems.size() != 0)
				                                                   {
					                                                   pd.dismiss();
					                                                   setDataintoAdapter();
				                                                   }


			                                                   }
		                                                   },
		                                                   new Response.ErrorListener()

		                                                   {
			                                                   @Override
			                                                   public void onErrorResponse(VolleyError error)
			                                                   {
				                                                   pd.dismiss();
				                                                   Log.d("Error.Response", error.toString() + "");
			                                                   }
		                                                   }
		);

// add it to the RequestQueue
		queue.add(getRequest);
//	}

		RequestQueue queue = Volley.newRequestQueue(this);
		String url = "http://202.143.96.20/Orderstest/api/Services/LoginService?";

		// POST parameters
		Map<String, String> params = new HashMap<String, String>();
		params.put("Username", "BU0004");
		params.put("Password", "BU0004");

		JSONObject jsonObj = new JSONObject(params);

// Request a json response from the provided URL
		JsonObjectRequest jsonObjRequest = new JsonObjectRequest
				(Request.Method.POST, url, jsonObj, new Response.Listener<JSONObject>()
				{
					@Override
					public void onResponse(JSONObject response)
					{
						Log.e("response", response.toString());
						Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
					}
				},
				 new Response.ErrorListener()
				 {
					 @Override
					 public void onErrorResponse(VolleyError error)
					 {
						 Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
					 }
				 });

// Add the request to the RequestQueue.
		queue.add(jsonObjRequest);
	}

	private void setDataintoAdapter()
	{
		if (listViewItems.size() != 0)
		{
			SolventRecyclerViewAdapter rcAdapter = new SolventRecyclerViewAdapter(HomeActivity.this, listViewItems);
			mRecycler_view.setAdapter(rcAdapter);

		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return true;
	}

}
