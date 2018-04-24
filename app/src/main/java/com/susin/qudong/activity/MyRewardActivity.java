package com.susin.qudong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.susin.qudong.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyRewardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reward);

        ButterKnife.bind(this);

    }

    @OnClick({ R.id.reward_back })
    public void click(View v) {
        switch (v.getId()) {
            case R.id.reward_back:
                finish();
                break;
            default:
                break;
        }
    }

}
