<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<%@ include file = "imports.jsp" %>
		<link rel="stylesheet" href="static/css/jozsef.css">
		

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Income</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
</head>
<body id="setup-body">

	<%@ include file = "_navbar.jsp" %>


	<div id='table-container'>

		<h3 class="no-top" id='totalSpent' hidden>${user.budget.totalSpent}</h3>
		<h3 class="no-top" id='totalBudget' hidden>${user.budget.totalBudget}</h3>
		

			<c:set var="headerMsg" value="Add Your Income"></c:set>
			<c:set var="type" value="deposit"></c:set>
			<%@ include file = "systematictransactionform.jsp" %>
			
	</div>
			<button class="btn btn-info submission submit-income" style='display: none'>Submit</button>



	<script type="text/javascript" src="static/js/jozsef.js"></script>
	<c:forEach items="${incomeList}" var="elem">
		<script>createAndFillDeposit('${elem.name}', '${elem.cost}', '${elem.chargeId}')</script>		
	</c:forEach>
	
	<c:set var="total" value="0"/>
	<c:forEach items="${user.budget.recurringCharge}" var="charge">
		<c:if test="${charge.cost > 0}">
		<c:set var="total" value="${total = total + charge.cost}"/>
		</c:if>
	</c:forEach>
	<p hidden id='total' value='${total}'></p>
</body>
</html>