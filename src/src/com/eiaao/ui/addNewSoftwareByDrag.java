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

	final private String ADD_NEW_SW_TITLE = "��ӿ����������";
	final private String ADD_NEW_SW_TIP_1 = "�뽫��Ҫ��ӿ������";
	final private String ADD_NEW_SW_TIP_2 ="���ļ��Ͻ��������߿���";
	final private String EXIT ="����Ҽ�����˳�";
	final private Font font = new Font("����", Font.BOLD, 18);
	
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
		
		//�������϶��¼�
		JFrameMoveListener jFrameMoveListener = new JFrameMoveListener(addNSWJFrame);
		jFrameMoveListener.addListener();
		
		//����Ϸ�֧��
		drapJPanel.setDropTarget(new DropTarget(drapJPanel,new DropTargetAdapter() {
			
			@Override
			public void drop(DropTargetDropEvent dtde) {
				try
                {
                    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))//���������ļ���ʽ��֧��
                    {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//������ק��������
                        List<File> list = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                        SoftWareBean softWareBean = new SoftWareBean();
                        for(File file:list)
                        {
                        	softWareBean.setSoftWareName(file.getName());
                        	softWareBean.setSoftWarePath(file.getPath());
                        	dom4jutil.update2Xml(softWareBean);
                        }
                        //����һ��1s���Զ���ʧ����Ϣ��ʾ����
                        AutoDisappearMsgJPanel.showMsg(addNSWJFrame,"��ӳɹ�(*������)y");
                        dtde.dropComplete(true);//ָʾ��ק���������
                    }
                    else
                    {
                        dtde.rejectDrop();//����ܾ���ק��������
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
			}
		}));
		
		//����Ҽ�����˳�����
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
		//֧���Ϸŵ����
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
