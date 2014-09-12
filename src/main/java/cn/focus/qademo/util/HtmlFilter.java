package cn.focus.qademo.util;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 


public class HtmlFilter {
	
	/**
	 * filter html tags
	 * @param inputStr
	 * @return
	 */
	public static String htmlFilter(String inputStr) {
		
		String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签   
		String pageHtml = "\\[[0-9]*\\]";
		StringBuffer sb = new StringBuffer("");
		Pattern p = Pattern.compile(regxpForHtml);
		Matcher m = p.matcher(inputStr);
		while(m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		String str = sb.toString();
		sb = new StringBuffer("");
		
		p = Pattern.compile(pageHtml);
		m = p.matcher(str);
		while(m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		str = sb.toString();
		return str;
		
	}

}

