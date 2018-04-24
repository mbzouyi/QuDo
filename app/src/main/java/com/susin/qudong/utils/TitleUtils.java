package com.susin.qudong.utils;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.susin.qudong.R;

public class TitleUtils {
	
	public static void setTranslucentStatus(Activity mContent, Window win) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
		
		// 创建状态栏的管理实例  
		SystemBarTintManager tintManager = new SystemBarTintManager(mContent);
		// 激活状态栏设置 
        tintManager.setStatusBarTintEnabled(true);
        // 设置一个样式背景给导航栏 
        tintManager.setStatusBarTintResource(R.color.white);//状态栏颜色
		
	}

}
