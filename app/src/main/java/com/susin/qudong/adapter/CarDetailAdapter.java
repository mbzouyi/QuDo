package com.susin.qudong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.susin.qudong.R;
import com.susin.qudong.application.Myapplication;
import com.susin.qudong.model.CheXing;
import com.susin.qudong.model.GroupMemberBean;

import java.util.List;

public class CarDetailAdapter extends BaseAdapter implements SectionIndexer {
	private List<GroupMemberBean> list = null;
	private List<CheXing.Car.Car1.Car2> list1 = null;
	private CheXing chexing;
	private Context mContext;
	private int i=1;

	public CarDetailAdapter(Context mContext, List<CheXing.Car.Car1.Car2> list) {
		this.mContext = mContext;
		this.list1 = list;
	}
	
	public CarDetailAdapter(Context mContext, List<CheXing.Car.Car1> list1, CheXing chexing) {
		this.mContext = mContext;
//		this.list1 = list1;
		this.chexing=chexing;
	}

	/**
	 * 当ListView数据发生变化时,调用此方法来更新ListView
	 * 
	 * @param list
	 */
//	public void updateListView(List<GroupMemberBean> list) {
//		this.list = list;
//		notifyDataSetChanged();
//	}
	
//	public void updateListView(List<Chepai> list1) {
//		this.list1 = list1;
//		notifyDataSetChanged();
//	}

	public int getCount() {
		return this.list1.size();
	}

	public Object getItem(int position) {
		return list1.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		CheXing.Car.Car1.Car2 car=list1.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.item_car, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		viewHolder.tvTitle.setText(this.list1.get(position).getName());
		
		if (position==0) {
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(Myapplication.parentname2);
		}else{
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
		
		return view;

	}

	final static class ViewHolder {
		TextView tvLetter;
		TextView tvTitle;
	}

	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	public int getSectionForPosition(int position) {
		return position;
//		return list.get(position).getSortLetters().charAt(0);
//		return list1.get(position).getInitial().charAt(0);
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	public int getPositionForSection(int section) {
//		for (int i = 0; i < getCount(); i++) {
////			String sortStr = list.get(i).getSortLetters();
//			String sortStr = list1.get(i).getInitial();
//			char firstChar = sortStr.toUpperCase().charAt(0);
//			if (firstChar == section) {
//				return i;
//			}
//		}

		return -1;
	}

//	/**
//	 * 提取英文的首字母，非英文字母用#代替。
//	 * 
//	 * @param str
//	 * @return
//	 */
//	private String getAlpha(String str) {
//		String sortStr = str.trim().substring(0, 1).toUpperCase();
//		// 正则表达式，判断首字母是否是英文字母
//		if (sortStr.matches("[A-Z]")) {
//			return sortStr;
//		} else {
//			return "#";
//		}
//	}

	@Override
	public Object[] getSections() {
		return null;
	}

}