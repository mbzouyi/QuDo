package com.susin.qudong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.susin.qudong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindCarActivity extends AppCompatActivity {

    @BindView(R.id.lv_mycar)
    ListView lv_mycar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_car);
        ButterKnife.bind(this);
    }

    @OnClick({ R.id.mycar_back ,R.id.bt_addcar})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.mycar_back:
                finish();
                break;
            case R.id.bt_addcar://新增车型
                Intent intent=new Intent(this,AddCarsortActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
