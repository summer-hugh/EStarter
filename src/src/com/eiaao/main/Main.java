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
		//欢迎页面
		new StartView().startView();
		// 启动全局快捷键监听线程
		GlobalHotKeysUtil.GlobalHotKeysThread();
		//启动后台托盘线程
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				new SystemTrayView().showSystemTrayView();
			}
		}).start();
	}
}