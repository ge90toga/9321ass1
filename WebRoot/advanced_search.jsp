<%@page import="config.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Publications</title>
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
					<input type="hidden" name="sevice_requestType" value="adv_search">

					<div class="col-xs-3">
						<label for="publications">Publication Title#1:</label> <input
							type="text" class="form-control"
							name="<%=Config.searc_type_pub_title%>" placeholder="Input Title">
					</div>

					<div class="col-xs-3">
						<label for="exampleInputName2">Author #1:</label> <input
							type="text" name="<%=Config.searc_type_author%>"
							class="form-control" placeholder="Author #1">
					</div>


					<div class="col-xs-2">
						<label for="exampleInputName2">Type #1:</label> <select
							name="<%=Config.searc_type_pub%>"
							class="form-control form-control-lg">
							<option value="article">Article</option>
							<option value="inproceedings">Inproceedings</option>
							<option value="proceedings">Proceedings</option>
							<option value="book">Book</option>
							<option value="incollection">Incollection</option>
							<option value="phdthesis">Phdthesis</option>
							<option value="mastersthesis">Masterthesis</option>
							<option value="www">www</option>
						</select>
					</div>

					<div class="col-xs-2">
						<label for="example">Year #1</label> <input
							name="<%=Config.searc_type_pub_date%>" class="form-control"
							type="text" placeholder="e.g. 1998">
					</div>

					<div class="col-xs-2">
						<label for="example">Venue #1</label> <input
							name="<%=Config.searc_type_venue%>" class="form-control"
							type="text" placeholder="Venue #1">
					</div>



				</div>
				<!-- row 2 -->
				<div class="form-group row">
					<div class="col-xs-3">
						<label for="publications">Publication Title #2:</label> <input
							type="text" class="form-control"
							name="<%=Config.searc_type_pub_title%>_2"
							placeholder="Input Title">
					</div>

					<div class="col-xs-3">
						<label for="exampleInputName2">Author #2:</label> <input
							type="text" name="<%=Config.searc_type_author%>_2"
							class="form-control" placeholder="Author #2">
					</div>


					<div class="col-xs-2">
						<label for="exampleInputName2">Type #2:</label> <select
							name="<%=Config.searc_type_pub%>_2"
							class="form-control form-control-lg">
							<option value="article">Article</option>
							<option value="inproceedings">Inproceedings</option>
							<option value="proceedings">Proceedings</option>
							<option value="book">Book</option>
							<option value="incollection">Incollection</option>
							<option value="phdthesis">Phdthesis</option>
							<option value="mastersthesis">Masterthesis</option>
							<option value="www">www</option>
						</select>
					</div>

					<div class="col-xs-2">
						<label for="example">Year #2</label> <input
							name="<%=Config.searc_type_pub_date%>_2" class="form-control"
							type="text" placeholder="e.g. 1998">
					</div>
					
					<div class="col-xs-2">
						<label for="example">Venue #2</label> <input
							name="<%=Config.searc_type_venue%>_2" class="form-control"
							type="text" placeholder="Venue #2">
					</div>

				</div>

				
				<div class="form-group row">
					<div class="col-xs-5"></div>

					<div class="col-xs-2">
						<button type="submit" class="form-control btn">Search</button>
					</div>

				</div>

			</form>

		</div>

		<div class="container" id="display_book"></div>


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