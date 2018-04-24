package com.susin.qudong.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.susin.qudong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {

    @BindView(R.id.rg_find)
    RadioGroup rg_find;
    @BindView(R.id.rb_answers)
    RadioButton rb_answers;
    @BindView(R.id.rb_refer)
    RadioButton rb_refer;
    @BindView(R.id.vp_find)
    ViewPager vp_find;
    @BindView(R.id.iv_nav_indicator)
    ImageView iv_nav_indicator;

    private int currentIndicatorLeft;
    private int indicatorWidth;
    private TabFragmentPagerAdapter mAdapter;


    public FindFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        ButterKnife.bind(this, view);
        currentIndicatorLeft=0;
        initView();
        setListener();
        return view;
    }

    private void initView() {

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        indicatorWidth = dm.widthPixels / 4;

        ViewGroup.LayoutParams cursor_Params = iv_nav_indicator.getLayoutParams();
        cursor_Params.width = indicatorWidth;// 初始化滑动下标的宽
        iv_nav_indicator.setLayoutParams(cursor_Params);


        mAdapter = new TabFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        vp_find.setAdapter(mAdapter);
    }

    private void setListener() {

        vp_find.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                if (rg_find != null && rg_find.getChildCount() > position) {
                        switch (position) {
                            case 0:
                                rb_answers.setChecked(true);
                                break;
                            case 1:
                                rb_refer.setChecked(true);
                                break;
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        rg_find.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rb_answers:
                        TranslateAnimation animation = new TranslateAnimation(currentIndicatorLeft,
                                ( rg_find.getChildAt(0)).getLeft(), 0f, 0f);
                        animation.setInterpolator(new LinearInterpolator());
                        animation.setDuration(100);
                        animation.setFillAfter(true);

                        //执行位移动画
                        iv_nav_indicator.startAnimation(animation);
                        vp_find.setCurrentItem(0);// 选择某一页
                        //记录当前 下标的距最左侧的 距离
                        currentIndicatorLeft = ( rg_find.getChildAt(0)).getLeft();
                        break;
                    case R.id.rb_refer:
                        TranslateAnimation animation1 = new TranslateAnimation(currentIndicatorLeft,
                                ( rg_find.getChildAt(1)).getLeft(), 0f, 0f);
                        animation1.setInterpolator(new LinearInterpolator());
                        animation1.setDuration(100);
                        animation1.setFillAfter(true);

                        //执行位移动画
                        iv_nav_indicator.startAnimation(animation1);
                        vp_find.setCurrentItem(1);
                        //记录当前 下标的距最左侧的 距离
                        currentIndicatorLeft = ( rg_find.getChildAt(1)).getLeft();
                        break;
                }

            }
        });
    }

    public static class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            Fragment ft ;
            switch (arg0) {
                case 0:
                    ft = new AnsFragment();
                    break;
                case 1:
                    ft = new ReferFragment();
                    break;
                default:
                    ft = new AnsFragment();
                    break;
            }
            return ft;
        }

        @Override
        public int getCount() {

            return 2;
        }

    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
