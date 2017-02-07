package com.eiaao.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.eiaao.bean.SoftWareBean;
import com.eiaao.listener.SelectResultListener;
import com.eiaao.util.dom4jutil;

/**
 * 显示搜索结果
 * @author eiaao
 *
 */
public class SearchResultJPanel  {

	private static int[] keys = {KeyEvent.VK_1,KeyEvent.VK_2,KeyEvent.VK_3,KeyEvent.VK_4,KeyEvent.VK_5,KeyEvent.VK_6,KeyEvent.VK_7,KeyEvent.VK_8,KeyEvent.VK_9,};
 	private static Map<String, SoftWareBean> resultMap;
	private static List<String> resultList = new ArrayList<>();

	private static List<SearchResultJLable> searchResultJLables = new ArrayList<>();
	public static List<SearchResultJLable> getSearchResultJLables() {
		return searchResultJLables;
	}

	private static JPanel searchResultView = new JPanel();
	private static JLabel resultbg = new JLabel();
	private final static Color ALPHA = new Color(0, 0, 0, 0);
	static {
		searchResultView.setLayout(null);
		searchResultView.setBackground(ALPHA);
		searchResultView.setSize(new Dimension(220,220));
		searchResultView.setVisible(false);
//		设置搜索框背景
		ImageIcon bgImage = new ImageIcon("./resouces/search_result_bg.png");
		resultbg.setSize(220, 220);
		resultbg.setIcon(bgImage);
		resultbg.setBounds(0, 0, 220, 220);
		searchResultView.add(resultbg);
		
//		new出9个搜索结果存放的Jlable，并初始化其绝对位置，放入List集合中管理
		for (int i = 0 , startx = 14, starty = 14; i < 9; i++) {
			if (i%3 == 0 && i != 0) {
				startx += 66;
			}
//			System.out.println(startx+"---"+ (starty+((i)%3)*66));
			SearchResultJLable searchResultJLable = new SearchResultJLable(startx, (starty+((i)%3)*66));
//			绑定键盘监听
			new SelectResultListener().addSelectResultListener(searchResultJLable, keys[i]);
			searchResultJLables.add(searchResultJLable);
		}
//		遍历List，将JLable添加进JPanel组件
		for (SearchResultJLable searchResultJLable : searchResultJLables) {
			searchResultView.add(searchResultJLable.getShowReusLabel());
		}
	}
	
	public static JPanel getResuleView() {
		ListResult2JPanel();
		return searchResultView;
	}
	
	private static void ListResult2JPanel() {
		if (SearchBox.getInputContent() == null) {
			return;
		}
		else 
		{
			//将结果集合Map转化为List
			resultMap = dom4jutil.FuzzyQuery(SearchBox.getInputContent());
			if (resultMap.isEmpty()) {
				return;
			}
			else {
				for (String key : resultMap.keySet()) {
					resultList.add(key);
				}
				resultMap.clear();
				System.out.println(resultList);
			}
			//遍历结果List，如果有值，则赋值给JLable。如果无值，则赋""
			for (int i = 0; i < 9; i++) {
				if (i < resultList.size()) {
					searchResultJLables.get(i).LableTextContentHandle(resultList.get(i));
				}
				else {
					searchResultJLables.get(i).LableTextContentHandle("");
				}
			}
			//清空结果集
			resultList.clear();
		}
	}
	
}
