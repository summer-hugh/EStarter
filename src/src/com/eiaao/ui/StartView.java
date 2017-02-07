package com.eiaao.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 配置使用说明页面
 * @author eiaao
 *
 */
public class StartView {

	/**
	 * 启动时显示一个欢迎页面
	 */
	public void startView(){
		JFrame startFrame = new JFrame();
		ImageIcon GBImage = new ImageIcon("./resouces/start_bg.png");
		JLabel GBbg = new JLabel();
		GBbg.setBackground(new Color(0,0,0,0));
		GBbg.setSize(350, 200);
		GBbg.setIcon(GBImage);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int top = (screen.height - startFrame.getHeight()) / 2;
		int left = (screen.width - startFrame.getWidth()) / 2;
		startFrame.setLocation(left, top);
		startFrame.add(GBbg);
		startFrame.setUndecorated(true);
		startFrame.setSize(new Dimension(350,200)); 
		startFrame.setBackground(new Color(0,0,0,0));
		startFrame.setVisible(true);
		//延时150ms退出程序
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				startFrame.dispose();
			}
		}, 1500);
	}
	
}
