package com.susin.qudong.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
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
import com.susin.qudong.application.Myapplication;
import com.susin.qudong.model.Chepai;
import com.susin.qudong.model.GroupMemberBean;
import com.susin.qudong.tools.CharacterParser;
import com.susin.qudong.tools.ClearEditText;
import com.susin.qudong.tools.PinyinComparator;
import com.susin.qudong.tools.PreloadDialog;
import com.susin.qudong.tools.SideBar;
import com.susin.qudong.tools.SortGroupMemberAdapter;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityChepinpai extends Activity implements SectionIndexer {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortGroupMemberAdapter adapter;
	private ClearEditText mClearEditText;

	private LinearLayout titleLayout;
	private TextView title;
	private TextView tvNofriends;
	/**
	 * 上次第一个可见元素，用于滚动时记录标识。
	 */
	private int lastFirstVisibleItem = -1;
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<GroupMemberBean> SourceDateList;
	private List<Chepai> list=new ArrayList<Chepai>();
	private List<Chepai> cList=new ArrayList<Chepai>();

	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;
	private StringRequest stringRequest;
	private RequestQueue mQueue;
	private PreloadDialog dialogLoad;
	
	private List<Chepai> filterDateList;//筛选后的list

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_friends);

		ButterKnife.bind(this);

		dialogLoad = new PreloadDialog(this);
		dialogLoad.show();
		
		//进入时删除缓存数据
		Myapplication.parentname1="";
		Myapplication.parentname2="";
		Myapplication.parentname2jian="";
		Myapplication.parentname="";
		Myapplication.carname="";
		
		getData();
//		initViews();
	}

	@OnClick({R.id.chepinpai_back})
	public void click(View v){
		switch (v.getId()) {
		case R.id.chepinpai_back:
			finish();
			break;
		default:
			break;
		}
	}
	
	private void getData() {
		mQueue=Volley.newRequestQueue(getApplicationContext());
		JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, "http://jisucxdq.market.alicloudapi.com/car/brand",
	            new Response.Listener<JSONObject>() {  
	                @Override  
	                public void onResponse(JSONObject response) { 
	                	dialogLoad.dismiss();
	                    try {  
	                        Log.i("car", "car===="+response); 
	                        JSONArray array=response.getJSONArray("result");
							Log.i("aa", "get请求====" + array.getJSONObject(0).toString());
							 for (int i = 0; i < array.length(); i++) {
								 Chepai chepai=new Gson().fromJson(array.getJSONObject(i).toString(),
											Chepai.class);
								 list.add(chepai);
//								 Log.i("ll", "ll===="+chepai.getName());
							} 
							Log.i("ll", "ll===="+list.size());
							initViews();
	                    } catch (Exception e) {  
	                        e.printStackTrace();  
	                    }  
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
	            HashMap<String, String> headers = new HashMap<>();
	            headers.put("Authorization", "APPCODE " +"c082bf8d70c547fb8d8f2f254dd034bf");  
	            return headers;
	        }  
	    }; 
	    
	    mQueue.add(jsonObjRequest);  
	    
	    mQueue.start();
	}

	private void initViews() {
		titleLayout = (LinearLayout) findViewById(R.id.title_layout);
		title = (TextView) this.findViewById(R.id.title_layout_catalog);
		tvNofriends = (TextView) this
				.findViewById(R.id.title_layout_no_friends);
		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);

		// 设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position);
				}

			}
		});

		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
				if (mClearEditText.length()==0) {//筛选框是否为空
					Myapplication.parentname=((Chepai) adapter.getItem(position)).getName();
					Intent intent=new Intent(ActivityChepinpai.this,ActivityCheXing.class);
					intent.putExtra("bid", list.get(position).getId());
					startActivity(intent);
				}else {
					Myapplication.parentname=((Chepai) adapter.getItem(position)).getName();
					Intent intent=new Intent(ActivityChepinpai.this,ActivityCheXing.class);
					intent.putExtra("bid", filterDateList.get(position).getId());
					startActivity(intent);
				}
			}
		});

//		SourceDateList = filledData(getResources().getStringArray(R.array.date));

		// 根据a-z进行排序源数据
//		Collections.sort(SourceDateList, pinyinComparator);
//		adapter = new SortGroupMemberAdapter(this, SourceDateList);
//		Collections.sort(list, pinyinComparator);
		Collections.sort(list,new Comparator<Chepai>() {
			@Override
			public int compare(Chepai lhs, Chepai rhs) {
				return lhs.getInitial().compareTo(rhs.getInitial());
			}
			
		});
		adapter = new SortGroupMemberAdapter(this, list,1);
		sortListView.setAdapter(adapter);
		sortListView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				int section = getSectionForPosition(firstVisibleItem);
				int nextSection = getSectionForPosition(firstVisibleItem + 1);
				int nextSecPosition = getPositionForSection(+nextSection);
				if (firstVisibleItem != lastFirstVisibleItem) {
					MarginLayoutParams params = (MarginLayoutParams) titleLayout
							.getLayoutParams();
					params.topMargin = 0;
					titleLayout.setLayoutParams(params);
//					title.setText(SourceDateList.get(
//							getPositionForSection(section)).getSortLetters());
					title.setText(list.get(
							getPositionForSection(section)).getInitial());
				}
				if (nextSecPosition == firstVisibleItem + 1) {
					View childView = view.getChildAt(0);
					if (childView != null) {
						int titleHeight = titleLayout.getHeight();
						int bottom = childView.getBottom();
						MarginLayoutParams params = (MarginLayoutParams) titleLayout
								.getLayoutParams();
						if (bottom < titleHeight) {
							float pushedDistance = bottom - titleHeight;
							params.topMargin = (int) pushedDistance;
							titleLayout.setLayoutParams(params);
						} else {
							if (params.topMargin != 0) {
								params.topMargin = 0;
								titleLayout.setLayoutParams(params);
							}
						}
					}
				}
				lastFirstVisibleItem = firstVisibleItem;
			}
		});
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

		// 根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 这个时候不需要挤压效果 就把他隐藏掉
				titleLayout.setVisibility(View.GONE);
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
	}

	/**
	 * 为ListView填充数据
	 * 
	 * @param date
	 * @return
	 */
	private List<GroupMemberBean> filledData(String[] date) {
		List<GroupMemberBean> mSortList = new ArrayList<GroupMemberBean>();

		for (int i = 0; i < date.length; i++) {
			GroupMemberBean sortModel = new GroupMemberBean();
			sortModel.setName(date[i]);
			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;

	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		filterDateList = new ArrayList<Chepai>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = list;
			tvNofriends.setVisibility(View.GONE);
		} else {
			filterDateList.clear();
			for (Chepai sortModel : list) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// 根据a-z进行排序
//		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
//		adapter.updateListView(list);
		if (filterDateList.size() == 0) {
			tvNofriends.setVisibility(View.VISIBLE);
		}
	}
	

	@Override
	public Object[] getSections() {
		return null;
	}

	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	public int getSectionForPosition(int position) {
		return list.get(position).getInitial().charAt(0);
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < list.size(); i++) {
			String sortStr = list.get(i).getInitial();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		return -1;
	}
}
