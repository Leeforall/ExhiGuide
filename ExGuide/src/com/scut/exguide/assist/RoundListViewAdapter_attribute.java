package com.scut.exguide.assist;



import com.scut.exguide.ui.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class RoundListViewAdapter_attribute extends BaseAdapter implements
		ListAdapter {

	final String[] strs1 = { "����", "����", "������", "���������(mm)", "����ṹ" 
			, "��߳���(km/h)", "�ٷ�0-100����(s)", "ʵ��0-100����(s)","ʵ��100-0�ƶ�(m)","ʵ���ͺ�(L)"};
	final String[] strs2= { "һ��-����", "�����ͳ�", "1.6L 105���� L4", "5���ֶ�", "4��5�����ᳵ" 
			, "185", "11.8", "","",""};
	
	
	final String[] strs3={"��Ϸ����", "��������", "��Ϸ����", "���й�˾", "���԰汾", "����ʱ��", "����ƽ̨"  };
	final String[] strs4={"��Ĺ��Ӱ9", "Crystal Dynamics", "������Ϸ", "", "Ӣ��" , "", "PC PS3 X360" };
	
	final String[] strs5={"���㳡��", "��������", "��װ�ṩ", "�����ṩ", "��ױ�ṩ", "�����ŵ" };
	final String[] strs6={"ʱ�н־�+���л�԰+�ھ�����ѡ��", "Crystal Dynamics", "�ṩ��װ3�ף����Դ���������3���ڣ�", "רҵ����3��", "��ѻ�ױ���ͣ��ṩרҵ̨�������Ŀ��ëһ�ԣ��ṩ��������Ҫ�����о���������Ʒ������ˡ��ٷ������ε�" , "������ŵ�Ӿ�֣�س�ŵ����ϵ���κ���������" };
	
	
	final String[] strs7={"��չ��", "չ������" };
	final String[] strs8={"һ������/���ݱ���/GMC/��ʱ��/������", "�����й��޴�����������г��Ϳ��ٷ�չ���й�������ҵ��������������չ������չ����ģ�����ʻ�ˮ׼��չƷ�����Լ���ȫ���Ӱ���������ߣ��ܵ����������硢���Ž��������ĸ߶ȹ�ע�ͻ������롣�ڶ����֪��������˾��������������չ������Ϊȫ������Ҫ�Ĺ��ʼ���չ���й�����������ҵҲ��������������չ������Ϊչʾ����֪ʶƷ�ơ��Ƴ����¿Ƽ��ɹ�����ѡƽ̨��"};
	
	final String[] strs9={"��չ��", "����","��Ʊ", "չ������" };
	final String[] strs10={" KONAMI�����տա�SQUARE ENIX", "2012��9��20��-9��23��","1000��Ԫ","20��21������Ϊý��/�����գ�22��23������Ϊ������"};
	
	final String[] strs11={"����", "չ������" };
	final String[] strs12={"2012��12��8-9��", "�й��鲩��--�������žٰ�Ĺ��Ҽ��鲩�ᡢȫ�򳬴��ģ�����缶Ʒ�ƽ��չ��ÿ���ڱ������Ϻ������ݵȵ�ͬʱ�ٰ촺���ﶬ�ļ�չ���Ⱥ���30������ҵ���Ʒ���ꡢ�����ʦ�������������й��鲩�ᷢ��ÿ������ǰ�ؽ��ʱ�У��й��鲩���ѳ�Ϊ�й�����������ѡ�Ľ��ɹ�Ʒ��ƽ̨���й����ʱ�з���ꡣ2012�����й��鲩�Ὣ��12��8��-9���ٴ��ڹ��һ�������¡���ٿ�����ʱ������Ӣ�����������⡢�ա���������3000�����ʹ��ڶ���Ʒ�ơ�50�������Ʒ�����ֽ��ͼҵ硭����3000��ʢ���ػݱ���������ˣ��������ܸ��Լ۱�һվʽ���ɹ��������ɳﱸ����Ʒ�ʻ���"};
	
	private Context context;
	private LayoutInflater inflater;

	public RoundListViewAdapter_attribute(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return strs11.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = inflater.inflate(R.layout.item_attribute, null);
		final View ll = convertView;
		TextView tv = (TextView) convertView
				.findViewById(R.id.navigation_more_checklist01_textview);
		tv.setText(strs11[position]);

		TextView tv2 = (TextView) convertView
				.findViewById(R.id.navigation_more_checklist02_textview);
		tv2.setText(strs12[position]);
		
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();

				// ��ɫ
				switch (position) {
				case 0:
					ll.setBackgroundResource(R.drawable.app_list_corner_round_top);
					break;

				default:
					if (position == strs1.length - 1) {
						ll.setBackgroundResource(R.drawable.app_list_corner_round_bottom);
					} else {
						ll.setBackgroundResource(R.drawable.app_list_corner_shape);
					}
				}

				try {
					context.startActivity(intent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		return convertView;
	}

}
