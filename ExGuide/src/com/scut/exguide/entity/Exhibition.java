package com.scut.exguide.entity;

/**
 * ����չ���ʵ��
 * @author fatboy
 *
 */
public class Exhibition {
	
	public static final int TAG = 2;

	private String mName;//չ�������
	private String mProvince;//����ʡ
	private String mCity;//���ڳ���
	private String mHall;//����չ��
	private int mID;//��Ӧ��ID
	
	public Exhibition(){
		
	}
	
	public Exhibition(String title, String province, String city, String hall, int id) {
		mName = title;
		mProvince = province;
		mCity = city;
		mHall = hall;
		mID = id;
	}
	
	public String getName() {
		return mName;
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
