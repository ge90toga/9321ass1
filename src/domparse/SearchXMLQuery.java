package domparse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import config.Config;
import entity.Publication;
import util.RandomUtil;
import util.StringUtil;

/**
 * The class dealing with searching xml data based on user's request. It
 * provides mainly two method for searching data and return a list publications.
 * simpleSearch and advancedSearch
 * 
 * @author Linus Quan
 */
public class SearchXMLQuery {

	public HashSet<String> tagSets = new HashSet<>(Arrays.asList(Config.subtags));
	private String xmlDataFilePath = null;

	public SearchXMLQuery(String xmlDataFilePath) {
		this.xmlDataFilePath = xmlDataFilePath;
	}

	/**
	 * 
	 * Simple searching by providing only a type and a keyword. E.g.
	 * searchTest.simpleSearch(Config.searc_type_pub, "article")
	 * 
	 * @param search_type
	 * @param keyword
	 * @return a list of publication, if publication.size() ==0, no result
	 *         found.
	 * @throws Exception
	 */
	public ArrayList<Publication> simpleSearch(String search_type, String keyword) throws Exception {
		ArrayList<Publication> publications = new ArrayList<>();

		// Open DOM parser and load xml file
		//
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(xmlDataFilePath);
		// Using Xpath to select and guide search
		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression = null;

		// If searching type is publication type (article, inproceddings,
		// phdthesis... higher path is /dblp/*)
		if (search_type.equals(Config.searc_type_pub)) {
			expression = "/dblp/*";
		} else {
			expression = generateExpressionSimple(search_type, keyword);
		}

		// Get rough qualified node
		NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);

