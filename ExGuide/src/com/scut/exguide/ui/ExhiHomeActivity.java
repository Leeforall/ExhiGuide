package com.scut.exguide.ui;

import java.util.ArrayList;

import com.scut.exguid.multithread.DownloadImage;
import com.scut.exguide.assist.ExhibitsPageAdapter;
import com.scut.exguide.assist.ExhibitsPageChangeListener;
import com.scut.exguide.assist.MenuListAdapter;
import com.scut.exguide.assist.MyActivity;
import com.scut.exguide.assist.PosterPageAdapter;
import com.scut.exguide.assist.PosterPageChangeListener;

import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.view.ViewPager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

@SuppressWarnings("deprecation")
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
	
	private Button menuBtn;
	private View[] children;
	
	// �²���Ʒ�����е�view
	private ViewPager mEviewPager;
	private ArrayList<View> mExhibitsPageView;

	// �²���Ʒ�����е�����ͷ
	private ViewGroup mItemHeader;
	private LinearLayout[] mItem;

	private MenuHorizontalScrollView scrollView;
	private ListView menuList;
	private MenuListAdapter menuListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		inflater = LayoutInflater.from(this);
		main = (ViewGroup) inflater.inflate(R.layout.exhibition, null);

		setContentView(inflater.inflate(R.layout.menu_scroll_view, null));
		this.scrollView = (MenuHorizontalScrollView) findViewById(R.id.mScrollView);
		this.menuListAdapter = new MenuListAdapter(this, 0);
		this.menuList = (ListView) findViewById(R.id.menuList);
		this.menuList.setAdapter(menuListAdapter);

		this.menuBtn = (Button)this.main.findViewById(R.id.menuBtn);
		this.menuBtn.setOnClickListener(onClickListener);
		
		View leftView = new View(this);
		leftView.setBackgroundColor(Color.TRANSPARENT);
		children = new View[]{leftView, main};
		this.scrollView.initViews(children, new com.scut.exguide.assist.SizeCallBackForMenu(this.menuBtn), this.menuList);
		this.scrollView.setMenuBtn(this.menuBtn);
		
		initalExhibits();

		initalItemHeader();

		String[] url = {
				"http://pic.iresearch.cn/news/0468/20120903/0082@56936.jpg",
				"http://nuomi.xnimg.cn/upload/deal//V_L/34075.jpg" };
		new DownloadImage(this).execute(url);

	}

	private OnClickListener onClickListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			scrollView.clickMenuBtn();
		}
	};
	
	public MenuHorizontalScrollView getScrollView() {
		return scrollView;
	}

	public void setScrollView(MenuHorizontalScrollView scrollView) {
		this.scrollView = scrollView;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(MenuHorizontalScrollView.menuOut == true)
				this.scrollView.clickMenuBtn();
			else
				this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}	
	
	/**
	 * ��ʼ���²���Ʒ����
	 */
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
		intent2.setClass(ExhiHomeActivity.this,
				ExhiVedioSelectListActivity.class);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		mExhibitsPageView = new ArrayList<View>();
		mExhibitsPageView.add(getLocalActivityManager().startActivity(
				"ExhiAttributeListActivity", intent).getDecorView());

		mExhibitsPageView.add(getLocalActivityManager().startActivity(
				"ExhiVedioSelectListActivity", intent2).getDecorView());

		mItem = new LinearLayout[mExhibitsPageView.size()];

		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				mItem[i] = (LinearLayout) mItemHeader.findViewById(R.id.infol);
				mItem[i].setBackgroundColor(Color.rgb(153, 153, 153));
			} else {
				mItem[i] = (LinearLayout) mItemHeader.findViewById(R.id.medial);
				mItem[i].setBackgroundColor(Color.rgb(51, 51, 51));
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
			// _imageView = new ImageView(this);
			View temp = inflater.inflate(R.layout.poster2, null);
			_imageView = (ImageView) temp.findViewById(R.id.imageView1);
			// _imageView.setImageBitmap(bitmaps.get(i));

			_imageView.setImageBitmap(bitmaps.get(i));
			// viewList.add(_imageView);
			viewList.add(temp);
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

	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return TAG;
	}

	@Override
	public void UpdateImage(ArrayList<Bitmap> bitmapsList) {
		// TODO Auto-generated method stub
		UpdatePoster(CreateViewList(bitmapsList));

	}

	@Override
	public void Update(Object... param) {
		// TODO Auto-generated method stub

	}
}
