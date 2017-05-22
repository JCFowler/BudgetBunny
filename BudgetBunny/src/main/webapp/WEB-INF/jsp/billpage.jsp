<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<%@ include file = "imports.jsp" %>
		<link rel="stylesheet" href="static/css/jozsef.css">

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bills</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
</head>
<body id="setup-body">

	<%@ include file = "_navbar.jsp" %>

		<div id="table-container">
		
		<h3 class="no-top" id='totalSpent' hidden>${user.budget.totalSpent}</h3>
		<h3 class="no-top" id='totalBudget' hidden>${user.budget.totalBudget}</h3>
		
			<c:set var="headerMsg" value="Add Your Bills"></c:set>
			<c:set var="type" value="withdraw"></c:set>
			<%@ include file = "systematictransactionform.jsp" %>

		<button class="btn btn-info submission submit-bill" style='display: none'>Submit</button>

		</div>

	<script type="text/javascript" src="static/js/jozsef.js"></script>
	
	<c:forEach items="${billList}" var="elem">
		<script>createAndFillWithdraw('${elem.name}', '${-1 * elem.cost}', '${elem.chargeId}')</script>		
	</c:forEach>
</body>
</html>