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
 * �����˳�ʱ�Ľ���ҳ��
 * @author eiaao
 *
 */
public class GoodbyeView {

	/**
	 * ��ʾ�˳�ʱ�Ľ���ҳ��
	 */
	public void goodbye(){
		JFrame goodbyeFrame = new JFrame();
		ImageIcon GBImage = new ImageIcon("./resouces/goodbye_bg.png");
		JLabel GBbg = new JLabel();
		GBbg.setBackground(new Color(0,0,0,0));
		GBbg.setSize(350, 200);
		GBbg.setIcon(GBImage);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int top = (screen.height - goodbyeFrame.getHeight()) / 2;
		int left = (screen.width - goodbyeFrame.getWidth()) / 2;
		goodbyeFrame.setLocation(left, top);
		goodbyeFrame.add(GBbg);
		goodbyeFrame.setUndecorated(true);
		goodbyeFrame.setSize(new Dimension(350,200)); 
		goodbyeFrame.setBackground(new Color(0,0,0,0));
		goodbyeFrame.setVisible(true);
		//��ʱ1500ms�˳�����
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.exit(0);
			}
		}, 1500);
	}
}
