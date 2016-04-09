package com.alssc.komicaparser.util.net;

import java.io.File;
import java.io.IOException;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;

public class GetSite {

	// http://www.useragentstring.com/pages/useragentstring.php 
	String uaFirefox = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1";
	String uaChrome = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

	
	public String downloadSource(String source) {
		UrlValidator urlValidator = new UrlValidator();
		if (urlValidator.isValid(source)) {
			return downloadSite(source);
		} else {
			return downloadDummy(source);
		}
	}
	
	public String downloadSite(String url) {
		try {
			return Jsoup.connect(url).userAgent(uaFirefox).get().html();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String downloadDummy(String name) {
		try {
			File input = new File("src/test_data/dummy/" + name + ".html");
			return Jsoup.parse(input, "UTF-8").html();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
