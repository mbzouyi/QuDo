package com.susin.qudong.adapter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.susin.qudong.R;

public class GvZiAdapter extends BaseAdapter{
	public Context context;
	public List list;

	public GvZiAdapter(Context context,List list){
		this.context=context;
		this.list=list;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView==null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_carzi, null);
		}
		TextView tv_zi = (TextView) convertView.findViewById(R.id.tv_carzi_item);
		tv_zi.setText(list.get(position).toString());
		return convertView;
	}

}
