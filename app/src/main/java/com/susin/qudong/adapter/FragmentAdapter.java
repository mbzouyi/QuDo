package com.susin.qudong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.susin.qudong.fragment.FindFragment;
import com.susin.qudong.fragment.HomeFragment;
import com.susin.qudong.fragment.MineFragment;
import com.susin.qudong.fragment.ShopFragment;

public class FragmentAdapter extends FragmentStatePagerAdapter {

	public FragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {

		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new ShopFragment();
			break;
		case 2:
			fragment = new FindFragment();
			break;
		case 3:
			fragment = new MineFragment();
			break;
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return 4;
	}

}
