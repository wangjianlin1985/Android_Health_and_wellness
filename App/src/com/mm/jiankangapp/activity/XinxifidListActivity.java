package com.mm.jiankangapp.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mm.foodapp.activity.R;
import com.miebo.utils.UIUtils;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miebo.utils.BaseActivity;
import com.mm.jiankangapp.adapter.XinxiAdapter;
import com.mm.jiankangapp.api.dishes;
import com.mm.jiankangapp.api.types;

/**
 * 
 * 
 * @author zlus
 * 
 */
public class XinxifidListActivity extends BaseActivity {
	private Button btnTopTitleLeft, btnTopTitleRight;
	private List<dishes> list;
	private XinxiAdapter adapter;
	private ListView listview1;
	private TextView tvTopTitleCenter;
	private final String keyword = "";
	private int typeid;
	private String typename;
	private final Gson gson = new Gson();
	private List<types> typelist;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		typeid = getIntent().getIntExtra("typeid", 0);
		typename = getIntent().getStringExtra("typename");
		findview();
		new loadAsyncTask().execute(keyword);
	}

	private void findview() {
		tvTopTitleCenter = ((TextView) findViewById(R.id.tvTopTitleCenter));
		tvTopTitleCenter.setText(typename);

		btnTopTitleLeft = (Button) findViewById(R.id.btnTopTitleLeft);
		btnTopTitleLeft.setVisibility(View.VISIBLE);
		btnTopTitleLeft.setOnClickListener(this);
		btnTopTitleLeft.setText("返回");
		
		btnTopTitleRight = (Button) findViewById(R.id.btnTopTitleRight);
		btnTopTitleRight.setVisibility(View.VISIBLE);
		btnTopTitleRight.setOnClickListener(this);
		btnTopTitleRight.setText("搜索");

		listview1 = (ListView) findViewById(R.id.listview1);

	}

	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(XinxifidListActivity.this, "提示", "获取中..");
		}

		@Override
		protected String doInBackground(String... params) {
			String json = null;
			serverUrl = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=getxinxifidlist&typeid="
					+ typeid;

			json = httpHelper.HttpRequest(serverUrl);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			list = new ArrayList<dishes>();
			if (!TextUtils.isEmpty(result)) {
				list = gson.fromJson(result, new TypeToken<List<dishes>>() {}.getType());
				adapter = new XinxiAdapter(XinxifidListActivity.this, list);
				listview1.setAdapter(adapter);
			} else {
				toastUtil.show("没有数据");
			}

		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		if(typeid==1)
		{
		menu.add(0, 99, 0, "食疗方法").setIcon(R.drawable.icon_application);
		menu.add(0, 100, 0, "中医养生").setIcon(R.drawable.icon_application);
		menu.add(0, 101, 0, "生活小妙招").setIcon(R.drawable.icon_application);
		}
		if(typeid==2)
		{
		menu.add(0, 102, 0, "亚健康类型").setIcon(R.drawable.icon_application);
		menu.add(0, 103, 0, "亚健康症状").setIcon(R.drawable.icon_application);
		
		}
		
		if(typeid==3)
		{
		menu.add(0, 104, 0, "养生资讯").setIcon(R.drawable.icon_application);
		menu.add(0, 105, 0, "对症养生").setIcon(R.drawable.icon_application);
		
		}
		
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case 99:// 人工服务
			intent = new Intent(XinxifidListActivity.this, XinxiListActivity.class);
			
			
			intent.putExtra("typeid", 4);
			intent.putExtra("typename", "食疗方法");
			startActivity(intent);
			break;

		case 100:
        intent = new Intent(XinxifidListActivity.this, XinxiListActivity.class);	
        intent.putExtra("typeid", 5);
			intent.putExtra("typename", "中医养生");
			startActivity(intent);
			break;
		case 101:
			 intent = new Intent(XinxifidListActivity.this, XinxiListActivity.class);	
		        intent.putExtra("typeid", 6);
					intent.putExtra("typename", "生活小妙招");
					startActivity(intent);
			break;
		case 102:
			intent = new Intent(XinxifidListActivity.this, XinxiListActivity.class);	
	        intent.putExtra("typeid", 7);
				intent.putExtra("typename", "亚健康类型");
				startActivity(intent);
			break;
			
		case 103:
			intent = new Intent(XinxifidListActivity.this, XinxiListActivity.class);	
	        intent.putExtra("typeid", 8);
				intent.putExtra("typename", "亚健康症状");
				startActivity(intent);
			break;
			
		case 104:
			intent = new Intent(XinxifidListActivity.this, XinxiListActivity.class);	
	        intent.putExtra("typeid", 9);
				intent.putExtra("typename", "养生资讯");
				startActivity(intent);
			break;
			
		case 105:
			intent = new Intent(XinxifidListActivity.this, XinxiListActivity.class);	
	        intent.putExtra("typeid", 10);
				intent.putExtra("typename", "对症养生");
				startActivity(intent);
			break;

		case 108:
			finish();
			System.exit(0);
			break;
		}
		return false;
	}
