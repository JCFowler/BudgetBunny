<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import ="com.revature.service.TransactionService" %>
<%@ page import ="com.revature.service.CategoryService" %>
<%@ page import ="com.revature.bean.Budget" %>
<%@ page import ="com.revature.bean.Transaction" %>
<%@ page import ="com.revature.bean.Category" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
CategoryService cat = new CategoryService();
ArrayList<Category> item = (ArrayList<Category>)session.getAttribute("cats");

%>



<%@ include file = "imports.jsp" %>

<link rel="stylesheet" href="./static/css/transaction.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Transactions</title>

</head>
<body>
	<div id='home-div'>
	<%@ include file = "_navbar.jsp" %>



<div class="container">

<!-- Filter Table-->
<table class="table">
  <thead class="thead-default">
    <tr>
      <th></th>
      <th>Start Date</th>
      <th>End Date</th>
      <th>Category</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row"></th>
      <td>      
      <input name="startDate" type="date"	>
        <span class="glyphicon glyphicon-th"></span></td>
      <td>      
      <input name="startDate" type="date"	>
        <span class="glyphicon glyphicon-th"></span></td>
      <td>  
  </div>
  
  <div class="dropdown">
  <button onclick="myFunction()" class="dropbtn">Dropdown</button>
  <div id="myDropdown" class="dropdown-content">
	<% for (Category test : item){ %>
	<a href="#"><%= test.getName()%></a>
	<% } %>
  </div>
      <button class='btn btn-info' id='sort'>Sort</button>
</div>


      </td>
    </tr>
  </tbody>
</table>



<!-- Table for Transactions -->
<table class="table table-hover">
  <thead>
    <tr>
      <th>Date of Transaction</th>
      <th>Spent</th>
      <th>Category</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${transaction}" var="item">
						<tr id="row${item.transactionId}">
							<td hidden>${item.transactionId}</td>
							<td>${item.date}</td>
							<td>$${String.format( "%.2f",item.cost)}</td>
							<td>${item.cat.name}</td>
							<td><button class='RemoveButton tranRemove'
									type="${item.transactionId}" id="${item.transactionId}">
									<span class="glyphicon glyphicon-remove glyph-small"
										style="font-size: .75em;" aria-hidden="true"></span>
								</button></td>
						</tr>
					</c:forEach>
  </tbody>
</table>


</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="static/js/transaction.js"></script>
