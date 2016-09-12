<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entity.Publication"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="util.PrintingUtil"%>
<%@ page import="util.StringUtil"%>
<%@ page import="util.PageHandler"%>

<%
	int id_num = Integer.parseInt(request.getParameter("id"));
	ArrayList<Publication> resultPublications = (ArrayList<Publication>) session.getAttribute("searchResult");
	if (resultPublications == null) {
		response.sendRedirect("../failure_or_error.jsp");
	}
	Publication publicationToDisplay = resultPublications.get(id_num);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="header">
		<div class="container">
			<a href="welcome.jsp"><img src="images/book.png" width="50"></a>
		</div>
	</div>

	<div class="main">
		<div class="container" id="search_block">
			<table class="table">
				<tbody>
					<%
						String title = publicationToDisplay.getTitle();
						if (title != null) {
							out.println("<tr><th>Title:&nbsp</th><th>" + title + "</th></tr>");
						}

						ArrayList<String> authors = publicationToDisplay.getAuthor_or_editors();

						if (authors != null) {
							for (String author : authors) {
								out.println("<tr><th>Author:&nbsp</th><th>" + author + "</th></tr>");
							}
						}

						String booktitle = publicationToDisplay.getBooktitle();
						if (booktitle != null) {
							out.println("<tr><th>Book Title:&nbsp</th><th>" + booktitle + "</th></tr>");
						}

						String pages = publicationToDisplay.getPages();
						if (pages != null) {
							out.println("<tr><th>Pages:&nbsp</th><th>" + pages + "</th></tr>");
						}

						String year = publicationToDisplay.getYear();
						if (year != null) {
							out.println("<tr><th>Year:&nbsp</th><th>" + year + "</th></tr>");
						}

						String address = publicationToDisplay.getAddress();
						if (address != null) {
							out.println("<tr><th>Address:&nbsp</th><th>" + address + "</th></tr>");
						}

						String volume = publicationToDisplay.getVolume();
						if (address != null) {
							out.println("<tr><th>Volume:&nbsp</th><th>" + volume + "</th></tr>");
						}

						String number = publicationToDisplay.getNumber();
						if (address != null) {
							out.println("<tr><th>Number:&nbsp</th><th>" + number + "</th></tr>");
						}

						String url = publicationToDisplay.getUrl();
						if (address != null) {
							out.println("<tr><th>Address:&nbsp</th><th>" + url + "</th></tr>");
						}

						String journal = publicationToDisplay.getJournal();
						if (journal != null) {
							out.println("<tr><th>Journal:&nbsp</th><th>" + journal + "</th></tr>");
						}

						String month = publicationToDisplay.getMonth();
						if (month != null) {
							out.println("<tr><th>Month:&nbsp</th><th>" + month + "</th></tr>");
						}
						
						String ee = publicationToDisplay.getEe();
						if (ee != null) {
							out.println("<tr><th>EE:&nbsp</th><th>" + ee + "</th></tr>");
						}
						
						String cdrom = publicationToDisplay.getCdrom();
						if (cdrom != null) {
							out.println("<tr><th>Month:&nbsp</th><th>" + cdrom + "</th></tr>");
						}
						
						String cite = publicationToDisplay.getCite();
						if (cite != null) {
							out.println("<tr><th>Month:&nbsp</th><th>" + cite + "</th></tr>");
						}
						
						String publisher = publicationToDisplay.getPublisher();
						if (publisher != null) {
							out.println("<tr><th>Month:&nbsp</th><th>" + publisher + "</th></tr>");
						}
						
						String note = publicationToDisplay.getNote();
						if (note != null) {
							out.println("<tr><th>Month:&nbsp</th><th>" + note + "</th></tr>");
						}
						
						String crossref = publicationToDisplay.getCrossref();
						if (crossref != null) {
							out.println("<tr><th>Crossref:&nbsp</th><th>" + crossref + "</th></tr>");
						}
						
						String isbn = publicationToDisplay.getIsbn();
						if (isbn != null) {
							out.println("<tr><th>ISBN:&nbsp</th><th>" + isbn + "</th></tr>");
						}
						
						String series = publicationToDisplay.getSeries();
						if (series != null) {
							out.println("<tr><th>Series:&nbsp</th><th>" + series + "</th></tr>");
						}
						
						String ieString = publicationToDisplay.getIeString();
						if (ieString != null) {
							out.println("<tr><th>IeString:&nbsp</th><th>" + ieString + "</th></tr>");
						}
						
						String school = publicationToDisplay.getSchool();
						if (school != null) {
							out.println("<tr><th>school:&nbsp</th><th>" + school + "</th></tr>");
						}
						
						String chapter = publicationToDisplay.getMonth();
						if (chapter != null) {
							out.println("<tr><th>chapter:&nbsp</th><th>" + chapter + "</th></tr>");
						}
						
						
						

						
				
					%>

				</tbody>
			</table>
			<div class="center_container">
				<a class="page_btn" id="cartadd"
					href="cart.jsp?record_id=<%=id_num%>">Add to cart</a>
			</div>
		</div>

	</div>

	<div class="footer">
		<div class="container">
			<ul>
				<li><a href="http://www.unsw.edu.au">Contact</a></li>
				<li><a href="welcome.jsp">Back to Home</a></li>
				<li><a
					href="http://www.cse.unsw.edu.au/~cs9321/16s2/assignments/ass1/assignment1.html">About</a></li>
			</ul>
		</div>
	</div>


</body>
</html>