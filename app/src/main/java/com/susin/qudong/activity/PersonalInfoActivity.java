package com.susin.qudong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.susin.qudong.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
    }

    @OnClick({ R.id.personnalset_back})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.personnalset_back:
                finish();
                break;
            default:
                break;
        }
    }
}
