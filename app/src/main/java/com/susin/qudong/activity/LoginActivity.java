package com.susin.qudong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.susin.qudong.R;
import com.susin.qudong.utils.NetWorkUtils;
import com.susin.qudong.utils.PhoneUtils;
import com.susin.qudong.utils.TitleUtils;
import com.susin.qudong.utils.VCodeHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {

	@BindView(R.id.login_phone)
	EditText et_phone;
	@BindView(R.id.login_pwd)
	EditText et_code;
	@BindView(R.id.tv_login_getcode)
	Button tv_login_getcode;
	@BindView(R.id.tv_agree)
	TextView tv_agree;
	private String phone;
	private String code;
	private VCodeHandler vCodeHandler;
	public static LoginActivity instance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		Window win = getWindow();
//		TitleUtils.setTranslucentStatus(LoginActivity.this, win);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);
		
		instance=this;
		
		initView();
		
		vCodeHandler = new VCodeHandler(tv_login_getcode);
	}

	private void initView() {
		String hasPhone=PhoneUtils.getStr(getApplicationContext(), "phone", "");
		String hasCode=PhoneUtils.getStr(getApplicationContext(), "password", "");
		if (!hasPhone.equals("")) {
			et_phone.setText(hasPhone);
		}
		if (!hasCode.equals("")) {
			et_code.setText(hasCode);
		}
	}


	@OnClick({ R.id.login_btn ,R.id.tv_login_getcode,R.id.tv_agree})
	public void click(View v) {
		switch (v.getId()) {
		case R.id.login_btn:
//			intent = new Intent(LoginActivity.this, ResgisterActivity.class);
//			startActivity(intent);
			
			if (NetWorkUtils.isNetworkConnected(LoginActivity.this)) {
				if (et_phone.getText().length()==0) {
					Toast.makeText(this, "请输入手机号", 0).show();
				}else if (et_code.getText().length()==0) {
					Toast.makeText(this, "请输入验证码", 0).show();
				}else if (et_phone.getText().length()!=0&&et_code.getText().length()!=0) {
					goLogin();
				}
			}else{
				Toast.makeText(this, "网络不佳，请检查您的网络设置或重试", 0).show();
			}
			break;
		case R.id.tv_login_getcode:
			if (NetWorkUtils.isNetworkConnected(this)) {
				if (et_phone.getText().length()==0) {
					Toast.makeText(this, "请输入手机号", 0).show();
				}else if (et_phone.getText()!=null) {
					getCode();
					vCodeHandler.sendEmptyMessage(0);
					et_code.setText("");
				}
			}else{
				Toast.makeText(this, "网络不佳，请检查您的网络设置或重试", 0).show();
			}
			break;
		case R.id.tv_agree:
//			intent=new Intent(LoginActivity.this,Activity_Agree.class);
//			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	//登录
	private void goLogin() {
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
		this.finish();
	}

	//获取验证码
	private void getCode(){
		phone=et_phone.getText().toString();
		
	}
	
	
}
