package com.eiaao.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.eiaao.bean.SoftWareBean;

public class dom4jutil {

	private static String xmlPath = "./SoftInfo.xml";
	private static Document dom;
	private static List<Element> rootlist = null;
	private static Map<String, SoftWareBean> rootMap =  new HashMap<>();
	private static Map<String, SoftWareBean> resultmap =  new HashMap<>();
	
	/**
	 * ���ʼ����
	 * �ж������ļ��Ƿ����
	 */
	static{
		try 
		{
//			��ȡ������
			SAXReader reader = new SAXReader();
//			������ȡ����XML��dom
			dom = reader.read(xmlPath);
			rootlist = dom.getRootElement().elements();
			for (Element element : rootlist) {
				rootMap.put(element.attributeValue("name"), 
							new SoftWareBean(element.attributeValue("name"),element.attributeValue("path")));
			}
		} 
		catch (DocumentException e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * ˽�л�������
	 */
	private dom4jutil() {
	}
	
	/**
	 * ͨ��������Xml�л�ȡSoftWareBean
	 * @param softwareName
	 * @return
	 */
	public static SoftWareBean getSWFromXmlByName(String softwareName) {
		return rootMap.get(softwareName);
	}
	
	/**
	 * ��Xml�ļ��и���װ��software�����Ϣ��Map����
	 */
	public static void updateFromXml() {
		try 
		{
//			��ȡ������
			SAXReader reader = new SAXReader();
//			������ȡ����XML��dom
			dom = reader.read(xmlPath);
			rootlist = dom.getRootElement().elements();
			for (Element element : rootlist) {
				rootMap.put(element.attributeValue("name"), 
							new SoftWareBean(element.attributeValue("name"),element.attributeValue("path")));
			}
		} 
		catch (DocumentException e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * ͨ���ؼ��ʶ�Map���Ͻ���ģ������
	 * @param keyword 
	 * @return ƥ���softwareName��List����
	 */
	public static Map<String, SoftWareBean> FuzzyQuery(String keyword) {
		resultmap.clear();
		for (String key : rootMap.keySet()) {
			if (key.trim().toLowerCase().startsWith(keyword.trim().toLowerCase())) {
//				װ��ƥ���softwareName ��software����
				resultmap.put(key, rootMap.get(key));
			}
		}
		return resultmap;
	}
	
	/**
	 * �������ݵ�Xml�ļ�
	 * @param software
	 */
	public static void update2Xml(SoftWareBean software) {
		//û����Ҫ���µĶ���
		if (software == null) {
			return;
		}
		updateFromXml();
		rootMap.put(software.getSoftWareName(), software);
		Element newSoftware = DocumentHelper.createElement("SoftWare");
		newSoftware.addAttribute("name", software.getSoftWareName());
		newSoftware.addAttribute("path", software.getSoftWarePath());
		dom.getRootElement().add(newSoftware);
		write2Xml();
		updateFromXml();
	}
	
	/**
	 * ����xml�����ļ�
	 */
	public static void write2Xml() {
		XMLWriter writer = null;
		try 
		{
			writer = new XMLWriter(new FileOutputStream(xmlPath),OutputFormat.createPrettyPrint());
			writer.write(dom);
		} 
		catch (IOException e) {e.printStackTrace();}
		finally
		{
			try {writer.close();} 
			catch (IOException e) {e.printStackTrace();}
		}
	}
	
}