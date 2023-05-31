package com.mm.jiankangapp.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miebo.utils.AsyncImageLoader;
import com.mm.foodapp.activity.R;
import com.mm.jiankangapp.activity.AppConstant;
import com.mm.jiankangapp.api.types;

public class HomeListAdapter extends BaseAdapter {
	private List<types> list = null;
	private final Context context;
	private LayoutInflater infater = null;
	private final AsyncImageLoader asyncImageLoader;
	private final String serverUrl;

	public HomeListAdapter(Context context, List<types> list) {
		this.infater = LayoutInflater.from(context);
		this.list = list;
		this.context = context;
		asyncImageLoader = new AsyncImageLoader(BitmapFactory.decodeResource(context.getResources(),
				R.drawable.pc_loading_fali));
		serverUrl = AppConstant.getRootUrl(context);
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Object getItem(int position) {

		return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(final int position, View convertview, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertview == null) {
			holder = new ViewHolder();
			convertview = infater.inflate(R.layout.listview_item_common, null);
			holder.textView1 = (TextView) convertview.findViewById(R.id.textView1);
			holder.textView2 = (TextView) convertview.findViewById(R.id.textView2);
			holder.textView3 = (TextView) convertview.findViewById(R.id.textView3);
			holder.textView2.setVisibility(View.GONE);
			holder.textView3.setVisibility(View.GONE);
			holder.textView1.setTextSize(26);
			convertview.setTag(holder);
		} else {
			holder = (ViewHolder) convertview.getTag();
		}

		holder.textView1.setText(list.get(position).getTypename());

		return convertview;
	}

	class ViewHolder {

		private TextView textView1;
		private TextView textView2;
		private TextView textView3;

	}

}
