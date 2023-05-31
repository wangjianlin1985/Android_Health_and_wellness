package com.mm.jiankangapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.miebo.utils.BaseActivity;
import com.mm.foodapp.activity.R;

/**
 * 
 * 
 * 
 * 
 */
public class HomeList1Activity extends BaseActivity {
	private Button btnTopTitleLeft, btnTopTitleRight;

	private TextView tvTopTitleCenter;

	private int typeid;
	private String typename;
	private TextView textView1, textView2, textView3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homelist1);
		typeid = getIntent().getIntExtra("typeid", 0);
		typename = getIntent().getStringExtra("typename");
		findview();

	}

	private void findview() {
		tvTopTitleCenter = ((TextView) findViewById(R.id.tvTopTitleCenter));
		tvTopTitleCenter.setText(typename);
		btnTopTitleLeft = (Button) findViewById(R.id.btnTopTitleLeft);
		btnTopTitleLeft.setVisibility(View.VISIBLE);
		btnTopTitleLeft.setOnClickListener(this);
		btnTopTitleLeft.setText("∑µªÿ");
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView3 = (TextView) findViewById(R.id.textView3);
		textView1.setText(typename);
		textView2.setText(typename + "√˚µÍ");
		textView3.setText("ΩÈ…‹");
		textView1.setOnClickListener(this);
		textView2.setOnClickListener(this);
		textView3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTopTitleLeft:
			finish();
			break;
		case R.id.textView1:
			intent = new Intent(HomeList1Activity.this, XinxiListActivity.class);
			intent.putExtra("typeid", typeid);
			intent.putExtra("typename", typename);
			startActivity(intent);
			break;
		case R.id.textView2:
			intent = new Intent(HomeList1Activity.this, ShopListActivity.class);
			intent.putExtra("typeid", typeid);
			intent.putExtra("typename", typename);
			startActivity(intent);
			break;
		case R.id.textView3:
			intent = new Intent(HomeList1Activity.this, TypeIntroActivity.class);
			intent.putExtra("typeid", typeid);
			intent.putExtra("typename", typename);
			startActivity(intent);

			break;

		default:
			break;
		}

	}

}
