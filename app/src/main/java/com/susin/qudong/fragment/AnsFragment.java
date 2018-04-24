package com.susin.qudong.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.susin.qudong.R;
import com.susin.qudong.activity.AnsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnsFragment extends Fragment {

//    @BindView(R.id.tv_find_ans_ans)
//    TextView tv_find_ans_ans;
//    @BindView(R.id.tv_find_ans_common1)
//    TextView tv_find_ans_common1;

    public AnsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ans, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({ R.id.tv_find_ans_ans ,R.id.tv_find_ans_common1})
    public void click(View v) {
        Intent intent;
        switch (v.getId()) {
           case R.id.tv_find_ans_ans:
                intent=new Intent(getActivity(), AnsActivity.class);
                startActivity(intent);
            break;
//            R.id.tv_find_ans_common1:
//
//            break;
            default:
                break;
        }
    }

}
