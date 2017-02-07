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
	 * 类初始化块
	 * 判断配置文件是否存在
	 */
	static{
		try 
		{
//			获取解析器
			SAXReader reader = new SAXReader();
//			解析获取代表XML的dom
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
	 * 私有化构造器
	 */
	private dom4jutil() {
	}
	
	/**
	 * 通过名字在Xml中获取SoftWareBean
	 * @param softwareName
	 * @return
	 */
	public static SoftWareBean getSWFromXmlByName(String softwareName) {
		return rootMap.get(softwareName);
	}
	
	/**
	 * 从Xml文件中更新装载software相关信息的Map集合
	 */
	public static void updateFromXml() {
		try 
		{
//			获取解析器
			SAXReader reader = new SAXReader();
//			解析获取代表XML的dom
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
	 * 通过关键词对Map集合进行模糊搜索
	 * @param keyword 
	 * @return 匹配的softwareName的List集合
	 */
	public static Map<String, SoftWareBean> FuzzyQuery(String keyword) {
		resultmap.clear();
		for (String key : rootMap.keySet()) {
			if (key.trim().toLowerCase().startsWith(keyword.trim().toLowerCase())) {
//				装入匹配的softwareName 和software对象
				resultmap.put(key, rootMap.get(key));
			}
		}
		return resultmap;
	}
	
	/**
	 * 更新内容到Xml文件
	 * @param software
	 */
	public static void update2Xml(SoftWareBean software) {
		//没有需要更新的对象
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
	 * 更新xml配置文件
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