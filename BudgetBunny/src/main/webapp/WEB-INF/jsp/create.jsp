<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./static/css/create.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Create Account</title>
</head>
<body>

<!--https://codepen.io/sevilayha/pen/IdGKH-->
<div class="container">
	<div class="row">
		<div class="col-sm-4 get_form_inner">
										<h4 class="__head">Create an Account</h4>
										<!-- ngIf: social_error_message != undefined -->
										<div class="text-center">
										<hr class="ortext2">
										<p></p>
										<form name="registerForm" method="post" action="">
											
                                            <div class="group">
												<input type="text" id="name" name="Name" required="required" placeholder = "Name">
                                                <span class="highlight"></span>
                                                <span class="bar"></span>
                                                
											</div>                                            
                                            <div class="group">
												<input type="text" id="username" name="Username" required="required" placeholder = "Username">
                                                <span class="highlight"></span>
                                                <span class="bar"></span>
                                                
											<div class="group">
												<input type="password" id="password" name="password" required="required" placeholder="Password"><input type="hidden" name="profile" value="Password" >	
                                                <span class="highlight"></span>
                                                <span class="bar"></span>
                                                
											</div>
													<p id="valid">test</p>
											<div class="form-group text-center">
												<div>
													<button type="submit" id="create" class="btn btn-info">Create Account</button>
												</div>
												<div class="container-fluid loading hidden">
													<label><span class="fa fa-refresh fa-spin"></span>
														 Creating...</label>
												</div>
											</div>
											<div class="text-right">
												<p class="text-right">Already Registered? <a href="javascript:void(0);" data-toggle="modal" data-target="#login">Login here</a></p>
											</div>
										</form>
									</div>
	</div>
</div>
</div>

</body>
</html>
<script src="static/js/create.js"></script>