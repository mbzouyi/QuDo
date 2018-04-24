package com.susin.qudong.utils;

import java.lang.ref.WeakReference;

import android.os.Handler;
import android.os.Message;
import android.widget.Button;

public class VCodeHandler extends Handler {
	private int second = 60;
	private WeakReference<Button> wr;
	public VCodeHandler(Button btn){
		wr = new WeakReference<>(btn);
	}
	
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		Button btn = wr.get();
		if (btn == null) {
			return;
		}
		if (second <=0) {
			second = 60;
			btn.setEnabled(true);
			btn.setText("重发验证码");
		}else{
			btn.setText(String.format(second+"秒后重发"));
			btn.setEnabled(false);
			sendEmptyMessageDelayed(0, 1000);
		}
		second--;
	}
	
//	public void cancle(){
//		second = 0;
//		Button btn = wr.get();
////		WMViewUtils.enableButton(btn);
//		btn.setText("获取验证码");
//	}
	
}
