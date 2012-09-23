package com.scut.exguide.entity;

import android.graphics.Bitmap;

/**
 * ���Ƕ�ӦͼƬ��ʵ��
 * @author fatboy
 *
 */
public class Poster {
	public static final int TAG = 3;

	private Bitmap mPoster;
	private int mID;
	
	public Poster(Bitmap bitmap, int id) {
		mPoster = bitmap;
		mID = id;
	}
	
	public Bitmap getPoster() {
		return mPoster;
	}
	
	public int getId() {
		return mID;
	}
	
}
