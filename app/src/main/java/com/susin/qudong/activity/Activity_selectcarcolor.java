package com.susin.qudong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.susin.qudong.R;
import com.susin.qudong.adapter.CarAdapter;
import com.susin.qudong.adapter.CarselectcolorAdapter;
import com.susin.qudong.application.Myapplication;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_selectcarcolor extends AppCompatActivity {

    @BindView(R.id.lv_selectcarcolor)
    ListView lv_selectcarcolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcarcolor);
        ButterKnife.bind(this);
        initView();
    }

    public void initView(){
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjRequest1 = new JsonObjectRequest(Request.Method.GET, "http://jisucxdq.market.alicloudapi.com/car/detail?carid="+ Myapplication.carId,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("car", "carcolor===="+response);
//                        CheXing chexing=new Gson().fromJson(response.toString(),
//                                CheXing.class);
                        try {
                            String color=response.getJSONObject("result").getJSONObject("body").getString("color");
                            init(color);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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

    public void init(String color){
        if (color.contains("|")) {
            String str[] = color.split("\\|");
            final List<String> list = Arrays.asList(str);
            CarselectcolorAdapter adapter = new CarselectcolorAdapter(this, list);
            lv_selectcarcolor.setAdapter(adapter);
            lv_selectcarcolor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String color[] = list.get(i).split(",");
                    Myapplication.color = color[1];
                    finish();
                }
            });
        }else{
            Toast.makeText(this,"暂未找到该车颜色",0).show();
        }
    }

    @OnClick({ R.id.selectcarcolor_back })
    public void click(View v) {
        switch (v.getId()) {
            case R.id.selectcarcolor_back:
                finish();
                break;
            default:
                break;
        }
    }
}
