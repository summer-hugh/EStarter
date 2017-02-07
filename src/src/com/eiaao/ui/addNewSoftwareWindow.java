package com.eiaao.ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.eiaao.bean.SoftWareBean;

/**
 * ����µ�������Ŀ
 * @author eiaao
 *
 */
public class addNewSoftwareWindow  
{
	private static SoftWareBean software = new SoftWareBean();
	
	/**
	 * �����ļ�ѡ����������µ�������Ŀ
	 * @return SoftWareBean����
	 */
	public static SoftWareBean addNewSoftware() 
	{
		JFileChooser JFC =new JFileChooser();
		JFC.setFileFilter(new FileNameExtensionFilter(".exe",".exe"));
//		JFC.setFileFilter(new FileNameExtensionFilter(".jpg",".jpg"));
		JFC.showOpenDialog(new JFrame());
		File file = JFC.getSelectedFile();
		//û��ѡ�񣬷���null
		if (file == null) {
			return null;
		}
		software.setSoftWareName(file.getName());
		software.setSoftWarePath(file.toString());
//		System.out.println(software);
		return software;
	}
}
