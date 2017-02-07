package com.eiaao.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.eiaao.listener.JFrameMoveListener;
import com.eiaao.service.FileOpenService;
import com.eiaao.util.dom4jutil;


/**
 * 实现快捷键控制程序
 * @author eiaao
 *
 */
public class SearchBox {
	
	private final static  String TEXTFIELDINFO = "Start search...";
	private static  JFrame bgJFrame = new JFrame();
	private static JTextField searchField = new JTextField(TEXTFIELDINFO);
	private static  String InputContent ;
	private static  JPanel resultView = SearchResultJPanel.getResuleView();

    
	//	*********************************************
	//用于处理拖动事件
	private static  JFrameMoveListener jFrameMoveListener = new JFrameMoveListener(bgJFrame);
	private static FileOpenService openService = new FileOpenService();

	//*********************************************
	private static  KeyListener selectResultKeyListener = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
//			System.out.println(Thread.currentThread().getName()+"---选择结果启动getKeyCode:"+e.getKeyChar());
			
			switch (e.getKeyCode()) {
			case 103:
			case 81: openService.openFile(dom4jutil.getSWFromXmlByName(SearchResultJPanel.getSearchResultJLables().get(0).getShowReusLabel().getText()
					.replace("<html>", "").replace("<br/>", "").replace("</html>", "")));
					changeVisible();break;
			case 100:
			case 65: openService.openFile(dom4jutil.getSWFromXmlByName(SearchResultJPanel.getSearchResultJLables().get(1).getShowReusLabel().getText()
					.replace("<html>", "").replace("<br/>", "").replace("</html>", "")));
					changeVisible();break;
			case 97:
			case 90: openService.openFile(dom4jutil.getSWFromXmlByName(SearchResultJPanel.getSearchResultJLables().get(2).getShowReusLabel().getText()
					.replace("<html>", "").replace("<br/>", "").replace("</html>", "")));
					changeVisible();break;
			case 108:
			case 87: openService.openFile(dom4jutil.getSWFromXmlByName(SearchResultJPanel.getSearchResultJLables().get(3).getShowReusLabel().getText()
					.replace("<html>", "").replace("<br/>", "").replace("</html>", "")));
					changeVisible();break;
			case 101:
			case 83: openService.openFile(dom4jutil.getSWFromXmlByName(SearchResultJPanel.getSearchResultJLables().get(4).getShowReusLabel().getText()
					.replace("<html>", "").replace("<br/>", "").replace("</html>", "")));
					changeVisible();break;
			case 98:
			case 88: openService.openFile(dom4jutil.getSWFromXmlByName(SearchResultJPanel.getSearchResultJLables().get(5).getShowReusLabel().getText()
					.replace("<html>", "").replace("<br/>", "").replace("</html>", "")));
					changeVisible();break;
			case 105:
			case 69: openService.openFile(dom4jutil.getSWFromXmlByName(SearchResultJPanel.getSearchResultJLables().get(6).getShowReusLabel().getText()
					.replace("<html>", "").replace("<br/>", "").replace("</html>", "")));
					changeVisible();break;
			case 102:
			case 68: openService.openFile(dom4jutil.getSWFromXmlByName(SearchResultJPanel.getSearchResultJLables().get(7).getShowReusLabel().getText()
					.replace("<html>", "").replace("<br/>", "").replace("</html>", "")));
					changeVisible();break;
			case 99:
			case 67: openService.openFile(dom4jutil.getSWFromXmlByName(SearchResultJPanel.getSearchResultJLables().get(8).getShowReusLabel().getText()
					.replace("<html>", "").replace("<br/>", "").replace("</html>", "")));
					changeVisible();break;
//					ESC键退出选择
			case 27:searchField.setText(searchField.getText().substring(0, searchField.getText().length()-1));
					searchField.addKeyListener(inputKeyListener);
					searchField.removeKeyListener(selectResultKeyListener);
					Thread.currentThread().stop();break;
			default:break;
			}						
		};
	};
	//*********************************************
	
	//搜索框键盘监听事件
	private static  KeyListener inputKeyListener = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
//			System.out.println(e.getKeyCode());
			//-------------------------------------------------------