		// Iterating through rough qualified node to check details
		for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
			Node nod = nodeList.item(i);
			// if searching type is publication type, then add all publications
			// belongs that type
			if (search_type.equals(Config.searc_type_pub) && nod.getNodeName().equals(keyword.trim().toLowerCase())) {
				Publication publication = getEntryDetail(nod);
				publications.add(publication);
				continue;
			} else if (nod.getTextContent().toLowerCase().contains(keyword.trim().toLowerCase())) {
				Publication publication = getEntryDetail(nod.getParentNode());
				publications.add(publication);
			}
		}
		
		publications = StringUtil.removeNull(publications);
		return publications;
	}
		

	/**
	 * Generate search xpath based on the search type
	 * 
	 * @param type
	 *            tile, author, address, year...
	 * @param keyword
	 * @return xpath expression string
	 */
	private String generateExpressionSimple(String type, String keyword) {
		switch (type) { // accept "pub_title, author, venue, date "
		case Config.searc_type_pub_title:
			return "/dblp//title";
		case Config.searc_type_author:
			return "/dblp//*[self::author or self::editor]";
		case Config.searc_type_venue:
			return "/dblp//*[self::booktitle or self::journal or self::url or self::school]";
		case Config.searc_type_pub_date:
			return "/dblp//year";
		}
		return null;
	}

	/**
	 * Providing detailed search ability Note pub_type Should never be null!
	 * title, year author and address are optional.
	 * 
	 * @param title
	 *            the title of the publicaiton...
	 * @param author
	 * @param pub_type
	 * @param year
	 * @param address
	 * @throws Exception
	 */
	public ArrayList<Publication> advancedSearch(String title, String author, String pub_type, String year,
			String venue) throws Exception {

		ArrayList<Publication> publications = new ArrayList<>();
		// Open DOM parser
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(xmlDataFilePath);
		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression = "/dblp/" + pub_type;
		// using xpath to match title
		if (!StringUtil.isNull(title)) {// [contains(text(),’Create’)]
			expression += "/title[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'"
					+ title.toLowerCase() + "')]";
		}

		NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);

		// continue checking detials
		for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
			Node nod = nodeList.item(i);

			if (StringUtil.isNull(title)) {// if title is null, the nod is the
											// parent node
				if (nodeDetailCheck(nod, author, year, venue)) {
					publications.add(getEntryDetail(nod));
				}
			} else if (nodeDetailCheck(nod.getParentNode(), author, year, venue)) {
				// if title is not null, the nod is the child node
				publications.add(getEntryDetail(nod.getParentNode()));
			}
		}

		return publications;

	}
		
	

	/**
	 * 
	 * @param parentNode
	 * @param author_key
	 * @param year_key
	 * @param address_key
	 * @return
	 */
	private boolean nodeDetailCheck(Node parentNode, String author_key, String year_key, String venue) {
		NodeList nodeList = parentNode.getChildNodes();

		// pre - prpocess
		boolean authorMatch = false;
		boolean yearMatch = false;
		boolean venueMatch = false;

		// if not specified, make them true
		if (StringUtil.isNull(author_key)) {
			authorMatch = true;
		}

		if (StringUtil.isNull(year_key)) {
			yearMatch = true;
		}
		
		if(StringUtil.isNull(venue)){
			venueMatch = true;
		}
		
		for (int j = 0; null != nodeList && j < nodeList.getLength(); j++) {
			Node nod = nodeList.item(j);
			if ((nod.getNodeName().equals("author") || nod.getNodeName().equals("editor")) && !authorMatch) {
				authorMatch = StringUtil.matches(nod.getTextContent(), author_key);
			} else if (nod.getNodeName().equals("year") && !yearMatch) {
				yearMatch = StringUtil.matches(nod.getTextContent(), year_key);
			} else if(
					(nod.getNodeName().equals("booktitle") ||
					nod.getNodeName().equals("journal")||
					nod.getNodeName().equals("url")||
					nod.getNodeName().equals("school"))
					&& !venueMatch){ 
				venueMatch = true;
			}
		}

		if (authorMatch && yearMatch && venueMatch) {
			return true;
		}
		return false;
	}

	/**
	 * Randomly extract some Records, usually used in front page display
	 * 
	 * @param included_entry_amount
	 *            select from top n entry
	 * @param display_num
	 *            retrive how many from included included_entry_amount,
	 *            generally should be smaller
	 * @throws Exception
	 */
	public ArrayList<Publication> randomRetrive(int included_entry_amount, int display_num) throws Exception {

		ArrayList<Publication> publications = tryRand10(included_entry_amount, display_num);
		while(true){
			if(publications.size()==10){
				return publications;
			}
			 publications = tryRand10(included_entry_amount, display_num);
		}
	}

	private ArrayList<Publication> tryRand10(int included_entry_amount, int display_num)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		if (display_num > included_entry_amount) {
			throw new NumberFormatException("included_entry_amount < display_num");
		}

		ArrayList<Publication> publications = new ArrayList<>();
		// Open DOM parser
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(xmlDataFilePath);
		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression = "/dblp/*[position() <= " + included_entry_amount + "]";

		NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);

		ArrayList<Integer> randoms = new ArrayList<>();

		for (int start = 0; start < display_num; start++) {
			int rand = RandomUtil.randInt(0, included_entry_amount - 1);
			Node nod = nodeList.item(rand);
			// generate display_nums index for random display
			while (!randoms.contains(rand) && getEntryDetail(nod) !=null) {
				randoms.add(rand);
				publications.add(getEntryDetail(nod));
			}

		}
		return publications;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Whenever a tag is matched, passing that tag and get all information to
	 * form an entire publication Object. Note if article/author and author is
	 * matched, need to pass article node which is the parent of author.
	 * 
	 * @param parentNode
	 * @return
	 */
	private Publication getEntryDetail(Node parentNode) {

		Publication publication = new Publication();
		publication.setType(parentNode.getNodeName());
		NodeList nodeList = parentNode.getChildNodes();
		for (int i = 0; null != nodeList && i < nodeList.getLength(); i++) {
			Node nod = nodeList.item(i);
			if (tagSets.contains(nod.getNodeName())) {
				switch (nod.getNodeName()) {
				case Config.title:
					publication.setTitle(nod.getTextContent());
					break;
				case Config.author:
					publication.add_Author_or_editors(nod.getTextContent());
					break;
				case Config.editor:
					publication.add_Author_or_editors(nod.getTextContent());
					break;
				case Config.booktitle:
					publication.setBooktitle(nod.getTextContent());
					break;
				case Config.pages:
					publication.setPages(nod.getTextContent());
					break;
				case Config.year:
					publication.setYear(nod.getTextContent());
					break;
				case Config.address:
					publication.setAddress(nod.getTextContent());
					break;
				case Config.volume:
					publication.setVolume(nod.getTextContent());
					break;
				case Config.number:
					publication.setNumber(nod.getTextContent());
					break;
				case Config.url:
					publication.setUrl(nod.getTextContent());
					break;
				case "journal":
					publication.setJournal(nod.getTextContent());
					break;
				case "month":
					publication.setMonth(nod.getTextContent());
					break;
				case "ee":
					publication.setEe(nod.getTextContent());
					break;
				case "cdrom":
					publication.setCdrom(nod.getTextContent());
					break;
				case "cite":
					publication.setCite(nod.getTextContent());
					break;
				case "publisher":
					publication.setPublisher(nod.getTextContent());
					break;
				case "note":
					publication.setNote(nod.getTextContent());
					break;
				case "crossref":
					publication.setCrossref(nod.getTextContent());
					break;
				case "isbn":
					publication.setIsbn(nod.getTextContent());
					break;
				case "series":
					publication.setSeries(nod.getTextContent());
					break;
				case "ieString":
					publication.setIeString(nod.getTextContent());
					break;
				case "school":
					publication.setSchool(nod.getTextContent());
					break;
				case "chapter":
					publication.setChapter(nod.getTextContent());
					break;	
				}

			}
		}
		if(StringUtil.isNull(publication.getTitle())){
			return null;
		}
		return publication;
	}

	

}
