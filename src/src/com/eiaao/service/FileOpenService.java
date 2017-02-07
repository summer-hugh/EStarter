package com.eiaao.service;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.eiaao.bean.SoftWareBean;

/**
 * �����������������
 * @author eiaao
 *
 */
public class FileOpenService {

	/**
	 * ����exe�ļ�
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
			//ʹ��ϵͳĬ�ϳ�����ļ�
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
