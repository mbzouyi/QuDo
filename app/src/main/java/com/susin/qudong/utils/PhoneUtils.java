package com.susin.qudong.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PhoneUtils {
	private static SharedPreferences preferences;
	public static final String KEY_FIRST_INStALL = "first_install"; // 第一次安装关键字

	public static SharedPreferences getPreferences(Context context) {
		if (preferences == null) {
			preferences = context.getSharedPreferences("MALI",
					Context.MODE_PRIVATE);
		}
		return preferences;
	}

	public static Editor getEditor(Context context) {
		return getPreferences(context).edit();
	}

	/**
	 * 保存APP用户ID
	 * 
	 * @param context
	 * @param value
	 */
	public static void saveInt(Context context, int value) {
		getEditor(context).putInt("uid", value).commit();
	}

	/**
	 * 获取APP用户ID
	 * 
	 * @param context
	 * @param value
	 * @return
	 */
	public static int getInt(Context context, int value) {
		return getPreferences(context).getInt("uid", value);
	}

	/**
	 * 保存取出int数据
	 */
	public static void saveInt(Context context,String key, int value) {
		getEditor(context).putInt(key, value).commit();
	}
	
	public static int getInt(Context context,String key, int value) {
		return getPreferences(context).getInt(key, value);
	}
	
	
	/**
	 * 保存APP第一次打开状态(是否第一次安装打开)
	 * 
	 * @param context
	 * @param value
	 */
	public static void saveBoolean(Context context, boolean value) {
		getEditor(context).putBoolean(KEY_FIRST_INStALL, value).commit();
	}

	/**
	 * 获取APP第一次打开状态
	 * 
	 * @param context
	 * @param value
	 * @return
	 */
	public static boolean getBoolean(Context context, boolean value) {
		return getPreferences(context).getBoolean(KEY_FIRST_INStALL, value);
	}
	
	/**
	 * 保存微信支付状态
	 * 
	 * @param context
	 * @param value
	 */
	public static void saveBooleanWxP(Context context, boolean value) {
		getEditor(context).putBoolean("wx", value).commit();
	}

	/**
	 * 获取当前微信支付状态
	 * 
	 * @param context
	 * @param value
	 * @return
	 */
	public static boolean getBooleanWxp(Context context, boolean value) {
		return getPreferences(context).getBoolean("wx", value);
	}
	
	/**
	 * 保存信息
	 * 
	 * @param context
	 * @param value
	 */
	public static void saveStr(Context context, String key,String value) {
		getEditor(context).putString(key, value).commit();
	}

	/**
	 * 获取信息
	 * 
	 * @param context
	 * @param value
	 * @return
	 */
	public static String getStr(Context context,String key, String value) {
		return getPreferences(context).getString(key, value);
	}

	
	/**
	 * 清除缓存
	 * 
	 * @param context
	 * @param key
	 */
	public static void remove(Context context, String key) {
		getEditor(context).remove(key).commit();
	}

}
