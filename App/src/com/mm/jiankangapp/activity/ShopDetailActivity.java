package com.mm.jiankangapp.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miebo.utils.AsyncImageLoader;
import com.miebo.utils.BaseActivity;
import com.miebo.utils.BaseUtil;
import com.mm.foodapp.activity.R;
import com.mm.jiankangapp.adapter.CommentAdapter;
import com.mm.jiankangapp.api.comments;

/**
 * 
 * 
 * 
 */
public class ShopDetailActivity extends BaseActivity {
	private int id = 0;
	private ImageView imageView1;
	private AsyncImageLoader asyncImageLoader;
	private String serverUrl;

	private Button btnTopTitleLeft;
	private com.mm.jiankangapp.activity.CommonApplication application;
	private ProgressDialog dialog;
	private MapView mMapView = null;
	private MapController mMapController = null;
	private GeoPoint geopoint;
	private TextView textview1;
	private ListView listview1;
	private List<comments> list;
	private final Gson gson = new Gson();
	private Button btnSend;
	private EditText etBody;
	private submitAsyncTask loginAsyncTask;
	private ImageView ivTopTitleRight;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		com.mm.jiankangapp.activity.CommonApplication app = (com.mm.jiankangapp.activity.CommonApplication) this
				.getApplication();
		if (app.mBMapManager == null) {
			app.mBMapManager = new BMapManager(getApplicationContext());
			/**
			 * ���BMapManagerû�г�ʼ�����ʼ��BMapManager
			 */
			app.mBMapManager.init(com.mm.jiankangapp.activity.CommonApplication.strKey, null);
		}
		/**
		 * ����MapView��setContentView()�г�ʼ��,��������Ҫ��BMapManager��ʼ��֮��
		 */
		setContentView(R.layout.activity_shopdetail);
		mMapView = (MapView) findViewById(R.id.bmapView);
		mMapController = mMapView.getController();
		mMapController.enableClick(true);
		mMapController.setZoom(16);
		application = (com.mm.jiankangapp.activity.CommonApplication) getApplicationContext();
		findview();
		asyncImageLoader = new AsyncImageLoader(
				BitmapFactory.decodeResource(getResources(), R.drawable.pc_loading_fali));
		serverUrl = AppConstant.getRootUrl(this);

