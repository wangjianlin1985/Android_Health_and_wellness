package com.mm.jiankangapp.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mm.foodapp.activity.R;
import com.mm.jiankangapp.api.zhenz;

public class NoticeAdapter extends BaseAdapter {
	private List<zhenz> list = null;
	private final Context context;
	private LayoutInflater infater = null;

	public NoticeAdapter(Context context, List<zhenz> list) {
		this.infater = LayoutInflater.from(context);
		this.list = list;
		this.context = context;
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

			holder.textView1 = (TextView) convertview
					.findViewById(R.id.textView1);
//			holder.textView2 = (TextView) convertview
//					.findViewById(R.id.textView2);
//			holder.textView3 = (TextView) convertview
//					.findViewById(R.id.textView3);
			holder.textView1.setTextSize(20);
			//holder.textView2.setTextSize(16);
			//holder.textView3.setTextSize(14);

			convertview.setTag(holder);
		} else {
			holder = (ViewHolder) convertview.getTag();
		}
		Log.i("ЕНет3","msg");
		holder.textView1.setText(list.get(position).getZhengzhuang());

	

		return convertview;
	}

	class ViewHolder {

		private TextView textView1;
		//private TextView textView2;
	//	private TextView textView3;

	}

}
