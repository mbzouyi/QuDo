package com.susin.qudong.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.susin.qudong.R;
import com.susin.qudong.adapter.FragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.layout_content)
    FrameLayout layout_content;
    @BindView(R.id.main_radio)
    RadioGroup main_radio;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    private int index ;
    private FragmentAdapter fragments;

    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        index=0;
        fragments = new FragmentAdapter(getSupportFragmentManager());
        main_radio.setOnCheckedChangeListener(this);
        main_radio.check(R.id.rb_home);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                index = 0;
                break;
            case R.id.rb_shop:
                index = 1;
                break;
            case R.id.rb_find:
                index = 2;
                break;
            case R.id.rb_mine:
                index = 3;
                break;
            default:
                index=0;
                break;
        }

        Fragment fragment = (Fragment) fragments.instantiateItem(layout_content, index);
        fragments.setPrimaryItem(layout_content, 0, fragment);
        fragments.finishUpdate(layout_content);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次后退键退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            this.finish();
        }
    }

}
