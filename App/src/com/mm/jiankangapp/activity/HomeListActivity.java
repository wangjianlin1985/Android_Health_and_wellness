package com.mm.jiankangapp.activity;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
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
import com.mm.jiankangapp.adapter.HomeListAdapter;
import com.mm.jiankangapp.api.types;

/**
 * 
 * 
 * 
 * 
 */
public class HomeListActivity extends BaseActivity {
	private Button btnTopTitleLeft, btnTopTitleRight;
	private List<types> list;
	private HomeListAdapter adapter;
	private ListView listview1;
	private TextView tvTopTitleCenter;
	private final String keyword = "";
	private final Gson gson = new Gson();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		findview();
		new loadAsyncTask().execute(keyword);
	}

	private void findview() {
		tvTopTitleCenter = ((TextView) findViewById(R.id.tvTopTitleCenter));
		tvTopTitleCenter.setText("各地名菜系");
		btnTopTitleLeft = (Button) findViewById(R.id.btnTopTitleLeft);
		btnTopTitleLeft.setVisibility(View.VISIBLE);
		btnTopTitleLeft.setOnClickListener(this);
		btnTopTitleLeft.setText("返回");
		listview1 = (ListView) findViewById(R.id.listview1);
		listview1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				intent = new Intent(HomeListActivity.this, HomeList1Activity.class);
				intent.putExtra("typeid", list.get(position).getId());
				intent.putExtra("typename", list.get(position).getTypename());
				startActivity(intent);
			}

		});
	}

	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(HomeListActivity.this, "提示", "获取中..");
		}

		@Override
		protected String doInBackground(String... params) {
			String json = null;

			serverUrl = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=getOneRow&Table=types";

			json = httpHelper.HttpRequest(serverUrl);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (!TextUtils.isEmpty(result)) {
				list = gson.fromJson(result, new TypeToken<List<types>>() {}.getType());
				adapter = new HomeListAdapter(HomeListActivity.this, list);
				listview1.setAdapter(adapter);
			} else {
				toastUtil.show("没有数据");
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		menu.add(0, 102, 0, "退出").setIcon(R.drawable.icon_application);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {

		case 102:
			finish();
			System.exit(0);
			break;
		}
		return false;
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
