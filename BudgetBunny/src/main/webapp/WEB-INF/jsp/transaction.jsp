<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.revature.service.TransactionService"%>
<%@ page import="com.revature.service.CategoryService"%>
<%@ page import="com.revature.bean.Budget"%>
<%@ page import="com.revature.bean.Transaction"%>
<%@ page import="com.revature.bean.Category"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	CategoryService cat = new CategoryService();
	ArrayList<Category> item = (ArrayList<Category>) session.getAttribute("cats");
	ArrayList<Transaction> list = (ArrayList<Transaction>) session.getAttribute("transaction");
%>



<%@ include file="imports.jsp"%>

<link rel="stylesheet" href="./static/css/transaction.css">
<link rel="stylesheet" href="./static/css/navbar.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title>Transactions</title>

</head>
<body>
	<div id='home-div'>
		<%@ include file="_navbar.jsp"%>



		<div class="container">

			<!-- Filter Table-->
			<table class="table" id="filtertable1">
				<thead class="thead-default">
					<tr id="top">
						<th></th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Category</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row"></th>
						<td><input id="startDate" type="date"> <span
							class="glyphicon glyphicon-th"></span></td>
						<td><input id="endDate" type="date"> <span
							class="glyphicon glyphicon-th"></span></td>
						<td>



							<div class="dropdown">
								<select id="myDropdown" class="dropdown-content">
									<option class="option" href="#">All</option>
									<%
										for (Category test : item) {
									%>
									<option class="option" href="#"><%=test.getName()%></option>
									<%
										}
									%>
								</select>
							</div>
						</td>

					</tr>
				</tbody>

			</table>

			<!-- Table for Transactions -->
			<table class="table table-hover" id="filtertable">
				<thead>
					<tr>
						<th>Date of Transaction</th>
						<th>Spent</th>
						<th>Category</th>
					</tr>
				</thead>
				<tbody id="filterrow">
					<c:forEach items="${transaction}" var="item">
						<tr id="row${item.transactionId}">
							<td hidden>${item.transactionId}</td>
							<td id="trandate">${item.date}</td>
							<td>$${String.format( "%.2f",item.cost)}</td>
							<td id="catname">${item.cat.name}</td>
							<td><button class='RemoveButton tranRemove'
									type="${item.transactionId}" id="${item.transactionId}">
									<span class="glyphicon glyphicon-remove glyph-small"
										style="font-size: .75em;" aria-hidden="true"></span>
								</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			</div>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="static/js/transaction.js"></script>
</body>



</html>


