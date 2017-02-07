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
		if (SystemTray.isSupported()) { // �ж��Ƿ�֧��ϵͳ����  
		    ImageIcon icon = new ImageIcon("./resouces/tray_icon.png"); // ʵ����ͼ�����  
		    Image image = icon.getImage(); // ���Image����  
		    TrayIcon trayIcon = new TrayIcon(image); // ��������ͼ��  
		    trayIcon.addMouseListener(new MouseAdapter() { // Ϊ����������������  
		        public void mouseClicked(MouseEvent e) { // ����¼�  
		            if (e.getClickCount() == 2) { // �ж��Ƿ�˫�������  
		                showFrame(); // ���÷�����ʾ����  
		            }  
		        }  
		    });  
		    trayIcon.setToolTip("EasyStart"); // ��ӹ�����ʾ�ı�  
		    PopupMenu popupMenu = new PopupMenu(); // ���������˵�  
		    //**********************************************
		    //�����˵���  -- �˳�
		    MenuItem exit = new MenuItem("�˳�"); 
		    //��Ӧ����  
		    exit.addActionListener(new ActionListener() {  
		  
		        public void actionPerformed(ActionEvent e) {  
		              System.exit(0);
		        }  
		    });  
		    
		    //**********************************************
		    //�����˵���  -- ...
		    MenuItem direction = new MenuItem("ʹ��˵��"); 
		    //��Ӧ����  
		    direction.addActionListener(new ActionListener() {  
		  
		        public void actionPerformed(ActionEvent e) {  
		              new DirectionView().directionView();
		        }  
		    }); 
		    
		    // Ϊ�����˵���Ӳ˵���  
		    popupMenu.add(direction);
		    popupMenu.add(exit); 
		    // Ϊ����ͼ��ӵ����˵�  
		    trayIcon.setPopupMenu(popupMenu); 
		    // ���ϵͳ���̶���  
		    SystemTray systemTray = SystemTray.getSystemTray(); 
		    // Ϊϵͳ���̼�����ͼ��  
		    try {  systemTray.add(trayIcon);} 
		    catch (Exception e) {e.printStackTrace();}
		}  
	}
	
	public void showFrame() {  
	    this.setVisible(true); // ��ʾ����  
	    this.setState(Frame.NORMAL);
	}  
}
