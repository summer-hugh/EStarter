package com.eiaao.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.eiaao.listener.JFrameMoveListener;

/**
 * 配置使用说明页面
 * @author eiaao
 *
 */
public class DirectionView {

	private JFrame directionJFrame = new JFrame();
	private Dimension size = new Dimension(300, 30);
	private Font font = new Font("宋体", Font.BOLD, 20);
	final String USE_DIRECTION = "使用说明";
	final String ADD_NEW_SW = "Alt+A : 添加新的快捷启动项";
	final String EXEC = "Alt+Z : 呼出搜索框窗口";
	final String EXIT = "Alt+E : 退出软件";
	
	JLabel useDirectionLabel = new JLabel(USE_DIRECTION,JLabel.CENTER);
	JLabel addNewSWLabel = new JLabel(ADD_NEW_SW,JLabel.LEFT);
	JLabel execLabel = new JLabel(EXEC,JLabel.LEFT);
	JLabel exitLabel = new JLabel(EXIT,JLabel.LEFT);
	
	/**
	 * 显示一个使用说明页面
	 */
	public void directionView(){
		useDirectionLabel.setSize(size);
		useDirectionLabel.setFont(font);
		useDirectionLabel.setBounds(30, 0, 300, 30);
		
		addNewSWLabel.setSize(size);
		addNewSWLabel.setFont(font);
		addNewSWLabel.setBounds(30, 33, 300, 30);
		
		execLabel.setSize(size);
		execLabel.setFont(font);
		execLabel.setBounds(30, 66, 300, 30);
		
		exitLabel.setSize(size);
		exitLabel.setFont(font);
		exitLabel.setBounds(30, 99, 300, 30);
		
		ImageIcon GBImage = new ImageIcon("./resouces/directionView_bg.png");
		JLabel GBbg = new JLabel();
		GBbg.setSize(360,150);
		GBbg.setIcon(GBImage);
		GBbg.setBounds(0, 0, 360, 150);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int top = (screen.height - directionJFrame.getHeight()) / 2;
		int left = (screen.width - directionJFrame.getWidth()) / 2;
		directionJFrame.setLocation(left, top);
		
		directionJFrame.add(useDirectionLabel);
		directionJFrame.add(addNewSWLabel);
		directionJFrame.add(execLabel);
		directionJFrame.add(exitLabel);
		directionJFrame.add(GBbg);
		directionJFrame.setUndecorated(true);
		directionJFrame.setSize(new Dimension(360,150));
		directionJFrame.setBackground(new Color(0,0,0,0));
		directionJFrame.setVisible(true);
		
		//用于处理拖动事件
		JFrameMoveListener jFrameMoveListener = new JFrameMoveListener(directionJFrame);
		jFrameMoveListener.addListener();
		
		//处理失去焦点事件
		directionJFrame.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				directionJFrame.dispose();
			}
		});
	}
	
}
