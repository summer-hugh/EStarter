package com.eiaao.main;

import com.eiaao.ui.StartView;
import com.eiaao.ui.SystemTrayView;
import com.eiaao.util.GlobalHotKeysUtil;
/**
 * Built in 2015/01/04 
 * @author eiaao
 *
 */
public class Main {

	public static void main(String[] args) {
		//��ӭҳ��
		new StartView().startView();
		// ����ȫ�ֿ�ݼ������߳�
		GlobalHotKeysUtil.GlobalHotKeysThread();
		//������̨�����߳�
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				new SystemTrayView().showSystemTrayView();
			}
		}).start();
	}
}