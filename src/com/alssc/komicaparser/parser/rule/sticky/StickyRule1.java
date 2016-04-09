package com.alssc.komicaparser.parser.rule.sticky;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alssc.komicaparser.util.Util;
import com.alssc.komicaparser.util.datamodel.StickyDataModel;
import com.alssc.komicaparser.util.net.GetSite;

public class StickyRule1 extends StickyRule{

	
	public StickyDataModel getSticky(String source) {
		try {
			return parse(source);
		} catch (Exception e) {
			System.out.println("parse fail.");
			return null;
		}
	}
	
	private StickyDataModel parse(String source) {
		GetSite getSite = new GetSite();
		StickyDataModel data = new StickyDataModel();
		Document doc = Jsoup.parse(getSite.downloadSource(source));
		Element eBody = doc.select("body").first();
				
		// get content
		Elements eContent = eBody.select("div#contents").first().select("div#threads").first().children(); 
		Element e1;
		Element e2;
		Element e3;
		Element e4;
		Element e5;
		Integer key = 1;
		
		for (Element cell : eContent) {
			if (cell.attr("class").equals("reply")) {
				e1 = cell.select("span").first();
				e2 = cell.select("img").first();
				e3 = cell.select("label").first();
				e4 = cell.select("a.qlink").first();
				e5 = cell.select("div.quote").first();
				
				data.putCellData(String.valueOf(key),
						(e1 == null ? "" : e1.text()),
						(e2 == null ? "" : e2.attr("src")),
						(e3 == null ? "" : e3.ownText()), 
						(e4 == null ? "" : e4.text()), 
						(e5 == null ? "" : Util.html2text(e5.html())));
				key++;
			} else if (cell.attr("class").equals("threadpost")) {		
				e1 = cell.select("span.title").first();				
				e2 = cell.select("label").first();
				e3 = cell.select("img").first();
				e4 = cell.select("div.quote").first();
				
				data.putHeadData((e1 == null ? "" : e1.text()), 
						(e2 == null ? "" : e2.ownText()), 
						(e3 == null ? "" : e3.attr("src")), 
						(e4 == null ? "" : e4.text()));
			} else {
				//
			}
			
			e1 = null;
			e2 = null;
			e3 = null;
			e4 = null;
			e5 = null;
		}
		System.out.println(data.toString());
		return data;
	}
}
