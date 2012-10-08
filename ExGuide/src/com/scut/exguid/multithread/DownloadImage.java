package com.scut.exguid.multithread;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;

import com.scut.exguide.assist.MyActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

//���ַ������ͷֱ������������ִ�е����������������̨����ִ�еĽ��ȡ�������̨�����������͡������ض������£�
//�������������Ͷ���ʹ�ã����û�б�ʹ�ã�������java.lang.Void���ʹ��档
//���������Ҫ�����ص���ͼƬ�õ�
public class DownloadImage extends
		AsyncTask<String[], java.lang.Void, ArrayList<Bitmap>> {

	public MyActivity mInstance;
	
	public DownloadImage(MyActivity Instance) {
		mInstance = Instance;
	}
	
	
	
	/**
	 * ����ͼƬ�ĺ�̨����
	 */
	@Override
	protected ArrayList<Bitmap> doInBackground(String[]... params) {
		// TODO Auto-generated method stub
		try {
			return getImage(params[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	

	@Override
	protected void onPostExecute(ArrayList<Bitmap> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		mInstance.UpdateImage(result);
		
	}



	/**
	 * ��������ͼƬ
	 * 
	 * @param url
	 * @return
	 */
	public Bitmap getImage(String romtepath) throws Exception {
		URL url = new URL(romtepath);// ��ȡ��·��
		// httpЭ�����Ӷ���
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");// �����ǲ�����д�ģ��꿴API����
		conn.setConnectTimeout(6 * 1000);
		Bitmap bitmap = null;
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			byte[] data = readStream(inputStream);
			File file = new File("smart.jpg");// ��ͼƬ������
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);			
			FileOutputStream outStream = new FileOutputStream(file);// д������
			outStream.write(data);// д��
			outStream.close(); // �ر���
		}
		return bitmap;
	}

	/**
	 * ���ض��ͼƬ
	 * 
	 * @param remotepath
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Bitmap> getImage(String[] remotepath) throws IOException {
		ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
		Bitmap bitmap = null;
		for (int i = 0; i < remotepath.length; i++) {
			try {
				URL url = new URL(remotepath[i]);// ��ȡ��·��
				// httpЭ�����Ӷ���
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setRequestMethod("GET");// �����ǲ�����д�ģ��꿴API����
				conn.setConnectTimeout(6 * 1000);
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					InputStream inputStream = conn.getInputStream();
					byte[] data = readStream(inputStream);
					File file = new File("smart.jpg");// ��ͼƬ������
					bitmap = BitmapFactory
							.decodeByteArray(data, 0, data.length);
					bitmapList.add(bitmap);
					FileOutputStream outStream = new FileOutputStream(file);// д������
					outStream.write(data);// д��
					outStream.close(); // �ر���
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return bitmapList;

	}

	private byte[] readStream(InputStream inStream) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024]; // ������װ
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outstream.write(buffer, 0, len);
		}
		outstream.close();
		inStream.close();
		// �ر���һ��Ҫ�ǵá�
		return outstream.toByteArray();

	}

}
