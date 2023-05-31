package com.mm.jiankangapp.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mm.foodapp.activity.R;
import com.mm.jiankangapp.api.comments;

public class CommentAdapter extends BaseAdapter {
	private List<comments> list = null;
	private final Context context;
	private LayoutInflater infater = null;

	public CommentAdapter(Context context, List<comments> list) {
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
			convertview = infater.inflate(R.layout.listview_item_comment, null);
			holder.tvUserName = (TextView) convertview.findViewById(R.id.tvUserName);
			holder.tvBody = (TextView) convertview.findViewById(R.id.tvBody);
			holder.tvCreateTime = (TextView) convertview.findViewById(R.id.tvCreateTime);

			convertview.setTag(holder);
		} else {
			holder = (ViewHolder) convertview.getTag();
		}
		holder.tvCreateTime.setText("·¢±íÓÚ:" + list.get(position).getCreatetime());
		holder.tvBody.setText(list.get(position).getBody());
		holder.tvUserName.setText(list.get(position).getUsername());

		return convertview;
	}

	class ViewHolder {

		private TextView tvUserName;
		private TextView tvBody;
		private TextView tvCreateTime;

	}

}
