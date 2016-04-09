package com.alssc.komicaparser.util;

import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class Util {

	/**
	 * return current system time.
	 * @return
	 */
	public static String currTime() {
		return "";
	}
	
	public static String validateStr(String str) {
		if (str == null || str.isEmpty()) {
			return "";
		} else {
			return str;
		}
	}
	
	public static boolean validateObj(Object obj) {
		return obj == null ? false : true;
	}
	
	public static String html2text(String str) {
		String result = "";
		str = str.replaceAll("\n", "");
		List<String> si = Arrays.asList(str.split("<br>")); // consider <br/>
		for (String s : si) {
			s = s.replaceAll("<[^>]*>", "");
			s = replaceEscapeChar(s);
			s = s.trim();
			result += s + "\n";
		}		
		return result;
	}
	
	public static String replaceEscapeChar(String str) {
		if (str.contains("&lt;")) {str = str.replaceAll("&lt;", "<");}
		if (str.contains("&gt;")) {str = str.replaceAll("&gt;", ">");}
		if (str.contains("&amp;")) {str = str.replaceAll("&amp;", "&");}
		if (str.contains("&quot;")) {str = str.replaceAll("&quot;", "\"");}
		//if (str.contains("")) {str = str.replaceAll("", "");}
		return str;
	}
}
