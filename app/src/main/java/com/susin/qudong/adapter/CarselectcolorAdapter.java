package com.susin.qudong.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.susin.qudong.R;
import com.susin.qudong.tools.RoundImageView;

import java.util.List;

public class CarselectcolorAdapter extends BaseAdapter{
	private List<String> list = null;
	private Context mContext;
	private int i=1;

	public CarselectcolorAdapter(Context mContext, List<String> list) {
		this.mContext = mContext;
		this.list = list;
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.item_carcolor, null);
			viewHolder.ivColor = (RoundImageView) view.findViewById(R.id.iv_carcolor);
			viewHolder.tvColor = (TextView) view.findViewById(R.id.tv_carcolor);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		if (list.get(position).contains(",")){
			String str[]=list.get(position).split(",");
			Log.i("color","colorlist2===="+str[0]);
			Log.i("color","colorlist2===="+str[1]);
			viewHolder.tvColor.setText(str[0]);
//			Picasso.with(mContext).load(Color.parseColor(str[1])).error(R.mipmap.ic_launcher).transform(new CircleTransform()).into(viewHolder.ivColor);
//			viewHolder.ivColor.setBackgroundColor(Color.parseColor(str[1]));
		}else{

		}
		return view;
	}

	final static class ViewHolder {
		RoundImageView ivColor;
		TextView tvColor;
	}

}