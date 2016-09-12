<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="domparse.SearchXMLQuery"%>
<%@ page import="entity.Publication"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="util.PrintingUtil"%>
<%@ page import="config.Config"%>

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
			<form action="servlet/ControlServlet" method="post">

				<!-- row one -->
				<div class="form-group row">
					<input type="hidden" name="sevice_requestType"
						value="simple_search">

					<div class="col-xs-6">
						<label for="search_box">Search:</label> <input type="text"
							class="form-control" name="keybox"
							placeholder="Input keyword to search">
					</div>

					<div class="col-xs-3">
						<label for="exampleInputName2">Search Type:</label> <select
							name="type" class="form-control form-control-lg">
							<!--  publications, authors, publication-type (e.g. Book, Journal, Conference, etc.), publication-date, and venues -->
							<option value="<%=Config.searc_type_pub_title %>">Publication Title</option>
							<option value="<%=Config.searc_type_author %>">Author</option>
							<option value="<%=Config.searc_type_pub %>">Publication Type</option>
							<option value="<%=Config.searc_type_pub_date %>">Date</option>
							<option value="<%=Config.searc_type_venue %>">Venue</option>
						</select>
					</div>

					<div class="col-xs-3">
						<button type="submit" class="form-control btn">Search</button>
					</div>
				</div>

			</form>

		</div>

		<div class="container" id="display_book">
			<h3>Trending Publications:</h3>
			<table class="table">
				<thead>
					<%
						String xmlDataBasePath = null;
						ServletContext context = getServletContext();
						xmlDataBasePath = context.getRealPath("");
						xmlDataBasePath += getServletContext().getInitParameter("xml_path");
						//out.println(xmlDataBasePath);

						SearchXMLQuery search = new SearchXMLQuery(xmlDataBasePath);
						ArrayList<Publication> publications = search.randomRetrive(15000, 10);
					%>

					<tr>
						<th>Num</th>
						<th>Publication Type</th>
						<th>Title</th>
						<th>Author</th>
					</tr>
				</thead>
				<tbody>
					<%
						PrintingUtil printUtil = new PrintingUtil();
						int index =1;
						for (Publication publication: publications) {
							String sb = printUtil.printItemLine(publication, index,false);
							out.print(sb);
							++index;
						}
					%>

				</tbody>
			</table>

		</div>


	</div>

	<div class="footer">
		<div class="container">
			<ul>
				<li><a href="http://www.unsw.edu.au">Contact</a></li>
				<li><a href="cart.jsp">Shopping Cart</a></li>
				<li><a href="advanced_search.jsp">Advanced Search</a></li>
				<li><a
					href="http://www.cse.unsw.edu.au/~cs9321/16s2/assignments/ass1/assignment1.html">About</a></li>
			</ul>
		</div>
	</div>





</body>
</html>