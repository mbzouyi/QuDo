package com.susin.qudong.tools;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.Window;

import com.susin.qudong.R;

public class PreloadDialog extends Dialog {
	private Context context;
	
	public PreloadDialog(Context context) {
		super(context,R.style.loadingDialogStyle);
		this.context = context;
		init();
	}

	private void init() {
		setContentView(R.layout.dialog_load);
		Window window = getWindow();
		window.setBackgroundDrawableResource(R.drawable.bg_pink_with_trans);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}
	

}
