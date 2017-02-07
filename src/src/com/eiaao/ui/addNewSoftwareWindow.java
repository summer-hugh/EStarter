package com.eiaao.ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.eiaao.bean.SoftWareBean;

/**
 * 添加新的启动项目
 * @author eiaao
 *
 */
public class addNewSoftwareWindow  
{
	private static SoftWareBean software = new SoftWareBean();
	
	/**
	 * 启动文件选择器，添加新的启动项目
	 * @return SoftWareBean对象
	 */
	public static SoftWareBean addNewSoftware() 
	{
		JFileChooser JFC =new JFileChooser();
		JFC.setFileFilter(new FileNameExtensionFilter(".exe",".exe"));
//		JFC.setFileFilter(new FileNameExtensionFilter(".jpg",".jpg"));
		JFC.showOpenDialog(new JFrame());
		File file = JFC.getSelectedFile();
		//没有选择，返回null
		if (file == null) {
			return null;
		}
		software.setSoftWareName(file.getName());
		software.setSoftWarePath(file.toString());
//		System.out.println(software);
		return software;
	}
}
