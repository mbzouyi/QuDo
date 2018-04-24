package com.susin.qudong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.susin.qudong.R;
import com.susin.qudong.adapter.CarDetailAdapter;
import com.susin.qudong.application.Myapplication;
import com.susin.qudong.model.CheXing;
import com.susin.qudong.tools.PreloadDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_CheDetail extends Activity{
	private ListView lv_chedetail;
	private List<CheXing.Car.Car1.Car2> list=new ArrayList<>();
	private PreloadDialog dialogLoad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chedetail);
		ButterKnife.bind(this);
		
		lv_chedetail=(ListView) findViewById(R.id.lv_chedetail);
//		Intent intent=getIntent();
//		list=(List<Car2>) intent.getSerializableExtra("list");
		list= Myapplication.list;
		
//		dialogLoad = new PreloadDialog(this);
//		dialogLoad.show();
		
		initLv();
	}

	@OnClick({R.id.chexinghao_back})
	public void click(View v){
		Intent intent;
		switch (v.getId()) {
		case R.id.chexinghao_back:
			this.finish();
			break;
		default:
			break;
		}
	}
	
	private void initLv() {
		CarDetailAdapter adapter=new CarDetailAdapter(Activity_CheDetail.this, list);
		Log.i("ll", "ll===="+list.get(0).getName());
		lv_chedetail.setAdapter(adapter);
		
		lv_chedetail.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Myapplication.carname=list.get(position).getName();
				Myapplication.carId=list.get(position).getId();
				Intent intent=new Intent(Activity_CheDetail.this,AddCarsortActivity.class);
				startActivity(intent);
			}
		});
	}
}