		id = getIntent().getIntExtra("id", 0);
		new loadAsyncTask().execute(id + "");

	}

	private void findview() {
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		btnTopTitleLeft = (Button) findViewById(R.id.btnTopTitleLeft);
		btnTopTitleLeft.setVisibility(View.VISIBLE);
		btnTopTitleLeft.setOnClickListener(this);
		btnTopTitleLeft.setText("����");
		textview1 = (TextView) findViewById(R.id.textview1);
		listview1 = (ListView) findViewById(R.id.listview1);
		btnSend = (Button) findViewById(R.id.btnSend);
		btnSend.setOnClickListener(this);
		etBody = (EditText) findViewById(R.id.etBody);
		ivTopTitleRight = (ImageView) findViewById(R.id.ivTopTitleRight);
		ivTopTitleRight.setVisibility(View.VISIBLE);
		ivTopTitleRight.setImageResource(R.drawable.tc_tabmovelike);
		ivTopTitleRight.setOnClickListener(this);
	}

	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(ShopDetailActivity.this, "��ʾ", "��ȡ��,���Ժ�..");
		}

		@Override
		protected String doInBackground(String... params) {
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=getOneRow";
			urlString = urlString + "&ID=" + params[0] + "&Table=shops";
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

					((TextView) findViewById(R.id.tvTopTitleCenter)).setText(jsonObject.getString("name"));
					((TextView) findViewById(R.id.tvTopTitleCenter)).setTextSize(16);
					if (!TextUtils.isEmpty(jsonObject.getString("img_url"))) {
						asyncImageLoader.loadBitmap(serverUrl + "UploadFile/" + jsonObject.getString("img_url"),
								imageView1);
					}
					textview1.setText("��ɫ�ˣ�" + jsonObject.getString("specialty"));
					double lat = Double.valueOf(jsonObject.getString("lat"));
					double lon = Double.valueOf(jsonObject.getString("lon"));
					geopoint = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
					// ׼��overlayͼ�����ݣ�����ʵ������޸�
					Drawable mark = getResources().getDrawable(R.drawable.icon_marka);
					mMapController.animateTo(geopoint);
					OverlayTest itemOverlay = new OverlayTest(mark, mMapView);

					// ��OverlayItem׼��Overlay����
					OverlayItem item1 = new OverlayItem(geopoint, "item1", "item1");
					mMapView.getOverlays().clear();
					mMapView.getOverlays().add(itemOverlay);
					itemOverlay.addItem(item1);
					mMapView.refresh();
					new loadCommentAsyncTask().execute();
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}
	}

	private class loadCommentAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {

		}

		@Override
		protected String doInBackground(String... params) {
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=getComment";
			urlString = urlString + "&shopid=" + id;
			String json = httpHelper.HttpRequest(urlString);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (!TextUtils.isEmpty(result) && result.trim().length() > 0) {
				list = gson.fromJson(result, new TypeToken<List<comments>>() {}.getType());
				CommentAdapter commentAdapter = new CommentAdapter(ShopDetailActivity.this, list);
				listview1.setAdapter(commentAdapter);
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTopTitleLeft:
			finish();
			break;

		case R.id.btnSend:
			if (application.getOnlineUser() == null) {
				toastUtil.show("���ȵ�¼");
				intent = new Intent(ShopDetailActivity.this, LoginActivity.class);
				startActivity(intent);
				return;
			}
			if (etBody.getText().length() == 0) {
				toastUtil.show("�����������ı�");
				return;
			}
			BaseUtil.hideSoftInput(ShopDetailActivity.this);
			new submitAsyncTask().execute();
			break;
		case R.id.ivTopTitleRight:
			if (application.getOnlineUser() == null) {
				toastUtil.show("���ȵ�¼");
				intent = new Intent(ShopDetailActivity.this, LoginActivity.class);
				startActivity(intent);
				return;
			}
			new collectAsyncTask().execute();
			break;
		default:
			break;
		}

	}

	@SuppressWarnings("deprecation")
	private class submitAsyncTask extends AsyncTask<String, Integer, String> {

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(ShopDetailActivity.this);
			dialog.setTitle("��ʾ");
			dialog.setMessage("������,���Ժ�..");
			dialog.setCancelable(true);
			dialog.setButton("ȡ��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (loginAsyncTask != null) {
						loginAsyncTask.cancel(true);
						loginAsyncTask = null;
						toastUtil.show("������ȡ��");
					}
				}
			});
			dialog.show();

		}

		@Override
		protected String doInBackground(String... params) {
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Action", "createcomment");
			map.put("shopid", id);
			map.put("body", etBody.getText());
			map.put("userid", user.getId());
			map.put("username", user.getName());
			String result = httpHelper.HttpPost(urlString, map);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			loginAsyncTask = null;
			dialog.dismiss();
			if (result != null && result.trim().equals("1")) {
				toastUtil.show("���۳ɹ�");
				etBody.setText("");
				new loadCommentAsyncTask().execute();
			} else {
				toastUtil.show("����ʧ��");
			}
		}
	}

	private class collectAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(ShopDetailActivity.this, "��ʾ", "������,���Ժ�..");
		}

		@Override
		protected String doInBackground(String... params) {
			user = application.getOnlineUser();
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=collect";
			urlString = urlString + "&userid=" + user.getId() + "&shopid=" + id;
			BaseUtil.LogII("urlString " + urlString);
			String json = httpHelper.HttpRequest(urlString);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (TextUtils.isEmpty(result)) {
				toastUtil.show("�ղ�ʧ��");
				return;
			}
			if ("1".equals(result.trim())) {
				toastUtil.show("�ղسɹ�");
				ivTopTitleRight.setImageResource(R.drawable.tc_tabmovelikeed);
			} else if ("-1".equals(result.trim())) {
				toastUtil.show("�Ѿ��ղع�");
			}
		}
	}

	@Override
	protected void onPause() {
		/**
		 * MapView������������Activityͬ������activity����ʱ�����MapView.onPause()
		 */
		mMapView.onPause();
		super.onPause();
	}

	@Override
	public void onResume() {
		mMapView.onResume();
		super.onResume();

	}

	@Override
	protected void onDestroy() {
		/**
		 * MapView������������Activityͬ������activity����ʱ�����MapView.destroy()
		 */
		mMapView.destroy();
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mMapView.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 100, 0, "�ҵ��ղ�").setIcon(R.drawable.icon_application);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case 100:
			if (application.getOnlineUser() == null) {
				toastUtil.show("���ȵ�¼");
				intent = new Intent(ShopDetailActivity.this, LoginActivity.class);
				startActivity(intent);
			} else {

				intent = new Intent(ShopDetailActivity.this, MyCollectsListActivity.class);
				startActivity(intent);
			}
			break;

		}
		return false;
	}

	/*
	 * Ҫ����overlay����¼�ʱ��Ҫ�̳�ItemizedOverlay ���������¼�ʱ��ֱ������ItemizedOverlay.
	 */
	class OverlayTest extends ItemizedOverlay<OverlayItem> {
		// ��MapView����ItemizedOverlay
		public OverlayTest(Drawable mark, MapView mapView) {
			super(mark, mapView);
		}

		@Override
		protected boolean onTap(int index) {
			// �ڴ˴���item����¼�
			System.out.println("item onTap: " + index);
			return true;
		}

		@Override
		public boolean onTap(GeoPoint pt, MapView mapView) {
			// �ڴ˴���MapView�ĵ���¼��������� trueʱ
			super.onTap(pt, mapView);
			return false;
		}

	}

}
