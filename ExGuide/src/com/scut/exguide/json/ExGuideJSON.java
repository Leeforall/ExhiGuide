package com.scut.exguide.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.scut.exguide.entity.Exhibition;

import android.util.Log;

public class ExGuideJSON {

	public static final String BASE_URL = "";

	public JSONObject getExhibitioninfo(int Id) {
		String url = BASE_URL + "?option=GetExhibition" + "&id=" + Id;
		HttpGet request = new HttpGet(url);
		JSONObject jsonObject = null;
		/* StringBulder,jdk_1.5�������÷�������StringBufferһ������Ч��Ҫ��StringBuffer�ߵö� */
		StringBuilder sbuilder = new StringBuilder();
		try {
			/* ģ��������ͻ��� */
			HttpClient client = new DefaultHttpClient();
			;
			/* ��ȡ�ͻ��˶������������������Ӧ��� */
			HttpResponse response = client.execute(request);
			/* �����Ƿ�����ɹ� */
			System.out.println(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));
				for (String s = reader.readLine(); s != null; s = reader
						.readLine()) {
					// s=new String(s.getBytes("UNICODE"), "UTF-8");
					sbuilder.append(s);
				}
				Log.i("json_str", sbuilder.toString());
				jsonObject = new JSONObject(sbuilder.toString());
				Log.i("id", jsonObject.getInt("id") + "");

				Log.i("website_name", jsonObject.getString("site_name"));

				Log.i("website_url", jsonObject.getString("site_url"));

				Log.i("category", jsonObject.getInt("category") + "");

				Log.i("title", jsonObject.getString("title"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;

	}

	public JSONObject getThings(int Id) {
		String url = BASE_URL + "?option=GetExhibition" + "&id=" + Id;
		HttpGet request = new HttpGet(url);
		JSONObject jsonObject = null;
		/* StringBulder,jdk_1.5�������÷�������StringBufferһ������Ч��Ҫ��StringBuffer�ߵö� */
		StringBuilder sbuilder = new StringBuilder();
		try {
			/* ģ��������ͻ��� */
			HttpClient client = new DefaultHttpClient();
			;
			/* ��ȡ�ͻ��˶������������������Ӧ��� */
			HttpResponse response = client.execute(request);
			/* �����Ƿ�����ɹ� */
			System.out.println(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));
				for (String s = reader.readLine(); s != null; s = reader
						.readLine()) {
					// s=new String(s.getBytes("UNICODE"), "UTF-8");
					sbuilder.append(s);
				}
				Log.i("json_str", sbuilder.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;

	}

	/**
	 * ��ȡչ����Ϣ�Ľӿڣ�
	 * 
	 * @return ����JSONArray�Ķ�����Ҫ����
	 */
	public ArrayList<Exhibition> getExhibitions() {
		ArrayList<Exhibition> datas = new ArrayList<Exhibition>();

		String url = "http://192.168.1.101/exhibition/GetExhiList.php";
		/* ģ�������GET���� */
		HttpGet request = new HttpGet(url);
		JSONArray jsonArray = null;
		/* StringBulder,jdk_1.5�������÷�������StringBufferһ������Ч��Ҫ��StringBuffer�ߵö� */
		StringBuilder sbuilder = new StringBuilder();
		try {
			/* ģ��������ͻ��� */
			HttpClient client = new DefaultHttpClient();
			;
			/* ��ȡ�ͻ��˶������������������Ӧ��� */
			HttpResponse response = client.execute(request);
			/* �����Ƿ�����ɹ� */
			System.out.println(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));
				for (String s = reader.readLine(); s != null; s = reader
						.readLine()) {
					// s=new String(s.getBytes("UNICODE"), "UTF-8");
					sbuilder.append(s);
				}
				Log.i("json_str", sbuilder.toString());

				jsonArray = new JSONArray(sbuilder.toString());
				for (int i = 0; i < jsonArray.length(); i++) {

					JSONObject jsonObject = jsonArray.getJSONObject(i);

					Log.i("id", jsonObject.getInt("id") + "");
					Log.i("name", jsonObject.getString("name"));
					Log.i("province", jsonObject.getString("province"));
					Log.i("city", jsonObject.getString("city") + "");
					Log.i("hall", jsonObject.getString("hall"));

					Exhibition ex = new Exhibition(
							jsonObject.getString("name"),
							jsonObject.getString("province"),
							jsonObject.getString("city"),
							jsonObject.getString("hall"),
							jsonObject.getInt("id"));

					datas.add(ex);
				}
				// JSONArray jssonArray=json.toJSONArray(jssonArray);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return datas;
	}
}
