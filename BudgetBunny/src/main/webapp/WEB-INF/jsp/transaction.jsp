<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@ include file = "imports.jsp" %>


<link rel="stylesheet" href="./static/css/transaction.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Transactions</title>

</head>
<body>
	<div id='home-div'>
	<%@ include file = "_navbar.jsp" %>

<!-- ArrayList DummyData -->
<% ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
	ArrayList<String> sub = new ArrayList<String>();
	sub.add("1");
	sub.add("2");
	sub.add("3");
	test.add(sub);
	test.add(sub);
%>


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
      
  <div class="dropdown">
    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Categories
    <span class="caret"></span></button>
    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Cat 1</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Cat 2</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Cat 3</a></li>

    </ul>
  </div>
 
      </td>
    </tr>
  </tbody>
</table>



<!-- Table for Transactions -->
<table class="table table-hover">
  <thead>
    <tr>
      <th></th>
      <th>Date of Transaction</th>
      <th>Spent</th>
      <th>Category</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Test</td>
      <td>Test</td>
      <td>Test</td>
    </tr>
        <tr>
      <th scope="row">2</th>
      <td>Test</td>
      <td>Test</td>
      <td>Test</td>
    </tr>
    <tr>
    </tr>
  </tbody>
</table>


</div>


  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-info btn-lg" id="myBtn">Add Transaction</button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <jsp:include page="newTransaction.jsp" />
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add a Transaction</h4>
        </div>
        <div class="modal-body">
          <p>Please add transaction here</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="static/js/transaction.js"></script>
