package com.scut.exguide.ui;

import java.util.ArrayList;

import com.scut.exguid.multithread.DownloadImage;
import com.scut.exguide.assist.ExhibitsPageAdapter;
import com.scut.exguide.assist.ExhibitsPageChangeListener;
import com.scut.exguide.assist.MyActivity;
import com.scut.exguide.assist.PosterPageAdapter;
import com.scut.exguide.assist.PosterPageChangeListener;

import android.R.drawable;
import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import android.os.Bundle;

import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.view.ViewGroup.LayoutParams;

import android.widget.ImageView;
import android.widget.LinearLayout;

public class ExhiHomeActivity extends ActivityGroup implements MyActivity {

	private final static String TAG = "ExhiHomeActivity";
	private LayoutInflater inflater;

	private ViewPager mPviewPager; // viewpager�ؼ�
	private ArrayList<View> mPosterPageViews;// ���ڷ�ҳ��view

	private ImageView _imageView;// ��ʱ����
	private ImageView[] imageViews;// СԲ���view
	// ��Ʒ��ҳ����
	private ViewGroup main;
	// �Ϸ��ĺ���
	private ViewGroup mPosterChannel;
	// �����е�СԲ��
	private ViewGroup mDotgroup;
	// �·��Ĳ�Ʒ��Ϣ
	private ViewGroup mExhibitsChannel;

	// �²���Ʒ�����е�view
	private ViewPager mEviewPager;
	private ArrayList<View> mExhibitsPageView;

	// �²���Ʒ�����е�����ͷ
	private ViewGroup mItemHeader;
	private LinearLayout[] mItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		inflater = getLayoutInflater();

		main = (ViewGroup) inflater.inflate(R.layout.exhibition, null);

		initalExhibits();

		initalItemHeader();

		setContentView(main);

		String[] url = {
				"http://auto.ce.cn/zt/2009/SHAutoShow/wgcz/E4/E4logo/200904/23/W020090423825631031078.jpg",
				"http://auto.msn.com.cn/images/auto_international/eu/2009/2/10/20092105da1cc54f1d247e0b1729deb544ce25e.jpg",
				"http://www.soccergaming.com/wp-content/uploads/2009/04/wcg-usa-2009.jpg" };
		new DownloadImage(this).execute(url);
		

	}

	/**
	 * ��ʼ���²���Ʒ����
	 */
	@SuppressWarnings("deprecation")
	private void initalExhibits() {
		mExhibitsChannel = (ViewGroup) main.findViewById(R.id.exhibits);

		mEviewPager = (ViewPager) mExhibitsChannel
				.findViewById(R.id.ExhibitsguidePages);

		mItemHeader = (ViewGroup) mExhibitsChannel
				.findViewById(R.id.item_header);

		Intent intent = new Intent();
		intent.setClass(ExhiHomeActivity.this, ExhiAttributeListActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		Intent intent2 = new Intent();
		intent2.setClass(ExhiHomeActivity.this, ExhiVedioSelectListActivity.class);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		mExhibitsPageView = new ArrayList<View>();
		mExhibitsPageView.add(getLocalActivityManager().startActivity(
				"ExhiAttributeListActivity", intent).getDecorView());

		mExhibitsPageView.add(getLocalActivityManager().startActivity("ExhiVedioSelectListActivity",
				intent2).getDecorView());
		
		

		mItem = new LinearLayout[mExhibitsPageView.size()];

		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				mItem[i] = (LinearLayout) mItemHeader.findViewById(R.id.infol);
				mItem[i].setBackgroundColor(Color.rgb(204, 204, 204));
			} else {
				mItem[i] = (LinearLayout) mItemHeader.findViewById(R.id.medial);
				mItem[i].setBackgroundColor(Color.rgb(255, 255, 255));
			}

		}

		mEviewPager.setAdapter(new ExhibitsPageAdapter(mExhibitsPageView));

		mEviewPager.setOnPageChangeListener(new ExhibitsPageChangeListener(
				mItem));
	}

	/*
	 * ��ʼ���ϲ�������
	 */
	private void UpdatePoster(ArrayList<View> viewList) {

		// mPosterPageViews = new ArrayList<View>(); // ��������
		// mPosterPageViews.add(inflater.inflate(R.layout.poster1, null)); //
		// ��Ҫ�Ǹ�չʾ��ҳ�����������
		// mPosterPageViews.add(inflater.inflate(R.layout.poster2, null));

		imageViews = new ImageView[viewList.size()]; // ����СԲ��

		mPosterChannel = (ViewGroup) main.findViewById(R.id.main);

		mDotgroup = (ViewGroup) mPosterChannel.findViewById(R.id.viewGroup);

		initalDot();// ��ʼ��СԲ��

		mPviewPager = (ViewPager) mPosterChannel.findViewById(R.id.guidePages);

		mPviewPager.setAdapter(new PosterPageAdapter(viewList));

		mPviewPager.setOnPageChangeListener(new PosterPageChangeListener(
				imageViews));
	}

	/**
	 * �����Ϸ�����ͼƬ
	 * 
	 * @param bitmaps
	 * @return
	 */
	private ArrayList<View> CreateViewList(ArrayList<Bitmap> bitmaps) {
		ImageView _imageView;
		ArrayList<View> viewList = new ArrayList<View>();
		for (int i = 0; i < bitmaps.size(); i++) {
			_imageView = new ImageView(this);
			_imageView.setImageBitmap(bitmaps.get(i));
			viewList.add(_imageView);
		}
		return viewList;
	}

	/**
	 * ��ʼ����ҳ��СԲ��
	 */
	private void initalDot() {
		// ��ʼ��СԲ��
		for (int i = 0; i < imageViews.length; i++) {
			_imageView = new ImageView(ExhiHomeActivity.this);
			_imageView.setLayoutParams(new LayoutParams(20, 20));
			_imageView.setPadding(20, 0, 20, 0);
			imageViews[i] = _imageView;
			if (i == 0) {
				// Ĭ��ѡ�е�һ��ͼƬ
				imageViews[i]
						.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				imageViews[i].setBackgroundResource(R.drawable.page_indicator);
			}

			mDotgroup.addView(imageViews[i]);
		}
	}

	/**
	 * ��ʼ��itemheader
	 */
	private void initalItemHeader() {

	}

	public String getTag() {
		// TODO Auto-generated method stub
		return TAG;
	}

	public void UpdateImage(ArrayList<Bitmap> bitmapsList) {
		// TODO Auto-generated method stub
		UpdatePoster(CreateViewList(bitmapsList));

	}

	@Override
	public void Update(Object... param) {
		// TODO Auto-generated method stub
		
	}
}
