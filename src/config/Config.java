package config;



public class Config {

	/**
	 * <article.*|<inproceedings.*|<proceedings.*|<book.*|"
//			+ "<incollection.*|<phdthesis.*|<mastersthesis.*|<www.*"
	 */
	public static final int articleExtractNum = 3000;
	public static final int inproceedingsExtractNum = 2500;
	public static int proceedingsRetriveNum = 2500;
	public static int bookExtractNum = 2500;
	public static int incollectionExtractNum = 2500;
	public static int phdthesisExtractNum = 2500;
	public static int mastersthesisExtractNum = 7;
	public static int wwwExtractNum = 3000;
	
	
	public static final String XMLFileFrom = "/Volumes/Quan/9321/newxml/dblp.xml"; //Should not be used in real
	public static final String XMLOutput = "/Users/Linus/Desktop/dplb.xml"; //
	public static final String XMLParse = "/Users/Linus/Documents/workspace2/Assignment1/WebRoot/xml/dblp.xml";
	///    usr/local/apache-tomcat-7.0.70/wtpwebapps/Assignment1/xml/dblp.xml
	//search types
	public static final String searc_type_pub = "pub_type";
	public static final String searc_type_author = "author";
	public static final String searc_type_pub_title = "pub_title";
	public static final String searc_type_pub_date = "pub_date";
	public static final String searc_type_venue = "venue";
	
	//tags we care about
	public static final String [] subtags = new String[] {"title","author","editor",
			"booktitle","pages","year","address","volume",
			"number","url", "journal","month",
			"ee","cdrom","cite","publisher","note","crossref","isbn","series","ieString","school","chapter"};
	public static final String title = "title";
	public static final String author = "author";
	public static final String editor = "editor";
	public static final String booktitle = "booktitle";
	public static final String pages = "pages";
	public static final String year = "year";
	public static final String address = "address";
	public static final String volume = "volume";
	public static final String number = "number";
	public static final String url = "url";
	
	//http service request type
	public static final String service_request_type_simpleSearch = "simple_search";
	public static final String service_request_type_adv_search = "adv_search";
	/*
	 *  * <option value="pub_title">Publication Title</option>
							<option value="author">Author</option>
							<option value="pub_type">Publication Type</option>
							<option value="pub_date">Date</option>
							<option value="venue">Venue</option>
		 */
	 
}
