package com.susin.qudong.tools;

import com.squareup.picasso.Picasso;
import com.susin.qudong.R;
import com.susin.qudong.model.Chepai;
import com.susin.qudong.model.GroupMemberBean;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

public class SortGroupMemberAdapter extends BaseAdapter implements SectionIndexer {
	private List<GroupMemberBean> list = null;
	private List<Chepai> list1 = null;
	private int i=0;
	private Context mContext;

	public SortGroupMemberAdapter(Context mContext, List<GroupMemberBean> list) {
		this.mContext = mContext;
		this.list = list;
	}
	
	public SortGroupMemberAdapter(Context mContext, List<Chepai> list1,int i) {
		this.mContext = mContext;
		this.list1 = list1;
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
	
	public void updateListView(List<Chepai> list1) {
		this.list1 = list1;
		notifyDataSetChanged();
	}

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
//		final GroupMemberBean mContent = list.get(position);
		Chepai chepai=list1.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.activity_group_member_item, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
			viewHolder.iv_chepai = (ImageView) view.findViewById(R.id.iv_chepai);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		// 根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);

		// 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
		if (position == getPositionForSection(section)) {
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
//			viewHolder.tvLetter.setText(mContent.getSortLetters());
			viewHolder.tvLetter.setText(chepai.getInitial());
		} else {
			viewHolder.tvLetter.setVisibility(View.GONE);
		}

//		viewHolder.tvTitle.setText(this.list1.get(position).getName());
		viewHolder.tvTitle.setText(this.list1.get(position).getName());
		/*
		 * 加载图片
		 */
		 Picasso.with(mContext).load(list1.get(position).getLogo())
		 .into(viewHolder.iv_chepai);
		 
		Log.i("name", "name===="+list1.get(position).getName());
		return view;

	}

	final static class ViewHolder {
		TextView tvLetter;
		TextView tvTitle;
		ImageView iv_chepai;
	}

	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	public int getSectionForPosition(int position) {
//		return list.get(position).getSortLetters().charAt(0);
		return list1.get(position).getInitial().charAt(0);
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
//			String sortStr = list.get(i).getSortLetters();
			String sortStr = list1.get(i).getInitial();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * 提取英文的首字母，非英文字母用#代替。
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String sortStr = str.trim().substring(0, 1).toUpperCase();
		// 正则表达式，判断首字母是否是英文字母
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}