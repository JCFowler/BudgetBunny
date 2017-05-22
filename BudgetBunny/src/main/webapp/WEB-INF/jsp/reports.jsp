<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	
	<%@ include file="imports.jsp"%>
	<script src="static/js/canvasjs/canvasjs.min.js"></script>
	
	<title>CanvasJS Example</title>
</head>
<body>
	<%@ include file="_navbar.jsp"%>
	
	<a id="chart">Show Chart</a>
		
	<div id="chartContainer" style="height: 800px; width: 100%;"></div>
	
	<script src="static/js/reports.js"></script>
</body>
</html>