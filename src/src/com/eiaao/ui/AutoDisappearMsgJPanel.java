package com.eiaao.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * 弹出一个1s后自动消失的信息提示窗口
 * @author eiaao
 *
 */
public class AutoDisappearMsgJPanel {

	/**
	 * 弹出一个1s后自动消失的信息提示窗口
	 * @param parent 父类窗体组件
	 * @param Msg	 需要提示的信息
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
		    	text.setFont(new Font("宋体", Font.BOLD,	16));
		    	text.setBackground(Color.BLACK);
		    	text.setForeground(Color.WHITE);
		    	text.setHorizontalAlignment(JLabel.CENTER);
		    	
		        text.setText(Msg);
		        msgJFrame.add(text, BorderLayout.CENTER);
		        
		        msgJFrame.setSize(size);
		        msgJFrame.setUndecorated(true);
		        msgJFrame.setLocationRelativeTo(parent);
		        msgJFrame.setVisible(true);
		        
		        //延时两秒
		        try {Thread.sleep(1000);} 
		        catch (InterruptedException e1) {e1.printStackTrace();}
		        //关闭窗口
		        finally{ msgJFrame.dispose();}
			}
		}).start();
    }
}
