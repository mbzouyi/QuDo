package com.susin.qudong.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.susin.qudong.R;
import com.susin.qudong.fragment.AnsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFabuActivity extends AppCompatActivity {

    @BindView(R.id.rg_fabu)
    RadioGroup rg_fabu;
    @BindView(R.id.rb_answers)
    RadioButton rb_answers;
    @BindView(R.id.rb_reply)
    RadioButton rb_reply;
    @BindView(R.id.vp_fabu)
    ViewPager vp_fabu;
    @BindView(R.id.iv_nav_indicator)
    ImageView iv_nav_indicator;
    @BindView(R.id.rl_nothing)
    RelativeLayout rl_nothing;

    private int currentIndicatorLeft;
    private int indicatorWidth;
    private TabFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fabu);

        ButterKnife.bind(this);
        currentIndicatorLeft=0;
        initView();
        setListener();
    }

    private void initView() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        indicatorWidth = dm.widthPixels / 4;

        ViewGroup.LayoutParams cursor_Params = iv_nav_indicator.getLayoutParams();
        cursor_Params.width = indicatorWidth;// 初始化滑动下标的宽
        iv_nav_indicator.setLayoutParams(cursor_Params);


        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
        vp_fabu.setAdapter(mAdapter);
    }

    private void setListener() {

        vp_fabu.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                if (rg_fabu != null && rg_fabu.getChildCount() > position) {
                    switch (position) {
                        case 0:
                            rb_answers.setChecked(true);
                            break;
                        case 1:
                            rb_reply.setChecked(true);
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

        rg_fabu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rb_answers:
                        TranslateAnimation animation = new TranslateAnimation(currentIndicatorLeft,
                                ( rg_fabu.getChildAt(0)).getLeft(), 0f, 0f);
                        animation.setInterpolator(new LinearInterpolator());
                        animation.setDuration(100);
                        animation.setFillAfter(true);

                        //执行位移动画
                        iv_nav_indicator.startAnimation(animation);
                        vp_fabu.setCurrentItem(0);// 选择某一页
                        //记录当前 下标的距最左侧的 距离
                        currentIndicatorLeft = ( rg_fabu.getChildAt(0)).getLeft();
                        break;
                    case R.id.rb_reply:
                        TranslateAnimation animation1 = new TranslateAnimation(currentIndicatorLeft,
                                ( rg_fabu.getChildAt(1)).getLeft(), 0f, 0f);
                        animation1.setInterpolator(new LinearInterpolator());
                        animation1.setDuration(100);
                        animation1.setFillAfter(true);

                        //执行位移动画
                        iv_nav_indicator.startAnimation(animation1);
                        vp_fabu.setCurrentItem(1);
                        //记录当前 下标的距最左侧的 距离
                        currentIndicatorLeft = ( rg_fabu.getChildAt(1)).getLeft();
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
//                    ft = new ReferFragment();
                    ft = new AnsFragment();
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

    @OnClick({ R.id.fabu_back})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.fabu_back:
                finish();
                break;
            default:
                break;
        }
    }
}
