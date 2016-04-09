package com.alssc.komicaparser.parser.rule.bulletin;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alssc.komicaparser.util.datamodel.BulletinDataModel;
import com.alssc.komicaparser.util.net.GetSite;


public class BulletinRule1 extends BulletinRule {

	public BulletinDataModel getBulletin(String source) {
		try {
			return parse(source);
		} catch (Exception e) {
			System.out.println("parse fail.");
			return null;
		}
	}
	
	private BulletinDataModel parse(String source) {
		GetSite getSite = new GetSite();
		BulletinDataModel data = new BulletinDataModel();
		Document doc = Jsoup.parse(getSite.downloadSource(source));
		Element eBody = doc.select("body").first();
		
		// get content
		Elements eContent = eBody.select("form").get(1).select("div.threadpost"); 
		Integer cellKey = 1; // start from 1.
		String cellTitle = "";
		String cellAvatarUrl = "";
		String cellMeta = "";
		String cellStickyUrl = "";
		String cellIntroduction = "";
		
		for (Element eCell : eContent) {			
			cellTitle = eCell.select("label").first().select("span.title").text();
			cellAvatarUrl = eCell.select("img").first().attr("src"); 
			cellMeta = eCell.select("label").first().text();
			
			for (Element tc : eCell.select("a")) {
				if (tc.text().equals("返信")) {
					cellStickyUrl = tc.attr("href");
					break;
				}
			}
			
			cellIntroduction = eCell.select("div.quote").first().html();
			cellIntroduction = cellIntroduction.replaceAll("\n", "");
			cellIntroduction = cellIntroduction.replaceAll("<br>", "\n");
			//cellIntroduction = cellIntroduction.replace("<br>", "\n");
			
			// save data.
			data.putData(String.valueOf(cellKey), cellTitle, cellAvatarUrl, cellMeta, cellStickyUrl, cellIntroduction);
			//clear temp parameter.
			cellKey++;
			cellTitle = "";
			cellAvatarUrl = "";
			cellMeta = "";
			cellStickyUrl = "";
			cellIntroduction = "";
		}
		
		// get page link.
		Element pageContent = eBody.getElementById("page_switch").select("td").get(1);
		Elements pageIndex = pageContent.children();
		List<String> pageUrl = new LinkedList<String>();
		
		// check page start index is 0 or 1.
		for (int i = 0; i < pageIndex.size(); i++) {
			if (pageIndex.get(i).tagName().equals("b")) {
				pageUrl.add("#");
				data.putCurrPage(i);
			} else {
				pageUrl.add(source + "/" + pageIndex.get(i).attr("href"));
			}
		}
				
		data.putPage(pageUrl);

		return data;		
	}
}
