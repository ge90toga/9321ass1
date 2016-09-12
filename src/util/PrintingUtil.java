package util;

import java.util.ArrayList;

import entity.Publication;

public class PrintingUtil {

	public String printItemLine(Publication publication, int index, boolean request_deail){
		StringBuffer sb = new StringBuffer("<tr>"+System.lineSeparator());
		sb.append("<th scope=\"row\">"+index+"</th>"+System.lineSeparator());
		sb.append("<td>"+publication.getType()+"</td>"+System.lineSeparator());
		
		String title = publication.getTitle();
		String a = "<a>";
		if(request_deail == true){
			a = addHyperLink(publication, index);
		}
		//sb.append(a);
		if(title.length()>=40){
			sb.append("<td>"+a+title.substring(0,40)+"</a></td>");
		}else{
			sb.append("<td>"+a+title+"</a></td>");
		}
		
		String authors = publication.toStringAuthors();
		
		if(authors.length()>=40){
			sb.append("<td>"+authors.substring(0,40)+"</td>");
		}else{
			sb.append("<td>"+authors+"</td>");
		}
		
		sb.append("</tr>"+System.lineSeparator());
		
		return sb.toString();
	}
	
	public String addHyperLink(Publication publication, int index){
		return "<a href=\"details.jsp?id="+(index-1)+"\" >";
	}
	
}
