package com.scut.exguide.entity;

/**
 * ����չ���ʵ��
 * @author fatboy
 *
 */
public class Location {
	
	public static final int TAG = 2;

	private String mTitle;//չ�������
	private String mProvince;//����ʡ
	private String mCity;//���ڳ���
	private String mHall;//����չ��
	private int mID;//��Ӧ��ID
	
	public Location(String title, String province, String city, String hall, int id) {
		mTitle = title;
		mProvince = province;
		mCity = city;
		mHall = hall;
		mID = id;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public String getProvince() {
		return mProvince;
	}
	
	public String getCity() {
		return mCity;
	}
	
	public String getHall() {
		return mHall;
	}
	
	public int getId() {
		return mID;
	}
}
