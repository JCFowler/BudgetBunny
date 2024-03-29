<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<%@ include file = "imports.jsp" %>
		<link rel="stylesheet" href="static/css/jozsef.css">

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Budget Setup</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
</head>
<body id="setup-body">

	<div id='table-container'>
		<div id="withdrawFormDiv">
			<c:set var="headerMsg" value="Add Your Income"></c:set>
			<c:set var="type" value="deposit"></c:set>
			<%@ include file = "systematictransactionform.jsp" %>
		</div>
		
		<div id="depositFormDiv">
			<c:set var="headerMsg" value="Add Your Bills"></c:set>
			<c:set var="type" value="withdraw"></c:set>
			<%@ include file = "systematictransactionform.jsp" %>
		</div>
		

		<%@ include file = "budgetform.jsp" %>

		<button class="btn btn-info submission" id="submitSetup">Submit</button>
	
	</div>


	<script type="text/javascript" src="static/js/jozsef.js"></script>
	
</body>
</html>

