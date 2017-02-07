package com.eiaao.util;


import com.eiaao.ui.GoodbyeView;
import com.eiaao.ui.SearchBox;
import com.eiaao.ui.addNewSoftwareByDrag;
import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class GlobalHotKeysUtil {

	final static int CHANGE_BGJFRAME_VISIBLE = 0;
	final static int ADD_ENW_SOFTWARE = 1;
	final static int EXIT = 2;
	public static void GlobalHotKeysThread()
	{
		new Thread(new Runnable() 
		{
			
		@Override
		public void run() 
		{
			JIntellitype.getInstance().registerHotKey(CHANGE_BGJFRAME_VISIBLE, JIntellitype.MOD_ALT,(int)'Z');
			JIntellitype.getInstance().registerHotKey(ADD_ENW_SOFTWARE, JIntellitype.MOD_ALT,(int)'A');
			JIntellitype.getInstance().registerHotKey(EXIT, JIntellitype.MOD_ALT,(int)'E');
			JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() 
			{
				@Override
				public void onHotKey(int keyMark) {
					switch (keyMark) {
					case CHANGE_BGJFRAME_VISIBLE:SearchBox.changeVisible();  //ÇÐ»»ËÑË÷¿òÏÔÊ¾×´Ì¬,ÉèÖÃÏà¹Ø¼àÌýÆ÷
												 break;
					case ADD_ENW_SOFTWARE :new addNewSoftwareByDrag().show(); break;
					case EXIT : new GoodbyeView().goodbye();break;
					default:break;
					}

					}
			});			
		}
		},"È«¾Ö¼àÌý").start();
	}
}
