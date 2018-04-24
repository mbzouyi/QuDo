package com.susin.qudong.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.susin.qudong.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GuideActivity extends Activity implements OnClickListener {
    @BindView(R.id.guide_btn)
	Button button;
    @BindView(R.id.guide_pager)
	ViewPager view_pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		Window win = getWindow();
//		TitleUtils.setTranslucentStatus(this, win);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		ButterKnife.bind(this);

		view_pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				if (position == 2) {
					button.setVisibility(View.VISIBLE);
					button.setEnabled(true);
				}else{
					button.setVisibility(View.GONE);
					button.setEnabled(false);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {}
			
		});
		
		initImageView();
	}

	private void initImageView() {
		List<View> lists = new ArrayList<>();
		
		ImageView image1 = new ImageView(this);
		ImageView image2 = new ImageView(this);
		ImageView image3 = new ImageView(this);
		
		image1.setBackgroundResource(R.drawable.guide_1);
		image2.setBackgroundResource(R.drawable.guide_2);
		image3.setBackgroundResource(R.drawable.guide_3);
		
		lists.add(image1);
		lists.add(image2);
		lists.add(image3);
		
		ViewPageAdapter pagerAdapter = new ViewPageAdapter(lists);
		view_pager.setAdapter(pagerAdapter);
		
		button.setOnClickListener(this);
		
	}
	
	private class ViewPageAdapter extends PagerAdapter{
		private List<View> pages;
		
		public ViewPageAdapter(List<View> lists){
			this.pages = lists;
		}
		
		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager)container).removeView(pages.get(position));
		}
		
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager)container).addView(pages.get(position));
			return pages.get(position);
			
		}

		@Override
		public int getCount() {
			return pages.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		
	}

	@Override
	public void onClick(View v) {
			startActivity(new Intent(GuideActivity.this, LoginActivity.class));
			finish();
	}
	
	
	
}
