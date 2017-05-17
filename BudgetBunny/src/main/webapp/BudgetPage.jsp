<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<%@ include file = "jspPieces/imports.jsp" %>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
</head>
<body id="setup-body">

	<div id='table-container'>
		
		<%@ include file = "jspPieces/BudgetForm.jsp" %>

		<button class="btn btn-info submission" id="submitSetup">Submit</button>
	
	</div>


	<script type="text/javascript" src="static/js/jozsef.js"></script>
	
	
</body>
</html>