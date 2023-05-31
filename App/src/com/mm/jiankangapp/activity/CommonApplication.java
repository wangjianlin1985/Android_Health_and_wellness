package com.mm.jiankangapp.activity;

import android.content.Context;

import com.baidu.mapapi.BMapManager;

public class CommonApplication extends com.miebo.utils.BaseApplication {

	private static CommonApplication mInstance = null;
	private boolean isRefreshComment;
	public boolean m_bKeyRight = true;
	public BMapManager mBMapManager = null;
	public static final String strKey = "F32d0b874fbade29187984040945de4e";

	@Override
	public void onCreate() {
		super.onCreate();
		CrashHandler crashHandler = CrashHandler.getInstance();    
	    crashHandler.init(getApplicationContext()); 
		initEngineManager(this);
	}

	public static CommonApplication getInstance() {
		return mInstance;
	}

	public void initEngineManager(Context context) {
		if (mBMapManager == null) {
			mBMapManager = new BMapManager(context);
		}
		mBMapManager.init(strKey, null);
	}

	public boolean isRefreshComment() {
		return isRefreshComment;
	}

	public void setRefreshComment(boolean isRefreshComment) {
		this.isRefreshComment = isRefreshComment;
	}

}
