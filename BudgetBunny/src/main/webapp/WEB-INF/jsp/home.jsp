<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>


<%@ include file="imports.jsp"%>
<link rel="stylesheet" href="static/css/jozsef.css">


</head>
<body>

	<div id='home-div'>
		<%@ include file="_navbar.jsp"%>

		<%@page import="com.revature.bean.Category"%>


		<c:forEach items='${cats}' var='cat'>
			<%@ include file="budgetdisplay.jsp"%>
		</c:forEach>

	</div>

	<%@ include file="newTransaction.jsp"%>


	<script type="text/javascript" src="static/js/jozsef.js"></script>


</body>
</html>