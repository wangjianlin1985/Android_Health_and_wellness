package com.mm.jiankangapp.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import com.miebo.utils.ActivityUtils;
import com.miebo.utils.BaseActivity;
import com.miebo.utils.BaseUtil;
import com.miebo.utils.HttpUtil;
import com.miebo.utils.OnLineUser;
import com.miebo.utils.SPUtil;
import com.mm.foodapp.activity.R;

public class MainActivity extends BaseActivity {

	
	private ProgressDialog dialog;

	String[] arrAppName = new String[] { "养生方法", "健康症状","养生指导","养生馆", "用户中心", "退出程序" };
	Integer[] icon = new Integer[] { R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,
			R.drawable.icon5, R.drawable.icon6 };
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		GridView gridview = (GridView) findViewById(R.id.grd);

		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < arrAppName.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", icon[i]);
			map.put("ItemText", arrAppName[i]);
			lstImageItem.add(map);
		}
		SimpleAdapter saImageItems = new SimpleAdapter(this, lstImageItem,
				R.layout.gitem, new String[] { "ItemImage", "ItemText" },
				new int[] { R.id.ItemImage, R.id.ItemText });
		gridview.setAdapter(saImageItems);
		gridview.setOnItemClickListener(new ItemClickListener());

		
	}

	// 当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件
	class ItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			HashMap<String, Object> item = (HashMap<String, Object>) arg0.getItemAtPosition(arg2);
			String str = (String) item.get("ItemText");
			if (str.equals("养生方法")) {
				intent = new Intent(MainActivity.this, XinxifidListActivity.class);
				
				
				intent.putExtra("typeid", 1);
				intent.putExtra("typename", "养生方法");
				startActivity(intent);
				
				
			} else if (str.equals("健康症状")) {
				intent = new Intent(MainActivity.this, XinxifidListActivity.class);
				intent.putExtra("typeid", 2);
				intent.putExtra("typename", "健康症状");
				startActivity(intent);
				//startActivity(new Intent(MainActivity.this,	XinxiListActivity.class));

			}else if (str.equals("养生指导")) {
				intent = new Intent(MainActivity.this, XinxifidListActivity.class);
				intent.putExtra("typeid", 3);
				intent.putExtra("typename", "养生指导");
				startActivity(intent);
				//startActivity(new Intent(MainActivity.this,	XinxiListActivity.class));

			}else if (str.equals("养生馆")) {
				
				
				intent = new Intent(MainActivity.this, ShopListActivity.class);
				intent.putExtra("typeid", 3);
				intent.putExtra("typename", "养生馆");
				startActivity(intent);
				//startActivity(new Intent(MainActivity.this,	XinxiListActivity.class));

			}
			
			
			else if (str.equals("在线聊天")) {
			
				startActivity(new Intent(MainActivity.this,
						XinxiListActivity.class));

			} else if (str.equals("用户中心")) {
				startActivity(new Intent(MainActivity.this,	PersonCenterActivity.class));


			} else if (str.equals("退出程序")) {
				MainActivity.this.finish();
			}
		}
	}

	

}
