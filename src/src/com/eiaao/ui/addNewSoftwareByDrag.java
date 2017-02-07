package com.eiaao.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.eiaao.bean.SoftWareBean;
import com.eiaao.listener.JFrameMoveListener;
import com.eiaao.util.dom4jutil;


public class addNewSoftwareByDrag {

	final private String ADD_NEW_SW_TITLE = "添加快捷启动程序";
	final private String ADD_NEW_SW_TIP_1 = "请将需要添加快捷启动";
	final private String ADD_NEW_SW_TIP_2 ="的文件拖进下面虚线框内";
	final private String EXIT ="鼠标右键点击退出";
	final private Font font = new Font("宋体", Font.BOLD, 18);
	
	public void show() {
		JFrame addNSWJFrame = new JFrame();
		
		JLabel titleLabel = new JLabel(ADD_NEW_SW_TITLE,JLabel.CENTER);
		titleLabel.setFont(font);
		titleLabel.setBounds(20, 5, 320, 25);
		
		JLabel titleLabel1 = new JLabel(ADD_NEW_SW_TIP_1,JLabel.CENTER);
		titleLabel1.setFont(font);
		titleLabel1.setBounds(20, 65, 320, 25);
		
		JLabel titleLabel2 = new JLabel(ADD_NEW_SW_TIP_2,JLabel.CENTER);
		titleLabel2.setFont(font);
		titleLabel2.setBounds(20, 95, 320, 25);
		
		JLabel exit = new JLabel(EXIT,JLabel.RIGHT);
		exit.setFont(font);
		exit.setBounds(20, 200, 320, 25);
		
		JPanel drapJPanel = new JPanel();
		drapJPanel.setBackground(new Color(0,0,0,0));
		
		//处理窗口拖动事件
		JFrameMoveListener jFrameMoveListener = new JFrameMoveListener(addNSWJFrame);
		jFrameMoveListener.addListener();
		
		//添加拖放支持
		drapJPanel.setDropTarget(new DropTarget(drapJPanel,new DropTargetAdapter() {
			
			@Override
			public void drop(DropTargetDropEvent dtde) {
				try
                {
                    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))//如果拖入的文件格式受支持
                    {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//接收拖拽来的数据
                        List<File> list = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                        SoftWareBean softWareBean = new SoftWareBean();
                        for(File file:list)
                        {
                        	softWareBean.setSoftWareName(file.getName());
                        	softWareBean.setSoftWarePath(file.getPath());
                        	dom4jutil.update2Xml(softWareBean);
                        }
                        //弹出一个1s后自动消失的信息提示窗口
                        AutoDisappearMsgJPanel.showMsg(addNSWJFrame,"添加成功(*￣▽￣)y");
                        dtde.dropComplete(true);//指示拖拽操作已完成
                    }
                    else
                    {
                        dtde.rejectDrop();//否则拒绝拖拽来的数据
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
			}
		}));
		
		//鼠标右键点击退出窗口
		addNSWJFrame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					addNSWJFrame.setVisible(false);
				}
			}
		});
		
		ImageIcon GBImage = new ImageIcon("./resouces/add_new_software_bg.png");
		JLabel BGJLabel = new JLabel();
		BGJLabel.setSize(new Dimension(360,250));
		BGJLabel.setIcon(GBImage);
		BGJLabel.setBounds(0, 0, 360, 250);
		
		addNSWJFrame.add(titleLabel);
		addNSWJFrame.add(titleLabel1);
		addNSWJFrame.add(titleLabel2);
		addNSWJFrame.add(exit);
		addNSWJFrame.add(BGJLabel);
		//支持拖放的组件
		addNSWJFrame.add(drapJPanel);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int top = (screen.height - addNSWJFrame.getHeight()) / 2;
		int left = (screen.width - addNSWJFrame.getWidth()) / 2;
		addNSWJFrame.setLocation(left, top);
		addNSWJFrame.setSize(360, 250);
		addNSWJFrame.setUndecorated(true);
		addNSWJFrame.setBackground(new Color(0,0,0,0));
		addNSWJFrame.setVisible(true);
	}
}
