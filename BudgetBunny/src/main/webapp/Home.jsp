<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
	

</head>
<body>
	<div>
	<%@page import="com.revature.bean.Category" %>
	
	
	<c:set var="cats" value='<%=new Category[]{new Category(0, "Daily", 500, 200, null), new Category(1, "Weekly", 1000, 200, null), new Category(2, "Monthly", 50, 5, null), new Category(3, "Annually", 200, 190, null)}%>' scope="application" />

	

	<c:forEach items='${cats}' var='cat'>
		<%@ include file = "BudgetDisplay.jsp" %>
	</c:forEach>
	</div>
</body>
</html>