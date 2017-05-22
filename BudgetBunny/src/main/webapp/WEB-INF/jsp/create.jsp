<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./static/css/login.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<title>Create Account</title>
</head>
<body>

<div class="container login">

<div class="row" style="margin-top:20px">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
    	<div>
			<fieldset>
				<h2>Create an Account</h2>
				<hr class="colorgraph">
				<div class="form-group">
                    <input type="text" id="name" class="form-control input-lg" placeholder = "Name" required="required">
				</div>
				<div class="form-group">
                    <input type="username" id="username" class="form-control input-lg" placeholder="Username" required="required">
				</div>
				<div class="form-group">
                    <input type="password" id="password" class="form-control input-lg" placeholder="Password" required="required">
				</div>
				<hr class="colorgraph">
				<div class="alert alert-success" role="alert" id="success-msg" hidden>
				  Success Message will go here.
				</div>
				<div class="alert alert-danger" role="alert" id="error-msg" hidden>
				  Error Message will go here.
				</div>
				<div class="form-group text-center">
					<div>
						<button type="submit" id="create" class="btn btn-info">Create Account</button>
					</div>
					<br>
					<div class="text">
						<p class="text">Already Registered? <a href="login" data-toggle="modal" data-target="#login">Login here</a></p>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</div>
	
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="static/js/create.js"></script>
</body>
</html>