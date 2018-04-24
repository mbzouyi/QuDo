package com.susin.qudong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioGroup;
import com.susin.qudong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends AppCompatActivity {

    @BindView(R.id.rg_order)
    RadioGroup rg_order;
    @BindView(R.id.lv_order)
    ListView lv_order;

    int tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        tag=intent.getIntExtra("tag",1);

        initView();
    }

    public void initView(){
        rg_order.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_daifukuan:

                        break;
                    case R.id.rb_daifuwu:

                        break;
                    case R.id.rb_daipingjia:

                        break;
                    case R.id.rb_hislist:

                        break;
                }
            }
        });
        rg_order.check(tag);
    }

    @OnClick({ R.id.order_back})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.order_back:
                finish();
                break;
            default:
                break;
        }
    }

}
