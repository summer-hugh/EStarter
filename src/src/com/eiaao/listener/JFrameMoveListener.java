package com.eiaao.listener;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class JFrameMoveListener {
	private Container container;
	
	//���ڴ����϶��¼�����ʾ��갴��ʱ�����꣬�����JFrame
	int xOld = 0;
	int yOld = 0;
	
	/**
	 * ��������������Ҫ����϶�Ч��������
	 * @param container
	 */
	public JFrameMoveListener(Container container ) {
		this.container = container;
	}
	
	//�����������϶��¼�
	private MouseListener mouseListener = new MouseAdapter() {
		@Override  
		public void mousePressed(MouseEvent e) {
			xOld = e.getX();  
            yOld = e.getY();
		};
	};
	
	//ִ���������϶��¼�
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