//			System.out.println(Thread.currentThread().getName()+"---SearchBox-keyPressed");
			//-------------------------------------------------------
			if (searchField.getText().equals(TEXTFIELDINFO)) {
				searchField.setText("");
			}
			//如果检测到输入";"，表明输入结束，启动选择结果线程
			if (e.getKeyCode() == KeyEvent.VK_SEMICOLON ) {
				searchField.addKeyListener(selectResultKeyListener);
				searchField.removeKeyListener(inputKeyListener);
			}
			resultView.setVisible(true);
			
			//单击Enter后，将搜索框中的值保存，并清空搜索框,退出窗口
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				changeVisible();
			}
		};
		@Override
		public void keyReleased(KeyEvent e) {
			//-------------------------------------------------------
//			System.out.println(Thread.currentThread().getName()+"---SearchBox-keyReleased");
			//-------------------------------------------------------
			if (searchField.getText().trim().equals("")) {
				resultView.setVisible(false);
			}
			else{
				InputContent = searchField.getText();
			resultView = SearchResultJPanel.getResuleView();
			}
		};
	};

	//主窗口失去焦点事件
	private static  WindowFocusListener windowFocusListener = new WindowAdapter() {
		@Override
		public void windowLostFocus(WindowEvent e) {
//			System.out.println("LostFocus");
			searchField.setText(TEXTFIELDINFO);
			
			jFrameMoveListener.removeListener();
			
			searchField.removeKeyListener(inputKeyListener);
			searchField.removeKeyListener(selectResultKeyListener);
			resultView.setVisible(false);
			bgJFrame.setVisible(false);
 			Thread.currentThread().stop();
		};
	};
	//	*********************************************
	
	/**
	 * 初始化搜索框窗口
	 */
	static{
		Color alpha = new Color(0, 0, 0, 0);
		bgJFrame.setLayout(null);
//		设置搜索框背景
		ImageIcon bgImage = new ImageIcon("./resouces/search_bg.png");
		JLabel bg = new JLabel();
		bg.setSize(400, 60);
		bg.setIcon(bgImage);
		bgJFrame.add(bg);
		
//		设置搜索框背景
		ImageIcon TextFieldImage = new ImageIcon("./resouces/search_bg_textfield_ret.png");
		JLabel TFbg = new JLabel();
		TFbg.setSize(350, 30);
		TFbg.setIcon(TextFieldImage);
		
		
//		设置搜索框组件
//		输入文字大小、颜色
		searchField.setFont(new Font("宋体", Font.BOLD, 20));
		searchField.setForeground(new Color(255,255,255));
//		无边框
		searchField.setBorder(new EmptyBorder(0, 0, 0, 0));
		searchField.setOpaque(false);
		
		
//		设置绝对位置,添加进JFrame
		searchField.setBounds(50, 15, 300, 30);
		bgJFrame.add(searchField);
		
		TFbg.setBounds(25, 15, 350, 30);
		bgJFrame.add(TFbg);
		
		resultView.setBounds(400, 0, 250, 250);
		bgJFrame.add(resultView);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int top = (screen.height - bgJFrame.getHeight()) / 4;
		int left = (screen.width - bgJFrame.getWidth()) / 4;
		bgJFrame.setLocation(left, top);
		bgJFrame.setUndecorated(true);
		bgJFrame.setBackground(alpha);
		bgJFrame.setSize(650, 250);
		bgJFrame.setVisible(false);
	}


	/**
	 * 切换主窗口的Visible属性，并设置相关监听器
	 */
	public static  void changeVisible() {

		if (bgJFrame.isVisible()) {
			bgJFrame.setVisible(false);
			searchField.setText(TEXTFIELDINFO);
			//移除监听器
			bgJFrame.removeWindowFocusListener(windowFocusListener);
			jFrameMoveListener.removeListener();
			searchField.removeKeyListener(selectResultKeyListener);
			searchField.removeKeyListener(inputKeyListener);
		}
		else {
			bgJFrame.setVisible(true);
			//主窗口失去焦点事件
			bgJFrame.addWindowFocusListener(windowFocusListener);
			//处理主窗口拖动事件
			jFrameMoveListener.addListener();
			//搜索框键盘监听事件
			searchField.addKeyListener(inputKeyListener);
		}
	}
	
	
	/**
	 * 获取搜索框的输入信息
	 * @return
	 */
	public synchronized static  String getInputContent() {
		return InputContent;
	}
}

