package com.eiaao.test;

import java.awt.FlowLayout;
import java.io.File;

import javax.print.attribute.standard.SheetCollate;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;

public class Test {

	public static void main(String[] args) {
		String filePath = "C:\\Users\\iaao\\Desktop\\SoftInfo.xml";
		File f = new File(filePath);
		JFrame frm = new JFrame();
		frm.setSize(300, 200);
		frm.setLocationRelativeTo(null);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
		frm.setLayout(new FlowLayout(10,10,FlowLayout.LEADING));
		
		JLabel sl = new JLabel("Сͼ��");
		frm.add(sl);
		JLabel bl = new JLabel("��ͼ��");
		frm.add(bl);
		
		sl.setIcon(getSmallIcon(f));
//		bl.setIcon(getBigIcon(f));
	}


/**
* ��ȡСͼ��
* @param f
* @return
*/
private static Icon getSmallIcon(File f) {
	if (f != null && f.exists()) {
		FileSystemView fsv = FileSystemView.getFileSystemView();
		return fsv.getSystemIcon(f);
	}
	return null;
}

/**
* ��ȡ��ͼ��
* @param f
* @return
*/
//private static Icon getBigIcon(File f) {
//	if (f!=null && f.exists()) {
//		try {
//			sun.awt.shell.ShellFolder sf = sun.awt.shell.ShellFolder.getShellFolder(f);
//			return new ImageIcon(sf.getIcon(true));
//			} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			}
//		}
//	return null;
//	}
}

