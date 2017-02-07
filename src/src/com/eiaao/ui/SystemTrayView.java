package com.eiaao.ui;

import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SystemTrayView extends JFrame {

	private static final long serialVersionUID = 1L;

	public void showSystemTrayView() {
		if (SystemTray.isSupported()) { // 判断是否支持系统托盘  
		    ImageIcon icon = new ImageIcon("./resouces/tray_icon.png"); // 实例化图像对象  
		    Image image = icon.getImage(); // 获得Image对象  
		    TrayIcon trayIcon = new TrayIcon(image); // 创建托盘图标  
		    trayIcon.addMouseListener(new MouseAdapter() { // 为托盘添加鼠标适配器  
		        public void mouseClicked(MouseEvent e) { // 鼠标事件  
		            if (e.getClickCount() == 2) { // 判断是否双击了鼠标  
		                showFrame(); // 调用方法显示窗体  
		            }  
		        }  
		    });  
		    trayIcon.setToolTip("EasyStart"); // 添加工具提示文本  
		    PopupMenu popupMenu = new PopupMenu(); // 创建弹出菜单  
		    //**********************************************
		    //创建菜单项  -- 退出
		    MenuItem exit = new MenuItem("退出"); 
		    //响应方法  
		    exit.addActionListener(new ActionListener() {  
		  
		        public void actionPerformed(ActionEvent e) {  
		              System.exit(0);
		        }  
		    });  
		    
		    //**********************************************
		    //创建菜单项  -- ...
		    MenuItem direction = new MenuItem("使用说明"); 
		    //响应方法  
		    direction.addActionListener(new ActionListener() {  
		  
		        public void actionPerformed(ActionEvent e) {  
		              new DirectionView().directionView();
		        }  
		    }); 
		    
		    // 为弹出菜单添加菜单项  
		    popupMenu.add(direction);
		    popupMenu.add(exit); 
		    // 为托盘图标加弹出菜弹  
		    trayIcon.setPopupMenu(popupMenu); 
		    // 获得系统托盘对象  
		    SystemTray systemTray = SystemTray.getSystemTray(); 
		    // 为系统托盘加托盘图标  
		    try {  systemTray.add(trayIcon);} 
		    catch (Exception e) {e.printStackTrace();}
		}  
	}
	
	public void showFrame() {  
	    this.setVisible(true); // 显示窗体  
	    this.setState(Frame.NORMAL);
	}  
}
