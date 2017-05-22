<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./static/css/login.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<title>Login</title>
</head>
<body>

<div class="container login">

<div class="row" style="margin-top:20px">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
    	<form role="form">
			<fieldset>
				<h2>Budget Bunny</h2>
				<hr class="colorgraph">
				<div class="form-group">
                    <input type="username" name="username" id="username" class="form-control input-lg" placeholder="Username" required="required">
				</div>
				<div class="form-group">
                    <input type="password" name="password" id="password" class="form-control input-lg" placeholder="Password" required="required">
				</div>
				<hr class="colorgraph">
				<div class="alert alert-danger" role="alert" id="error-msg" hidden>
				  Error Message will go here.
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
                        <button type="submit" id="login" class="btn btn-lg btn-default btn-block" >Log In</button>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<a href="create" class="btn btn-lg btn-default btn-block">Create an Account</a>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>
	
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="static/js/login.js"></script>
</body>
</html>