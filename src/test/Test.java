package test;

import com.alssc.komicaparser.parser.access.AccessBulletin;
import com.alssc.komicaparser.parser.access.AccessMenu;
import com.alssc.komicaparser.parser.access.AccessSticky;
import com.alssc.komicaparser.util.DataLoader;
import com.alssc.komicaparser.util.datamodel.BulletinDataModel;
import com.alssc.komicaparser.util.datamodel.MenuDataModel;
import com.alssc.komicaparser.util.datamodel.StickyDataModel;
import com.alssc.komicaparser.util.datamodel.XMLDataModel;

public class Test {

	
	public static void main(String[] args) {
		// load setting.
		DataLoader dataLoader = new DataLoader();
		dataLoader.loadAll();
		XMLDataModel dataModule = dataLoader.getXMLDataModule();
		//System.out.println(dataModule.toString());
		
		String key = "";
		String className = "";
		String source = "";
		
		// download menu.
		//key = "KOMICA";
		//className = dataModule.getMenuMap(key);
		//source = "dummyMenu1";
		//AccessMenu am = new AccessMenu();
		//am.init(className);
		//MenuDataModel menuDataModel;
		//menuDataModel = am.getMenu(source);	
		//System.out.println(menuDataModel.toString());
		
		
		// download bulletin.
		//key = "KOMICA.連線板.新番實況";
		//className = dataModule.getBulletinMap(key);
		//source = "dummyBulletin1";
		//source = "http://2cat.or.tl/~tedc21thc/live/1.htm?";
		//AccessBulletin ab = new AccessBulletin();
		//ab.init(className);
		//BulletinDataModel bulletinDataModel;
		//bulletinDataModel = ab.getBulletin(source);
		//System.out.println(bulletinDataModel.toString());

	
		// download sticky.
		//key = "KOMICA.連線板.新番實況";
		//className = dataModule.getStickyMap(key);
		//source = "dummySticky1";
		//StickyDataModel stickyDataModule = new StickyDataModel();
		//AccessSticky as = new AccessSticky();
		//as.init(className);
		//stickyDataModule = as.getSticky(source);
		//System.out.println(stickyDataModule.toString());
	}
}
