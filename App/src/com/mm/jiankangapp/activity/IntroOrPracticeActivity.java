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
public class IntroOrPracticeActivity extends BaseActivity {
	private int id = 0;
	private int type = 1;
	private TextView textview1;
	private TextView tvTopTitleCenter, tvTitle;
	private Button btnTopTitleLeft;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_introorpractice);
		id = getIntent().getIntExtra("id", 0);
		type = getIntent().getIntExtra("type", 1);
		findview();
		new loadAsyncTask().execute(id + "");

	}

	private void findview() {
		tvTopTitleCenter = (TextView) findViewById(R.id.tvTopTitleCenter);
		textview1 = (TextView) findViewById(R.id.textview1);
		btnTopTitleLeft = (Button) findViewById(R.id.btnTopTitleLeft);
		btnTopTitleLeft.setVisibility(View.VISIBLE);
		btnTopTitleLeft.setOnClickListener(this);
		btnTopTitleLeft.setText("返回");
		tvTitle = (TextView) findViewById(R.id.tvTitle);
	}

	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(IntroOrPracticeActivity.this, "提示", "获取中,请稍后..");
		}

		@Override
		protected String doInBackground(String... params) {
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=getOneRow";
			urlString = urlString + "&ID=" + params[0] + "&Table=xinxi";
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
						tvTopTitleCenter.setText("详情");
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
		case R.id.btnTopTitleLeft:
			finish();
			break;

		default:
			break;
		}

	}

}
