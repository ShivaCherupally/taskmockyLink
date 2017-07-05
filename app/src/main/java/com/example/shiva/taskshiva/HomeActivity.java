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

		mRecycler_view= (RecyclerView) findViewById(R.id.recycler_view);
		mRecycler_view.setHasFixedSize(true);
		mContext = HomeActivity.this;
		gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
		mRecycler_view.setLayoutManager(gaggeredGridLayoutManager);
		if (Utility.isOnline(mContext)){
			queue = Volley.newRequestQueue(this);
			url = "http://www.mocky.io/v2/595c894b110000b700098815";
		}else {
			Toast.makeText(mContext, "Please Check internet Connection", Toast.LENGTH_SHORT).show();
		}


		/*// Instantiate the RequestQueue.
		RequestQueue queue = Volley.newRequestQueue(this);
		String url = "http://104.211.96.182:91/Service.asmx/Authenticate_User";

		mParams = new HashMap<String, String>();
		mParams.put("ptntcode", "SHRAF2510882");
		mParams.put("pwd", "SHRA");

		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
		                                                new Response.Listener<String>()
		                                                {
			                                                @Override
			                                                public void onResponse(String response)
			                                                {
				                                                response = response.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
				                                                response = response.replace("<string xmlns=\"http://tempuri.org/\">", "");
				                                                response = response.replace("<string xmlns=\"http://220.227.57.139:81/\">", "");
				                                                response = response.replace("</string>", "");
				                                                Log.e("response", response + "");
				                                                try
				                                                {
					                                                JSONObject jobj = new JSONObject(response);
					                                                String code = jobj.getString("code");
					                                                Log.e("code", code + "");
					                                                String msg = jobj.getString("msg");
					                                                Log.e("msg", msg + "");
					                                                JSONArray jsonArray = jobj.getJSONArray("data");
					                                                Log.e("jsonArray", jsonArray + "");
					                                                for (int i = 0; i < jsonArray.length(); i++)
					                                                {
						                                                String userId = jsonArray.getJSONObject(i).getString("PTNT_CD");
						                                                Log.e("userId", userId + "");
						                                                String emailid = jsonArray.getJSONObject(i).getString("EMAIL_ID");
						                                                Log.e("emailid", emailid + "");
						                                                String city = jsonArray.getJSONObject(i).getString("CITY");
						                                                Log.e("city", city + "");
					                                                }


//
//
//						OffersListItemData offerlist_data = new OffersListItemData();
//						offerlist_data.setID(jArray.getJSONObject(i).getInt(Constants.offer_id));
//						offerlist_data.setDESCRIPTION(jArray.getJSONObject(i).getString(Constants.offer_description));
//						offerlist_data.setIMAGE_URL(jArray.getJSONObject(i).getString(Constants.offer_image_url));
//						offerlist_data.setCREATED_DATE(jArray.getJSONObject(i).getString(Constants.offer_created_date));
//						offerlist_data.setACTIVE_FLAG(jArray.getJSONObject(i).getString(Constants.offer_active_date));
//						offerlist_data.setNAME(jArray.getJSONObject(i).getString(Constants.offer_name));
//						offerlist_data.setFRM_DT(jArray.getJSONObject(i).getString(Constants.offer_from_date));
//						offerlist_data.setTO_DT(jArray.getJSONObject(i).getLong(Constants.offer_to_date));
//						offerlist_data.setSEQUENCE(jArray.getJSONObject(i).getString(Constants.offer_sequence));
//						offerlist_data.setTESTCODE(jArray.getJSONObject(i).getString(Constants.offer_test_code));
//						offerlist_data.setDISCOUNT_TYPE(jArray.getJSONObject(i).getString(Constants.offer_discount_type));
//						offerlist_data.setPERC_AMT(jArray.getJSONObject(i).getString(Constants.offer_perc_amt));
//						offerlist_data.setACTION(jArray.getJSONObject(i).getString(Constants.offer_action));
//						offerlist_data.setBANNER_FLG(jArray.getJSONObject(i).getString(Constants.offer_banner_flag));
//						if (jArray.getJSONObject(i).getString(Constants.offer_banner_flag).equalsIgnoreCase("N"))
//						{
//							mOffersListItemData.add(offerlist_data);
//						}
//
//					}
				                                                }
				                                                catch (Exception e)
				                                                {
					                                                e.printStackTrace();
				                                                }

				                                                // Display the first 500 characters of the response string.
//				                                                mTextView.setText("Response is: " + response);
			                                                }
		                                                }, new Response.ErrorListener()


		{
			@Override
			public void onErrorResponse(VolleyError error)
			{
//				mTextView.setText("That didn't work!");
			}
		})
		{
			@Override
			protected Map<String, String> getParams()
			{
				return mParams;
			}
		};

	// Add the request to the RequestQueue.
		queue.add(stringRequest);*/





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
				                                                   }
				                                                   if (listViewItems.size()!=0){
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
//		List<ItemObjects> gaggeredList = getListItemData();


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
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
/*
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}*/

	private List<ItemObjects> getListItemData()
	{
		List<ItemObjects> listViewItems = new ArrayList<ItemObjects>();
		listViewItems.add(new ItemObjects("Alkane", String.valueOf(R.drawable.hearts)));
		listViewItems.add(new ItemObjects("Alkane", String.valueOf(R.drawable.hearts)));
		listViewItems.add(new ItemObjects("Alkane", String.valueOf(R.drawable.hearts)));
		listViewItems.add(new ItemObjects("Alkane", String.valueOf(R.drawable.hearts)));
		listViewItems.add(new ItemObjects("Alkane", String.valueOf(R.drawable.hearts)));
		listViewItems.add(new ItemObjects("Alkane", String.valueOf(R.drawable.hearts)));
		listViewItems.add(new ItemObjects("Alkane", String.valueOf(R.drawable.hearts)));
		listViewItems.add(new ItemObjects("Alkane", String.valueOf(R.drawable.hearts)));
		listViewItems.add(new ItemObjects("Alkane", String.valueOf(R.drawable.hearts)));
		listViewItems.add(new ItemObjects("Alkane", String.valueOf(R.drawable.hearts)));
		listViewItems.add(new ItemObjects("Alkane", String.valueOf(R.drawable.hearts)));
		listViewItems.add(new ItemObjects("Alkane", String.valueOf(R.drawable.hearts)));
		listViewItems.add(new ItemObjects("Alkane", String.valueOf(R.drawable.hearts)));
		return listViewItems;
	}

}
