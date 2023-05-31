package com.mm.jiankangapp.activity;

import android.content.Context;

import com.miebo.utils.SPUtil;

public class AppConstant {
	/**
	 * ��ȡ�����Url��Ŀ¼
	 * 
	 * @param context
	 * @return
	 */
	public static String getRootUrl(Context context) {
		return "http://" + SPUtil.get(context, "IP", "") + "/FoodAppService/";
	}

	/**
	 * ��ȡ�����Url servlet Ŀ¼
	 * 
	 * @param context
	 * @return
	 */
	public static String getUrl(Context context) {
		return getRootUrl(context) + "servlet/";
	}

}
