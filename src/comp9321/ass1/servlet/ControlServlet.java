package comp9321.ass1.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;
import domparse.SearchXMLQuery;
import entity.Publication;
import util.StringUtil;




/**
 * Servlet implementation class ControlServlet
 */
@WebServlet("/servlet/ControlServlet")
public class ControlServlet extends HttpServlet {
	
	String xmlDataBasePath;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlServlet() {
        super();
       
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String sevice_requestType = request.getParameter("sevice_requestType");
		if(sevice_requestType == null){
			return;
		}
		//SearchXMLQuery searchXMLQuery = new SearchXMLQuery(xmlDataFilePath);
		switch (sevice_requestType) {
		case Config.service_request_type_simpleSearch:
			doSimpleSearch(request,response);
			break;
		case Config.service_request_type_adv_search:
			doAdvancedSearch(request, response);
		default:
			break;
		}
		
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext context = getServletContext();
		xmlDataBasePath = context.getRealPath("");
		xmlDataBasePath += getServletContext().getInitParameter("xml_path");
	}
	
	
	/**
	 * What it will do:
	 * 1. search and find list of publications 
	 * 2. get the first 10 records
	 * 3. forward search result to result.jsp for display
	 * @param keyword
	 * @param searchType
	 * @throws IOException 
	 */
	private void doSimpleSearch(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String keyword = request.getParameter("keybox");
		String searchType = request.getParameter("type");
		SearchXMLQuery searchXMLQuery = new SearchXMLQuery(xmlDataBasePath);
		ArrayList<Publication> resultPublications = null;
		try{
			resultPublications = searchXMLQuery.simpleSearch(searchType, keyword);
			request.getSession().setAttribute("searchResult", resultPublications);
			response.sendRedirect("../result.jsp?page=1");
			
		}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("../failure_or_error.jsp");
		}
	}
	
	
	private void doAdvancedSearch(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String pub_title_key_1 = request.getParameter(Config.searc_type_pub_title);
		String pub_title_key_2 = request.getParameter(Config.searc_type_pub_title+"_2");
		
		String author_1 = request.getParameter(Config.searc_type_author);
		String author_2 = request.getParameter(Config.searc_type_author+"_2");
		
		String type_1 = request.getParameter(Config.searc_type_pub);
		String type_2 =  request.getParameter(Config.searc_type_pub+"_2");
		
		String year = request.getParameter(Config.searc_type_pub_date);
		String year_2 = request.getParameter(Config.searc_type_pub_date+"_2");
		
		String venue = request.getParameter(Config.searc_type_venue);
		String venue_2 = request.getParameter(Config.searc_type_venue+"_2");
		
		
		SearchXMLQuery searchXMLQuery = new SearchXMLQuery(xmlDataBasePath);
		ArrayList<Publication> resultPublications = null;
		
		try{
			resultPublications = searchXMLQuery.advancedSearch(pub_title_key_1, author_1, type_1, year, venue);
			if(filedNotEmpty(pub_title_key_2,author_2,year_2)){
				resultPublications.addAll(searchXMLQuery.advancedSearch(pub_title_key_2, author_2, type_2, year_2, venue_2));
			}
			
			request.getSession().setAttribute("searchResult", resultPublications);
			response.sendRedirect("../result.jsp?page=1");
			
		}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("../failure_or_error.jsp");
		}
				
		
	}
	
	private boolean filedNotEmpty(String title,String author,String year ){
		
		return ((!StringUtil.isNull(title))|(!StringUtil.isNull(author))|(!StringUtil.isNull(year)));
	}
	

}
