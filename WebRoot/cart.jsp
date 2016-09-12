<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="entity.Cart"%>
<%@ page import="entity.Publication"%>
<%@ page import="entity.Publication"%>
<%@ page import="java.util.ArrayList"%>
<%
	//record_id
	int record_id = -1;
	Cart cart = null;
	if (request.getParameter("record_id") != null) {
		record_id = Integer.parseInt(request.getParameter("record_id"));
	}

	if (null != session.getAttribute("cart")) {
		cart = (Cart) session.getAttribute("cart");
	}

	//check if there is removing request:
	
	String[] itmesRemove = request.getParameterValues("is_remove");
	if(itmesRemove !=null && cart !=null){
		for(int i=0; i<itmesRemove.length;++i ){
			//System.out.println(isRemove[i]+" "+i);
			cart.removeItem(Integer.parseInt(itmesRemove[i]));
		}
		
	}
	
	if (record_id >= 0) {
		//String current_item = request.getParameter("item_title");
		ArrayList<Publication> resultPublications = (ArrayList<Publication>) session
				.getAttribute("searchResult");
		Publication pub = resultPublications.get(record_id);
		String item = pub.getTitle();
		if (cart == null) { //create a cart obj
			cart = new Cart();
		}
		if (cart != null) {
			cart.addItem(item);
			session.setAttribute("cart", cart);
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Result</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart</title>
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
				if (!Cart.emptyCart(cart)) {
			%>
			<form action="cart.jsp" method="post">
			<input type="hidden" name="remove_type"
						value="remove">
				<table class="table">
					<thead>
						<tr>
							<th>Num</th>
							<th>Title</th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<%
							int index = 1;
								Iterator<String> ite = cart.getItems().iterator();
								while (ite.hasNext()) {
									String title = ite.next();
						%>
						<tr>
							<th><%=index%></th>
							<th><%=title%></th>
							<th><input type="checkbox" name="is_remove" value="<%=index-1 %>"/></th>
						</tr>

						<%
							++index;
								}
						%>



					</tbody>

					</tbody>
				</table>
				<div class="form-group row">
				<div class="col-xs-4">
				</div>
					<div class="col-xs-3">
						<button type="submit" class="form-control btn">Remove from cart</button>
					</div>
				</div>


			</form>
			<%
				} else {
					out.println("<p>Shopping Cart is Empty!</p>");
				}
			%>
		</div>

	</div>
	<div class="footer">
		<div class="container">
			<ul>
				<li><a href="http://www.unsw.edu.au">Contact</a></li>
				<li><a href="welcome.jsp">Back to Search</a></li>

			</ul>
		</div>
	</div>
</body>
</html>