<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page not found</title>
<link rel = "stylesheet" type="text/css" href="static/myStyle.css"/>
</head>
<body>
<div class="container">
    <div class="row">
    <div class="error-template">
	    <h1>Oops!</h1>
	    <h2>404 Not Found</h2>
	    <div class="error-details">
		Sorry, an error has occured, Requested page not found!<br>
		<?php echo CHtml::encode($message); ?>
	    </div>
	    <div class="error-actions">
	    <form action="/BudgetBunny/home">
		  <input type="submit" class="btn btn-info" value="Home Page">
		</form> 
	    </div>
	</div>
    </div>
</div>
</body>
</html>