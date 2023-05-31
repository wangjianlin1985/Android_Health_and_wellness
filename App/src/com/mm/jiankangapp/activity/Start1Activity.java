package com.mm.jiankangapp.activity;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.miebo.utils.BaseActivity;
import com.miebo.utils.OnLineUser;
import com.miebo.utils.SPUtil;
import com.mm.foodapp.activity.R;


public class Start1Activity extends BaseActivity {
	private Button btnOK;
	private int id = 7;
	private int type = 1;
	private TextView textview1;
	private TextView tvTopTitleCenter, tvTitle;
	private Button btnTopTitleLeft;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start1);
		findview();
		if (SPUtil.get(this, "login", "").length() > 0) {
			dealLogin(SPUtil.get(this, "login", ""));
			new loadAsyncTask().execute(id + "");
		}
	}

	private void findview() {
		btnOK = (Button) findViewById(R.id.btnOK);
		btnOK.setOnClickListener(this);
		
		tvTopTitleCenter = (TextView) findViewById(R.id.tvTopTitleCenter);
		textview1 = (TextView) findViewById(R.id.textview1);
		btnTopTitleLeft = (Button) findViewById(R.id.btnTopTitleLeft);
		btnTopTitleLeft.setVisibility(View.VISIBLE);
		btnTopTitleLeft.setOnClickListener(this);
		btnTopTitleLeft.setText("");
		tvTitle = (TextView) findViewById(R.id.tvTitle);
	}

	private void dealLogin(String result) {
		try {
			jsonArray = new JSONArray(result);
			jsonObject = jsonArray.getJSONObject(0);
			// 保存登录用户信息
			CommonApplication application = (CommonApplication) getApplicationContext();
			OnLineUser model = new OnLineUser();
			model.setId(jsonObject.getInt("id"));
			model.setLoginid(jsonObject.getString("loginid"));
			model.setName(jsonObject.getString("name"));
			application.setLoginUser(model);

		} catch (JSONException e) {

			e.printStackTrace();
		}
	}
	
	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(Start1Activity.this, "提示", "获取中,请稍后..");
		}

		@Override
		protected String doInBackground(String... params) {
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=getOneRow";
			urlString = urlString + "&ID=" + params[0] + "&Table=dishes";
			String json = httpHelper.HttpRequest(urlString);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (result.trim().length() > 0) {
				try {
					jsonArray = new JSONArray(result);
					jsonObject = jsonArray.getJSONObject(0);
					tvTitle.setText(jsonObject.getString("title"));
					if (type == 1) {
						tvTopTitleCenter.setText("今日养生资讯");
						textview1.setText("   " + jsonObject.getString("intro"));
					} else {
						tvTopTitleCenter.setText("做法");
						textview1.setText("   " + jsonObject.getString("practice"));
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnOK:
			intent = new Intent(Start1Activity.this, MainActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}

	}
}
