package com.mm.jiankangapp.activity;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.miebo.utils.BaseActivity;
import com.mm.foodapp.activity.R;

/**
 * 
 * 介绍或做法界面
 * 
 */
public class TypeIntroActivity extends BaseActivity {
	private int typeid;
	private String typename;
	private TextView textview1;
	private TextView tvTopTitleCenter, tvTitle;
	private Button btnTopTitleLeft;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_introorpractice);
		typeid = getIntent().getIntExtra("typeid", 0);
		typename = getIntent().getStringExtra("typename");
		findview();
		new loadAsyncTask().execute(typeid + "");

	}

	private void findview() {
		tvTopTitleCenter = (TextView) findViewById(R.id.tvTopTitleCenter);
		tvTopTitleCenter.setText(typename);
		textview1 = (TextView) findViewById(R.id.textview1);
		btnTopTitleLeft = (Button) findViewById(R.id.btnTopTitleLeft);
		btnTopTitleLeft.setVisibility(View.VISIBLE);
		btnTopTitleLeft.setOnClickListener(this);
		btnTopTitleLeft.setText("返回");
		findViewById(R.id.tvTitle).setVisibility(View.GONE);
	}

	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(TypeIntroActivity.this, "提示", "获取中,请稍后..");
		}

		@Override
		protected String doInBackground(String... params) {
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=getOneRow";
			urlString = urlString + "&ID=" + params[0] + "&Table=types";
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

					textview1.setText("   " + jsonObject.getString("intro"));

				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTopTitleLeft:
			finish();
			break;

		default:
			break;
		}

	}

}
