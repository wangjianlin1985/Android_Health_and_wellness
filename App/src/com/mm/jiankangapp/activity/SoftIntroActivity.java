package com.mm.jiankangapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.miebo.utils.BaseActivity;
import com.mm.foodapp.activity.R;

public class SoftIntroActivity extends BaseActivity {

	private Button btnTopTitleLeft;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_softintro);
		findview();

	}

	private void findview() {
		((TextView) findViewById(R.id.tvTopTitleCenter)).setText("ΩÈ…‹");
		btnTopTitleLeft = (Button) findViewById(R.id.btnTopTitleLeft);
		btnTopTitleLeft.setVisibility(View.VISIBLE);
		btnTopTitleLeft.setOnClickListener(this);
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
