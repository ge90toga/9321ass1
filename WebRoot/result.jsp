<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entity.Publication"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="util.PrintingUtil"%>
<%@ page import="util.StringUtil"%>
<%@ page import="util.PageHandler"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Result</title>
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
		<div class="container">
			<%
				int page_num = 1;
				int total_page_num = 1;
				page_num = Integer.parseInt(request.getParameter("page"));
				ArrayList<Publication> resultPublications = (ArrayList<Publication>) session.getAttribute("searchResult");
				if(resultPublications!=null && resultPublications.size() != 0){	
			%>
			
			<p>Results Found: <%=resultPublications.size() %></p>
			<table class="table">
				<thead>
					<tr>
						<th>Num</th>
						<th>Publication Type</th>
						<th>Title</th>
						<th>Author</th>
					</tr>
				</thead>

				<tbody>
					<%
						String s = (String) request.getAttribute("page");
						total_page_num = PageHandler.get_Total_Page(resultPublications);
						//System.out.println(total_page_num);
						//System.out.println(s);
 						PageHandler pageHandler = new PageHandler();
						if(page_num <= total_page_num){
							ArrayList<Publication> show10Publications = pageHandler.showPage(resultPublications, page_num);
							PrintingUtil printer = new PrintingUtil();
							int index = 1;
							for(Publication publication:show10Publications ){
								out.println(printer.printItemLine(publication, (page_num-1)*10+index,true));
								++index;
							}
							
						}
						
					%>

				</tbody>
			</table>

			<% } else {%>
			<p>Sorry, no matching datasets found!</p>	
			<%} %>


			<div class="center_container">
				<% if(page_num > 1){ %>
					<a href="./result.jsp?page=<%=page_num-1 %>" class="page_btn">Previous</a>
				<%} %>
				
				<% if(page_num < total_page_num){ %>
					<a href="./result.jsp?page=<%=page_num+1 %>" class="page_btn">Next</a>
				<%} %>
				
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="container">
			<ul>
				<li><a href="http://www.unsw.edu.au">Contact</a></li>
				<li><a href="cart.jsp">Shopping Cart</a></li>
				<li><a href="welcome.jsp">Back to Home</a></li>
				<li><a href="http://www.cse.unsw.edu.au/~cs9321/16s2/assignments/ass1/assignment1.html">About</a></li>
			</ul>
		</div>
	</div>


</body>
</html>