//	private void query() {
//		showProgressDialog("获取中,请稍后..");
//		mParamMaps.clear();
//		mParamMaps.put("Action", "getdisheslist");
//		mParamMaps.put("keyword", keyword);
//		mParamMaps.put("typeid", typeid);
//		AsyncRequestUtils.newInstance().get(mParamMaps, new AsyncListener() {
//			@Override
//			public void onResult(String result) {
//				hideProgressDialog();
//
//				if (!TextUtils.isEmpty(result) && result.trim().length() > 0) {
//					list = gson.fromJson(result, new TypeToken<List<dishes>>() {
//					}.getType());
//					adapter = new XinxiAdapter(XinxifidListActivity.this, list);
//					listview1.setAdapter(adapter);
//				} else {
//					toastUtil.show("没有数据");
//				}
//
//				querytype();
//			}
//
//		});
//	}
//
//	private void querytype() {
//		mParamMaps.clear();
//		mParamMaps.put("Action", "getOneRow");
//		mParamMaps.put("Table", "types");
//		AsyncRequestUtils.newInstance().get(mParamMaps, new AsyncListener() {
//			@Override
//			public void onResult(String result) {
//				hideProgressDialog();
//				if (!TextUtils.isEmpty(result) && result.trim().length() > 0) {
//					typelist = gson.fromJson(result,
//							new TypeToken<List<types>>() {
//							}.getType());
//
//				} else {
//					typelist = new ArrayList<types>();
//				}
//
//			}
//		});
//	}
//	private void dialog() {
//		View view = LayoutInflater.from(this).inflate(R.layout.layout_search,
//				null);
//		final EditText etName = (EditText) view.findViewById(R.id.etName);
//		final Spinner spinner1 = (Spinner) view.findViewById(R.id.spinner1);
//		String[] strs = new String[typelist.size() + 1];
//		strs[0] = "所有";
//		for (int i = 0; i < typelist.size(); i++) {
//			strs[i + 1] = typelist.get(i).getTypename();
//		}
//		UIUtils.bindSpinner(this, spinner1, strs);
//		AlertDialog dialog = new AlertDialog.Builder(this).setTitle("搜索")
//				.setIcon(android.R.drawable.ic_dialog_info).setView(view)
//				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						keyword = etName.getText().toString();
//
//						if (spinner1.getSelectedItemPosition() != 0) {
//							typeid = typelist.get(
//									spinner1.getSelectedItemPosition() - 1)
//									.getId();
//						} else {
//							typeid = 0;
//						}
//						query();
//					}
//
//				}).setNegativeButton("取消", null).create();
//		dialog.show();
//	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTopTitleRight:
			//dialog();
			break;
		case R.id.btnTopTitleLeft:
			finish();
			break;

		default:
			break;
		}

	}

}
