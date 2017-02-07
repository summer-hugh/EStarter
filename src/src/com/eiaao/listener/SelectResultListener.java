package com.eiaao.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



import com.eiaao.service.FileOpenService;
import com.eiaao.ui.SearchResultJLable;
import com.eiaao.util.dom4jutil;

public class SelectResultListener {
	public void addSelectResultListener(SearchResultJLable searchResultJLable, int Key) {
		FileOpenService openService = new FileOpenService();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				searchResultJLable.getShowReusLabel().addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == Key) {
							openService.openFile(dom4jutil.getSWFromXmlByName(searchResultJLable.getShowReusLabel().getText()));
						}
					}
				});			
			}
		},"Ìí¼Ó³ÌÐò");
	}
}
