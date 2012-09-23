package com.scut.exguide.assist;

import com.scut.exguide.ui.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class RoundListViewAdapter_exhibition extends BaseAdapter implements
		ListAdapter {

	final String[] strs = { "���޹���չ����", "���޹���չ����" };

	private Context context;
	private LayoutInflater inflater;

	public RoundListViewAdapter_exhibition(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return strs.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = inflater.inflate(R.layout.item_exhibition, null);
		final View ll = convertView;
		TextView tv = (TextView) convertView
				.findViewById(R.id.exhibition_name_text);
		tv.setText(strs[position]);

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();

				// ��ɫ
				switch (position) {
				case 0:
					ll.setBackgroundResource(R.drawable.app_list_corner_round_top);
					break;

				default:
					if (position == strs.length - 1) {
						ll.setBackgroundResource(R.drawable.app_list_corner_round_bottom);
					} else {
						ll.setBackgroundResource(R.drawable.app_list_corner_shape);
					}
				}

				try {
					context.startActivity(intent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		return convertView;
	}

}
