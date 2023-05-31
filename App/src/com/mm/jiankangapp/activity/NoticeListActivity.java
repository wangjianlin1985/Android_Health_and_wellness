package com.mm.jiankangapp.activity;

import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mm.foodapp.activity.R;
import com.mm.jiankangapp.adapter.NoticeAdapter;
import com.mm.jiankangapp.api.zhenz;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miebo.utils.BaseActivity;

/**
 * 
 * 
 * 
 * 
 */
public class NoticeListActivity extends BaseActivity {
	private Button btnTopTitleLeft, btnTopTitleRight;
	private List<zhenz> list;
	private NoticeAdapter adapter;
	private ListView listview1;
	private TextView tvTopTitleCenter;
	private final Gson gson = new Gson();

	private int row = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		findview();
		new loadAsyncTask().execute();
	}

	private void findview() {
		tvTopTitleCenter = ((TextView) findViewById(R.id.tvTopTitleCenter));
		btnTopTitleRight = (Button) findViewById(R.id.btnTopTitleRight);
		btnTopTitleRight.setText("�ҵ�");
//		if (user.getOther().equals("����Ա")) {
//		  btnTopTitleRight.setVisibility(View.VISIBLE);
//		}
		btnTopTitleRight.setOnClickListener(this);
		tvTopTitleCenter.setText("�ҵ�֢״����");

		listview1 = (ListView) findViewById(R.id.listview1);
		listview1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				row = position;
//				intent = new Intent(NoticeListActivity.this,
//						NoticeDetailActivity.class);
//				intent.putExtra("id", list.get(position).getId());
//				startActivity(intent);
			}

		});
		listview1.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				row = position;
				//if (user.getOther().equals("����Ա")) {
					showEditDialog();
				//}
				
				return true;
			}
		});

	}

	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog
					.show(NoticeListActivity.this, "��ʾ", "��ȡ��..");
		}

		@Override
		protected String doInBackground(String... params) {
			String json = null;
			serverUrl = AppConstant.getUrl(getApplicationContext())
					+ "ServletService?Action=getnoticelist";
			json = httpHelper.HttpRequest(serverUrl);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (!TextUtils.isEmpty(result) && result.trim().length() > 0) {
				Log.i("����1","msg");
				list = gson.fromJson(result, new TypeToken<List<zhenz>>() {
				}.getType());
				adapter = new NoticeAdapter(NoticeListActivity.this, list);
				Log.i("����2","msg");
				listview1.setAdapter(adapter);
			} else {
				toastUtil.show("û������");
			}
		}
	}

	// ���������Ĳ˵�
	private void showEditDialog() {
		String[] arg = new String[] {  "����" };
		new AlertDialog.Builder(this).setTitle("ѡ�����")
				.setItems(arg, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:// �޸�
							toastUtil.show("����ɹ�");
//							intent = new Intent(NoticeListActivity.this,
//									NoticeEditActivity.class);
//							intent.putExtra("id", list.get(row).getId());
//							startActivityForResult(intent, 1);
							break;
						case 1:// ɾ��
							new deleteAsyncTask().execute();
							break;
						default:
							break;
						}
					}
				}).show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == 1) {
			new loadAsyncTask().execute();
		}

	}

	private class deleteAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(NoticeListActivity.this, "��ʾ",
					"������,���Ժ�..");
		}

		@Override
		protected String doInBackground(String... params) {
			serverUrl = AppConstant.getUrl(getApplicationContext())
					+ "ServletService?Action=Del&Table=tb_notices&ID="
					+ list.get(row).getId();
			String json = httpHelper.HttpRequest(serverUrl);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (result != null && result.trim().length() > 0) {
				toastUtil.show("ɾ���ɹ�");
				list.remove(row);
				adapter.notifyDataSetChanged();
			} else {
				toastUtil.show("ɾ��ʧ��");

			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTopTitleLeft:
			finish();
			break;
		case R.id.btnTopTitleRight:
//			intent = new Intent(NoticeListActivity.this,
//					NoticeEditActivity.class);
//			startActivityForResult(intent, 1);
			break;
		}

	}

}
