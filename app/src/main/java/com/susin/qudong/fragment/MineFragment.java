package com.susin.qudong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.susin.qudong.R;
import com.susin.qudong.activity.BindCarActivity;
import com.susin.qudong.activity.GradeActivity;
import com.susin.qudong.activity.MyFabuActivity;
import com.susin.qudong.activity.MyRewardActivity;
import com.susin.qudong.activity.NewsActivity;
import com.susin.qudong.activity.OrderActivity;
import com.susin.qudong.activity.SetActivity;
import com.susin.qudong.activity.SuggestActivity;
import com.susin.qudong.activity.YouhuiquanActivity;
import com.susin.qudong.activity.YoukaActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends Fragment {

    @BindView(R.id.tv_mine_myreward)
    TextView tv_mine_myreward;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({ R.id.ll_mine_myreward,R.id.tv_signin ,R.id.rl_mine_youhuiquan,R.id.rl_mine_youka,R.id.rl_mine_suggest,R.id.rl_mine_news,R.id.rl_mine_set,
                R.id.ll_mine_daifukuan , R.id.ll_mine_daifuwu ,R.id.ll_mine_daipingjia ,R.id.ll_mine_hislist ,R.id.ll_mine_bindcar ,R.id.rl_mine_bindcar,
                R.id.rl_mine_renwu ,R.id.rl_mine_fabu ,R.id.iv_mine_vip})
    public void click(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_mine_myreward:
                intent=new Intent(getActivity(), MyRewardActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_signin:
                Toast.makeText(getActivity(),"签到成功",0).show();
                break;
            case R.id.iv_mine_vip:
                intent=new Intent(getActivity(), GradeActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_mine_daifukuan:
                intent=new Intent(getActivity(),OrderActivity.class);
                intent.putExtra("tag",1);
                startActivity(intent);
                break;
            case R.id.ll_mine_daifuwu:
                intent=new Intent(getActivity(),OrderActivity.class);
                intent.putExtra("tag",2);
                startActivity(intent);
                break;
            case R.id.ll_mine_daipingjia:
                intent=new Intent(getActivity(),OrderActivity.class);
                intent.putExtra("tag",3);
                startActivity(intent);
                break;
            case R.id.ll_mine_hislist:
                intent=new Intent(getActivity(),OrderActivity.class);
                intent.putExtra("tag",4);
                startActivity(intent);
                break;
            case R.id.ll_mine_bindcar:
                intent=new Intent(getActivity(), BindCarActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_mine_bindcar:
                intent=new Intent(getActivity(),BindCarActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_mine_youka:
                intent=new Intent(getActivity(),YoukaActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_mine_youhuiquan:
                intent=new Intent(getActivity(), YouhuiquanActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_mine_renwu:
//                intent=new Intent(getActivity(), SuggestActivity.class);
//                startActivity(intent);
                break;
            case R.id.rl_mine_fabu:
                intent=new Intent(getActivity(), MyFabuActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_mine_news:
                intent=new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_mine_suggest:
                intent=new Intent(getActivity(), SuggestActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_mine_set:
                intent=new Intent(getActivity(), SetActivity.class);
                startActivity(intent);
                break;
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
