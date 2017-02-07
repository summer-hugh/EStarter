package com.eiaao.service;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.eiaao.bean.SoftWareBean;

/**
 * 启动第三方软件服务
 * @author eiaao
 *
 */
public class FileOpenService {

	/**
	 * 启动exe文件
	 * @param software
	 */
	public void openFile(SoftWareBean software) {
		if (software.getSoftWareName().contains(".exe")) {
			openExeFile(software);
			return;
		}
		else 
		{
			File file = null;
			file = new File(software.getSoftWarePath());
			//使用系统默认程序打开文件
			try {Desktop.getDesktop().open(file);} 
			catch (IOException e) {	e.printStackTrace();}
			finally{file = null;}
		}
	}
	
	private void openExeFile(SoftWareBean software){
		try {Runtime.getRuntime().exec(software.getSoftWarePath());} 
		catch (IOException e) {e.printStackTrace();}
	}

}
