package com.scut.exguide.ui;

import com.scut.exguide.assist.RoundListView;
import com.scut.exguide.assist.RoundListViewAdapter_exhibition;
import com.scut.exguide.assist.ScrollExpandListViewUtil;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

/**
 * ���listactivity�Ǹ��û�ѡ����һ������
 * 
 * @author fatboy
 * 
 */
public class ExhiSelectListActivity extends Activity {

	private ListView listviewExhibition;// ѡ���չ��listview

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.listview);

		RoundListViewAdapter_exhibition exhibitionAdapter = new RoundListViewAdapter_exhibition(
				this);

		listviewExhibition = (RoundListView) findViewById(R.id.roundlistview02);
		listviewExhibition.setAdapter(exhibitionAdapter);
		ScrollExpandListViewUtil
				.setListViewHeightBasedOnChildren(listviewExhibition);
		
		Intent intent = new Intent();
		intent.setClass(ExhiSelectListActivity.this, ExGuideTutorialsActivity.class);
		startActivity(intent);
	}
	

}
