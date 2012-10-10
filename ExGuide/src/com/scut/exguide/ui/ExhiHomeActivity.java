package com.scut.exguide.ui;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


import com.baidu.oauth2.BaiduOAuth;
import com.baidu.oauth2.BaiduOAuthViaDialog;
import com.baidu.oauth2.BaiduOAuthViaDialog.DialogListener;
import com.baidu.pcs.PcsClient;
import com.scut.exguid.multithread.DownloadImage;
import com.scut.exguide.assist.ExhibitsPageAdapter;
import com.scut.exguide.assist.ExhibitsPageChangeListener;
import com.scut.exguide.assist.MenuListAdapter;
import com.scut.exguide.assist.MyActivity;
import com.scut.exguide.assist.PosterPageAdapter;
import com.scut.exguide.assist.PosterPageChangeListener;
import com.scut.exguide.utils.TransistionUtil;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;

import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class ExhiHomeActivity extends ActivityGroup implements MyActivity {
	// ����Menu�˵�ѡ���ItemId
	final static int ONE = Menu.FIRST;
	final static int TWO = Menu.FIRST + 1;
	final static int THREE = Menu.FIRST + 2;
	final static int FOUR = Menu.FIRST + 3;
	final static int FIVE = Menu.FIRST + 4;
	/*
	 * �ٶ�PCS��֤
	 */
	final static private String APP_KEY = "GqRQpN9bayVt6yIAzrqsUbnU";
	final static private String APP_ROOT = "/apps/չ�ᵼ�α�";
	private PcsClient mPcsClient = null;// �ͻ��˶���
	private BaiduOAuth mBaiduoauth;// �ٶȷ�װ����֤

	private final static String TAG = "ExhiHomeActivity";
	private LayoutInflater inflater;

	private ViewPager mPoserViewPager; // viewpager�ؼ�
	private ArrayList<View> mPosterPages;// ���ڷ�ҳ��view

	// �²���Ʒ�����е�view
	private ViewPager mInfoViewPager;
	private ArrayList<View> mInfoPages;

	private ImageView _imageView;// ��ʱ����
	private ImageView[] DotImageViews;// СԲ���view

	// ��Ʒ��ҳ����
	private ViewGroup main;
	// �Ϸ��ĺ���
	private ViewGroup mPosterChannel;
	// �����е�СԲ��
	private ViewGroup mDotgroup;
	// �·��Ĳ�Ʒ��Ϣ
	private ViewGroup mInfoChannel;
	// ͼƬ��progressbar
	private ProgressBar mPosterProgressBar;

	// �²���Ʒ�����е�����ͷ
	private ViewGroup mItemHeader;
	private LinearLayout[] mItem;

	// չ�������
	private TextView mExhibitionName;

	private Button menuBtn;
	private View[] children;

	private MenuHorizontalScrollView scrollView;
	private ListView menuList;
	private MenuListAdapter menuListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		inflater = LayoutInflater.from(this);
		main = (ViewGroup) inflater.inflate(R.layout.homeactivity, null);
		// setContentView(inflater.inflate(R.layout.menu_scroll_view, null));
		// this.scrollView = (MenuHorizontalScrollView)
		// findViewById(R.id.mScrollView);
		// this.menuListAdapter = new MenuListAdapter(this, 0);
		// this.menuList = (ListView) findViewById(R.id.menuList);
		// this.menuList.setAdapter(menuListAdapter);
		//
		// this.menuBtn = (Button) this.main.findViewById(R.id.menuBtn);
		// this.menuBtn.setOnClickListener(onClickListener);
		//
		// View leftView = new View(this);
		// leftView.setBackgroundColor(Color.TRANSPARENT);
		// children = new View[] { leftView, main };
		// this.scrollView.initViews(children,
		// new com.scut.exguide.assist.SizeCallBackForMenu(this.menuBtn),
		// this.menuList);
		// this.scrollView.setMenuBtn(this.menuBtn);

		initalLayout();

		initalInfo();

		initalItemHeader();
		setContentView(main);

		String[] url = {
				"http://pic.iresearch.cn/news/0468/20120903/0082@56936.jpg",
				"http://nuomi.xnimg.cn/upload/deal//V_L/34075.jpg" };
		new DownloadImage(this).execute(url);

	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			scrollView.clickMenuBtn();
			Log.d("aaa", MenuHorizontalScrollView.menuOut + "");
			if (!MenuHorizontalScrollView.menuOut) {
				main.setEnabled(false);
			} else
				main.setEnabled(true);

		}
	};

	public MenuHorizontalScrollView getScrollView() {
		return scrollView;
	}

	public void setScrollView(MenuHorizontalScrollView scrollView) {
		this.scrollView = scrollView;
	}

	// ����Menu�˵�
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, ONE, 0, "�󶨰ٶ�"); // ����������ͼ��
		menu.add(0, TWO, 0, "����");
		menu.add(0, THREE, 0, "����");
		menu.add(0, FOUR, 0, "����");
		menu.add(0, FIVE, 0, "�˳�");
		return super.onCreateOptionsMenu(menu);
	}

	// ���Menu�˵�ѡ����Ӧ�¼�
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch (item.getItemId()) {
		case 1:
			this.getBaiduOAuth();
			break;
		/*----------------------------------------------------*/
		case 2:
			intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_SUBJECT, "����");
			intent.putExtra(Intent.EXTRA_TEXT, "չ�ᱦ");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(Intent.createChooser(intent, "����"));
			break;
		/*----------------------------------------------------*/
		case 4:
			intent.setClass(this, ExGuideTutorialsActivity.class);
			startActivity(intent);
			break;
		case 5:
			AlertDialog alertDialog = new AlertDialog.Builder(this)
					.setTitle("��ʾ")
					.setMessage("�Ƿ��˳�������")
					.setPositiveButton(
							"ȷ��",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									finish();
								}
							})
					.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									return;
								}
							}).create();
			alertDialog.show();
			break;
		default:

		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog alertDialog = new AlertDialog.Builder(this)
					.setTitle("��ʾ")
					.setMessage("�Ƿ��˳�������")
					.setPositiveButton(
							"ȷ��",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									finish();
								}
							})
					.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									return;
								}
							}).create();
			alertDialog.show();
		}
		return false;
	}

	/**
	 * ���������һЩ�ؼ���ʼ��
	 */
	private void initalLayout() {
		mInfoChannel = (ViewGroup) main.findViewById(R.id.info);

		mInfoViewPager = (ViewPager) mInfoChannel
				.findViewById(R.id.infoviewpages);

		mPosterChannel = (ViewGroup) main.findViewById(R.id.poster);

		mDotgroup = (ViewGroup) mPosterChannel.findViewById(R.id.dotviewgroup);

		mPoserViewPager = (ViewPager) mPosterChannel
				.findViewById(R.id.posterviewpages);

		mItemHeader = (ViewGroup) mInfoChannel.findViewById(R.id.item_header);

		mPosterProgressBar = (ProgressBar) mPosterChannel
				.findViewById(R.id.posterloading);

		// //��������ҵ��ҵ���չ������
		ViewGroup title = (ViewGroup) main.findViewById(R.id.title);
		mExhibitionName = (TextView) title.findViewById(R.id.exhibitionname);
	}

	/**
	 * ��ʼ���²���Ʒ����
	 */
	private void initalInfo() {
		// mInfoChannel = (ViewGroup) main.findViewById(R.id.info);

		/*
		 * �������Ƶ��Activity
		 */
		// mInfoViewPager = (ViewPager) mInfoChannel
		// .findViewById(R.id.infoviewpages);

		Intent intent = new Intent();
		intent.setClass(ExhiHomeActivity.this, ExhiAttributeListActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		Intent intent2 = new Intent();
		intent2.setClass(ExhiHomeActivity.this,
				ExhiVedioSelectListActivity.class);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		mInfoPages = new ArrayList<View>();
		mInfoPages.add(getLocalActivityManager().startActivity(
				"ExhiAttributeListActivity", intent).getDecorView());

		mInfoPages.add(getLocalActivityManager().startActivity(
				"ExhiVedioSelectListActivity", intent2).getDecorView());

	}

	/*
	 * ��ʼ���ϲ������� �����Ƿ��ص�imageview
	 */
	private void UpdatePoster(ArrayList<View> viewList) {

		DotImageViews = new ImageView[viewList.size()]; // ����СԲ��

		// mPosterChannel = (ViewGroup) main.findViewById(R.id.poster);
		//
		// mDotgroup = (ViewGroup)
		// mPosterChannel.findViewById(R.id.dotviewgroup);
		//
		initalDot();// ��ʼ��СԲ��
		//
		// mPoserViewPager = (ViewPager) mPosterChannel
		// .findViewById(R.id.posterviewpages);

		mPoserViewPager.setAdapter(new PosterPageAdapter(viewList));

		mPoserViewPager.setOnPageChangeListener(new PosterPageChangeListener(
				DotImageViews));
	}

	/**
	 * �����Ϸ�����ͼƬ
	 * 
	 * @param bitmaps
	 * @return
	 */
	private ArrayList<View> CreatePosterView(ArrayList<Bitmap> bitmaps) {

		ImageView _imageView;
		ArrayList<View> viewList = new ArrayList<View>();
		for (int i = 0; i < bitmaps.size(); i++) {
			View temp = inflater.inflate(R.layout.poster, null);
			_imageView = (ImageView) temp.findViewById(R.id.show);
			_imageView.setImageBitmap(bitmaps.get(i));
			viewList.add(temp);
		}
		return viewList;
	}

	/**
	 * ��ʼ����ҳ��СԲ��
	 */
	private void initalDot() {
		// ��ʼ��СԲ��
		for (int i = 0; i < DotImageViews.length; i++) {
			_imageView = new ImageView(ExhiHomeActivity.this);
			_imageView.setLayoutParams(new LayoutParams(20, 20));
			_imageView.setPadding(20, 0, 20, 0);
			DotImageViews[i] = _imageView;
			if (i == 0) {
				// Ĭ��ѡ�е�һ��ͼƬ
				DotImageViews[i]
						.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				DotImageViews[i]
						.setBackgroundResource(R.drawable.page_indicator);
			}
			mDotgroup.addView(DotImageViews[i]);
		}
	}

	/**
	 * ��ʼ��itemheader
	 */
	private void initalItemHeader() {
		// mItemHeader = (ViewGroup)
		// mInfoChannel.findViewById(R.id.item_header);

		mItem = new LinearLayout[mInfoPages.size()];
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				mItem[i] = (LinearLayout) mItemHeader.findViewById(R.id.infol);
				mItem[i].setBackgroundColor(Color.rgb(153, 153, 153));
			} else {
				mItem[i] = (LinearLayout) mItemHeader.findViewById(R.id.medial);
				mItem[i].setBackgroundColor(Color.rgb(51, 51, 51));
			}
		}

		mInfoViewPager.setAdapter(new ExhibitsPageAdapter(mInfoPages));
		mInfoViewPager.setOnPageChangeListener(new ExhibitsPageChangeListener(
				mItem));

	}

	/**
	 * ������
	 */
	public void setProgressBar(boolean flag) {
		if (!flag) {
			mPosterProgressBar.setVisibility(View.GONE);
		} else {
			mPosterProgressBar.setVisibility(View.VISIBLE);
		}

	}

	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return TAG;
	}

	/**
	 * 1�Ǹ��º���
	 */
	@Override
	public void Update(Object... param) {
		// TODO Auto-generated method stub

		switch (((Integer) param[1])) {
		case 1: {
			ArrayList<Bitmap> bitmapsList = (ArrayList<Bitmap>) param[0];
			UpdatePoster(CreatePosterView(bitmapsList));
		}
			break;
		}

	}

	/*
	 * �ٶ�PCS��֤����
	 */
	public void getBaiduOAuth() {
		mBaiduoauth = new BaiduOAuthViaDialog(APP_KEY);

		mBaiduoauth.startDialogAuth(ExhiHomeActivity.this, new String[] {
				"basic", "netdisk" }, new DialogListener() {

			@Override
			public void onCancel() {
				Toast.makeText(getApplicationContext(),
						"User  cancel the request", Toast.LENGTH_LONG).show();
			}

			/*
			 * ���access_token (non-Javadoc)
			 * 
			 * @see
			 * com.baidu.oauth2.BaiduOAuthViaDialog.DialogListener#onComplete
			 * (android.os.Bundle)
			 */
			@Override
			public void onComplete(Bundle values) {
				String accessToken = values.getString("access_token");
				Toast.makeText(getApplicationContext(), accessToken,
						Toast.LENGTH_LONG).show();

			}

			@Override
			public void onException(String msg) {
				Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
						.show();
			}
		});
	}
	
	
	/**
	 * �����ķ���
	 */
	
	@Override
	public void onNewIntent(Intent intent) {
		setIntent(intent);
		resolveIntent(intent);

	}

	void resolveIntent(Intent intent) {
		// Parse the intent
		String action = intent.getAction();
		if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
			// Get an instance of the TAG from the NfcAdapter
			Tag productTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

			MifareClassic mfc = MifareClassic.get(productTag);

			try {
				// Conncet to card
				mfc.connect();
				boolean auth = false;
				auth = mfc.authenticateSectorWithKeyA(0,
						MifareClassic.KEY_DEFAULT);

				if (auth) {
					byte[] data = mfc.readBlock(1);
					char[] cData = TransistionUtil.getChars(data);
					String mBarcodeStr = String.valueOf(cData).trim(); // ע��Ҫȥ���ո񡣡�
					//DownloadInfo();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				Toast.makeText(getApplicationContext(), "����ʧ��\n������ˢȡ��Ƭ",
						Toast.LENGTH_SHORT).show();
			}
		} else {
			
		}

	}

}
