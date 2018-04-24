package com.susin.qudong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.susin.qudong.R;
import com.susin.qudong.utils.PhoneUtils;

public class SplashActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		Window win = getWindow();
//		TitleUtils.setTranslucentStatus(this, win);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		

		new Handler(){
			public void handleMessage(android.os.Message msg) {
				if (PhoneUtils.getBoolean(SplashActivity.this, false)) {
					if (PhoneUtils.getInt(getApplicationContext(), 0)!=0) {
						if (PhoneUtils.getInt(getApplicationContext(), "isarea", 0)==1) {
							startActivity(new Intent(SplashActivity.this, MainActivity.class));
						}else {
							startActivity(new Intent(SplashActivity.this, LoginActivity.class));
						}
					}else {
						startActivity(new Intent(SplashActivity.this, LoginActivity.class));
					}
					finish();
				}else{
					startActivity(new Intent(SplashActivity.this, GuideActivity.class));
					PhoneUtils.saveBoolean(SplashActivity.this, true);
					finish();
				}
			};
		}.sendEmptyMessageDelayed(0, 2000);
	}
	

}
