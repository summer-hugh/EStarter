package com.eiaao.ui;

import java.awt.Color;
import java.awt.FontMetrics;

import javax.swing.JLabel;

public class SearchResultJLable {

	private JLabel showReusLabel = new JLabel();

	public SearchResultJLable(int startX, int startY) 
	{
		showReusLabel.setOpaque(true);
		showReusLabel.setFocusable(true);
		showReusLabel.setForeground(Color.WHITE);
		showReusLabel.setBackground(new Color(0, 0, 0));
		showReusLabel.setBounds(startX, startY, 60, 60);
	}
	
	public  JLabel getShowReusLabel() {
		return showReusLabel;
	}
	
	public void LableTextContentHandle(String content) {
		if (content.equals("")) {
			showReusLabel.setText("");
			return;
		}
		String longString = content;
	    StringBuilder builder = new StringBuilder("<html>");
	    char[] chars = longString.toCharArray();
	    FontMetrics fontMetrics = showReusLabel.getFontMetrics(showReusLabel.getFont());
	    for (int beginIndex = 0, limit = 1;; limit++) {
//	        System.out.println(longString+" "+beginIndex + " " + limit + " " + (beginIndex + limit)+"--"+fontMetrics.charsWidth(chars, beginIndex, limit)+"--"+chars.length);
	        if (fontMetrics.charsWidth(chars, beginIndex, limit) < showReusLabel.getWidth()) 
	        {
	            if (beginIndex + limit < chars.length) 
	            {
	                continue;
	            }
	            builder.append(chars, beginIndex, limit);
	            break;
	        }
	        //追加到builder后更新指针和偏移量，偏移量该为1,由于for循环中自加1，所以此处为0，否则某些情况下会报异常
	        builder.append(chars, beginIndex, limit - 1).append("<br/>");
	        beginIndex += limit - 1;
	        limit = 0;
	    }
	    builder.append("</html>");
	    //更新结果JLable显示的内容
	    new Thread(new Runnable() {
			
			@Override
			public void run() {
				 showReusLabel.setText(builder.toString());
			}
		}).start();
	}
	
	
}
