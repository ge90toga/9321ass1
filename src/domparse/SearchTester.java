package domparse;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import config.Config;
import entity.Publication;
import util.PageHandler;
import util.StringUtil;

public class SearchTester {

	@Test
	public void testSimpleSearchTitle() throws Exception {
		
//		SearchXMLQuery searchXMLQuery = new SearchXMLQuery(Config.XMLParse);
//		System.out.println("Test Simple Search article");
//		ArrayList<Publication> publications = searchXMLQuery.simpleSearch(Config.searc_type_venue, "Logik, Komplexit");
//		StringUtil.printTop10List(publications);
		
		//System.out.println("");
		//fail("Not yet implemented");
	}
//	
	
//	@Test
//	public void testSimpleSearchOther() throws Exception {
//		SearchXMLQuery searchXMLQuery = new SearchXMLQuery(Config.XMLParse);
//		System.out.println("Test Simple Search author");
//		ArrayList<Publication> publications = searchXMLQuery.simpleSearch(Config.searc_type_pub_title, "ASP.NET cookbook - the ultimate ASP.NET");
//		PageHandler pg_handler = new PageHandler();
//		System.out.println(PageHandler.get_Total_Page(publications));
//		StringUtil.printTop10List(publications);
//
//	}
	
	@Test
	public void testAdv() throws Exception {
		
		SearchXMLQuery searchXMLQuery = new SearchXMLQuery(Config.XMLParse);
		System.out.println("Test Adv Search article");
		//ArrayList<Publication> publications = searchXMLQuery.advSearchII("article", "Mike", null, null);
		ArrayList<Publication> publications = searchXMLQuery.
				advancedSearch("", "Wannaporn", "inproceedings", "", "Complex Adapti");
		StringUtil.printTop10List(publications);
		
	}

}
