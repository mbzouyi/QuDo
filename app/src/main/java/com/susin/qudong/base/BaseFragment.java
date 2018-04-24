package com.susin.qudong.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * ================================================
 * 版    本：1.0.0
 * 日    期：2017/9/19
 * 说    明：基类
 * ================================================
 */
public abstract class BaseFragment extends Fragment {
    public Context mContext;
//    public LoadingView mLoadingView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

//        mLoadingView = new LoadingView(mContext, null);
//        int color1 = ContextCompat.getColor(mContext, R.color.colorPrimary);
//        mLoadingView.setCircleColors(color1, color1, color1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

}
