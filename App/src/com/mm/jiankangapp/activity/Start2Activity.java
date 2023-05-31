package com.mm.jiankangapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.miebo.utils.BaseActivity;
import com.mm.foodapp.activity.R;

public class Start2Activity extends BaseActivity {
	private Button btnOK, btnOK1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start2);
		findview();

	}

	private void findview() {
		btnOK = (Button) findViewById(R.id.btnOK);
		btnOK.setOnClickListener(this);
		btnOK1 = (Button) findViewById(R.id.btnOK1);
		btnOK1.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnOK:
			intent = new Intent(Start2Activity.this, HomeListActivity.class);
			startActivity(intent);
			break;
		case R.id.btnOK1:
			intent = new Intent(Start2Activity.this, SoftIntroActivity.class);
			startActivity(intent);
			break;
		}

	}
}
