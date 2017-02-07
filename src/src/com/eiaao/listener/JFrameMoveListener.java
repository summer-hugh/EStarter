package com.eiaao.listener;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class JFrameMoveListener {
	private Container container;
	
	//用于处理拖动事件，表示鼠标按下时的坐标，相对于JFrame
	int xOld = 0;
	int yOld = 0;
	
	/**
	 * 构造器，传入需要添加拖动效果的容器
	 * @param container
	 */
	public JFrameMoveListener(Container container ) {
		this.container = container;
	}
	
	//处理主窗口拖动事件
	private MouseListener mouseListener = new MouseAdapter() {
		@Override  
		public void mousePressed(MouseEvent e) {
			xOld = e.getX();  
            yOld = e.getY();
		};
	};
	
	//执行主窗口拖动事件
	private MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
		@Override  
		public void mouseDragged(MouseEvent e) {
			int xx = e.getXOnScreen() - xOld;  
			int yy = e.getYOnScreen() - yOld;  
			container.setLocation(xx, yy);
		};
	};
	
	public void removeListener() {
		container.removeMouseListener(mouseListener);
		container.removeMouseMotionListener(mouseMotionListener);
	}
	
	public void addListener() {
		container.addMouseListener(mouseListener);
		container.addMouseMotionListener(mouseMotionListener);
	}
}
