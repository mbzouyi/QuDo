package com.susin.qudong.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.susin.qudong.R;
import com.susin.qudong.adapter.CarAdapter;
import com.susin.qudong.application.Myapplication;
import com.susin.qudong.model.CheXing;
import com.susin.qudong.tools.PreloadDialog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityCheXing extends Activity{

	private StringRequest stringRequest;
	private ListView lv_chexing;
	private List<CheXing.Car.Car1> list=new ArrayList<CheXing.Car.Car1>();
	CarAdapter adapter;
	private String bid;
	private PreloadDialog dialogLoad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chexing);
		ButterKnife.bind(this);
		
		lv_chexing = (ListView) findViewById(R.id.lv_chexing);
		
		Intent intent=getIntent();
		bid=intent.getStringExtra("bid");
		
		dialogLoad = new PreloadDialog(this);
		dialogLoad.show();
		
		getdata();
		initListview();
	}

	private void initListview() {
		lv_chexing.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Myapplication.list=list.get(position).getList();
				Myapplication.parentname1=list.get(position).getParentname();
				Myapplication.parentname2=list.get(position).getFullname();
				Myapplication.parentname2jian=list.get(position).getName();
//				Myapplication.carId=list.get(position).getId();
				Myapplication.carname="";
				Intent intent=new Intent(ActivityCheXing.this,AddCarsortActivity.class);
				intent.putExtra("fromcar", true);
				startActivity(intent);
			}
		});
	}

	@OnClick({R.id.chexi_back})
	public void click(View v){
		Intent intent;
		switch (v.getId()) {
		case R.id.chexi_back:
			this.finish();
			break;
		default:
			break;
		}
	}
	
	private void getdata() {
		 RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
			JsonObjectRequest jsonObjRequest1 = new JsonObjectRequest(Request.Method.GET, "http://jisucxdq.market.alicloudapi.com/car/carlist?parentid="+bid,
		            new Response.Listener<JSONObject>() {  
		                @Override  
		                public void onResponse(JSONObject response) {  
		                        Log.i("car", "car11===="+response); 
		                        dialogLoad.dismiss();
		        					CheXing chexing=new Gson().fromJson(response.toString(),
		        							CheXing.class);
		        					int i=chexing.getResult().size();
		        					for (int j = 0; j < i; j++) {
		        						list.addAll(chexing.getResult().get(j).getCarlist());
		        						for (int k = 0; k < chexing.getResult().get(j).getCarlist().size(); k++) {
		        							chexing.getResult().get(j).getCarlist().get(k).setParentname(chexing.getResult().get(j).getName());
		        						}
		        					}
//		        					list=chexing.getData().get(0).getCarlist();
		        					adapter=new CarAdapter(ActivityCheXing.this, list,chexing);
		        					lv_chexing.setAdapter(adapter);
		                        
		                }
		            }, new Response.ErrorListener() {  
		                @Override  
		                public void onErrorResponse(VolleyError error) {  
		                	dialogLoad.dismiss();
		                	Log.i("car","onErrorResponse, error===" + error);  
		                }  
		            }) {  
		        @Override  
		        public Map<String, String> getHeaders() throws AuthFailureError {  
		            HashMap<String, String> headers = new HashMap<String, String>();  
		            headers.put("Authorization", "APPCODE " +"c082bf8d70c547fb8d8f2f254dd034bf");  
		            return headers;  
		        } 
		        
		    }; 
		    mQueue.add(jsonObjRequest1);  
		    
		    mQueue.start();
		
	}
}
