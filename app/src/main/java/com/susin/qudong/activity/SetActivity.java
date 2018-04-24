package com.susin.qudong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.susin.qudong.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
    }

    @OnClick({ R.id.set_back ,R.id.rl_set_personnalinfo ,R.id.rl_set_newsset })
    public void click(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.set_back:
                finish();
                break;
            case R.id.rl_set_personnalinfo:
                intent=new Intent(this,PersonalInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_set_newsset:
                intent=new Intent(this,NewsSetActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
