package com.mm.jiankangapp.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miebo.utils.BaseActivity;
import com.mm.foodapp.activity.R;
import com.mm.jiankangapp.adapter.ShopAdapter;
import com.mm.jiankangapp.api.shops;

/**
 * 
 * 
 * 
 * 
 */
public class ShopListActivity extends BaseActivity {
	private Button btnTopTitleLeft, btnTopTitleRight;
	private List<shops> list;
	private ShopAdapter adapter;
	private ListView listview1;
	private TextView tvTopTitleCenter;
	private final String keyword = "";
	private int typeid;
	private String typename;
	private final Gson gson = new Gson();

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
		listview1 = (ListView) findViewById(R.id.listview1);
		listview1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				intent = new Intent(ShopListActivity.this, ShopDetailActivity.class);
				intent.putExtra("id", list.get(position).getId());
				startActivity(intent);
			}
		});

	}

	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(ShopListActivity.this, "提示", "获取中..");
		}

		@Override
		protected String doInBackground(String... params) {
			String json = null;
			serverUrl = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=getshopslist&typeid="
					+ typeid;

			json = httpHelper.HttpRequest(serverUrl);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			list = new ArrayList<shops>();
			if (!TextUtils.isEmpty(result)) {
				list = gson.fromJson(result, new TypeToken<List<shops>>() {}.getType());
				adapter = new ShopAdapter(ShopListActivity.this, list);
				listview1.setAdapter(adapter);
			} else {
				toastUtil.show("没有数据");
			}

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == 1) {
			new loadAsyncTask().execute(tvTopTitleCenter.getText().toString(), keyword);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTopTitleRight:
			break;
		case R.id.btnTopTitleLeft:
			finish();
			break;

		default:
			break;
		}

	}

}
