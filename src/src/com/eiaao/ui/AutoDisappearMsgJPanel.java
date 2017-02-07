package com.eiaao.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * ����һ��1s���Զ���ʧ����Ϣ��ʾ����
 * @author eiaao
 *
 */
public class AutoDisappearMsgJPanel {

	/**
	 * ����һ��1s���Զ���ʧ����Ϣ��ʾ����
	 * @param parent ���ര�����
	 * @param Msg	 ��Ҫ��ʾ����Ϣ
	 */
	public static void showMsg(JFrame parent ,String Msg) {
		JFrame msgJFrame = new JFrame();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				JLabel text = new JLabel();
		    	Dimension size = new Dimension(200,30);
		    	text.setOpaque(true);
		    	text.setSize(size);
		    	text.setFont(new Font("����", Font.BOLD,	16));
		    	text.setBackground(Color.BLACK);
		    	text.setForeground(Color.WHITE);
		    	text.setHorizontalAlignment(JLabel.CENTER);
		    	
		        text.setText(Msg);
		        msgJFrame.add(text, BorderLayout.CENTER);
		        
		        msgJFrame.setSize(size);
		        msgJFrame.setUndecorated(true);
		        msgJFrame.setLocationRelativeTo(parent);
		        msgJFrame.setVisible(true);
		        
		        //��ʱ����
		        try {Thread.sleep(1000);} 
		        catch (InterruptedException e1) {e1.printStackTrace();}
		        //�رմ���
		        finally{ msgJFrame.dispose();}
			}
		}).start();
    }
}
