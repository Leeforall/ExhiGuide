package com.scut.exguide.assist;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ScrollExpandListViewUtil {

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		// ��ȡListView��Ӧ��Adapter

		int totalHeight = 0;
		ListAdapter listAdapter = listView.getAdapter();
		// listAdapter.notifyDataSetChanged();
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()�������������Ŀ

			View listItem = listAdapter.getView(i, null, listView);

			listItem.measure(0, 0); // ��������View �Ŀ��

			totalHeight += listItem.getMeasuredHeight(); // ͳ������������ܸ߶�

		}
		System.out.println(listAdapter.getCount());
		ViewGroup.LayoutParams params = listView.getLayoutParams();

		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));

		// listView.getDividerHeight()��ȡ�����ָ���ռ�õĸ߶�

		// params.height���õ�����ListView������ʾ��Ҫ�ĸ߶�

		listView.setLayoutParams(params);

	}
}
