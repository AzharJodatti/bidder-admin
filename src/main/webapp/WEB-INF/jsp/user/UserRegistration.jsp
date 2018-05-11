<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript" src="<c:url value="./js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="./js/bootstrap.min.js"/>"></script>
<link href="<c:url value="./css/style.css"/>" rel="stylesheet">
<link href="<c:url value="./css/bootstrap.min.css"/>" rel="stylesheet">

<title>User Registration</title>

<script type="text/javascript">
	
	function registerUser() {
		
		var userName = $("#username").val();
		var password = $("#password").val();
		var name = $("#nameTxt").val();
		var emailId = $("#emailTxt").val();
		var userRole = $("#userRole").val();
		
		var errorMessage = [];
		var flag = true;
		
		if(userName == "" || userName == null) {
			errorMessage.push("\n User name should not be blank");
		}
			
		if(userName.length != 10) {
			errorMessage.push("\n User name should be only 10 digits");
		}
		
		if(password == "" || password == null ) {
			errorMessage.push("\n Password field should not be blank");
		}
		
		if(errorMessage.length != 0) {
			flag = false;
		}
		
		if(flag) {
			$.ajax({
				type : "post",
				url : "userRegistration",
				data:{
					userName:userName,
					password:password,
					name:name,
					emailId:emailId,
					userRole:userRole,
				},		
				success : function(data) {
					if(data=='ERROR') {
						alert("Error")
					} else {
						alert(data);
						window.location.href="index.jsp";
					}
				},
				error: function(e) {
					alert("In error Block : " +e);
				}
			});
		} else {
			alert(errorMessage.toString())
		}
	}
	
	
	function back() {
		document.getElementById("registrationform").action = "index.jsp";
		document.forms["registrationform"].submit();
	}	
</script>
</head>

<body>
		<center>
		<H1>User Registration</H1>
		<c:if test="${not empty message}">${message}</c:if>
 			
	<div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form name="registrationform" id="registrationform" action="userRegistration.html" method="POST">
									
									<div class="form-group">
										<input type="number" id="username" name="username" tabindex='1' value="" placeholder="Username" class='loginPassTxtBox form-control' />
									</div>
									
									<div class="form-group">
										<input type="password" id="password" name="password" tabindex='2' value="" class='loginPassTxtBox form-control' onkeydown="if (event.keyCode == 13) document.getElementById('signup_btn').click()" placeholder="Password" />
									</div>
									
									<div class="form-group">
										<input type="text" id="nameTxt" name="nameTxt" tabindex='3' value="" class='loginPassTxtBox form-control' placeholder="Name" />
									</div>
									
									<div class="form-group">
										<input type="text" id="emailTxt" name="emailTxt" tabindex='4' value="" class='loginPassTxtBox form-control' placeholder="Emial Id" />
									</div>
									
									<div class="form-group">
										<select id="userRole" name="userRole" class="loginPassTxtBox form-control" tabindex='5' >
											<option value=""> -- User Role--</option>
											<option value="admin"> Admin </option>
											<option value="user"> User </option>
											<option value="agent"> Agent </option> 
											<option value="bookie"> Bookie </option> 
										</select>
									</div>
									
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<!-- <input type="submit" class="submitbutton btn form-control btn btn-login" tabindex='6' name="signup_btn" id="signup_btn" value="Register" /> -->
												<input type="button" class="form-control btn btn-login" tabindex='6' name="signup_btn" id="signup_btn" value="Register" onclick="registerUser()" />
												<input type="button" class="form-control btn btn-login" tabindex='7' name="back_btn" id="back_btn" value="Back" onclick="back()" />
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="#" tabindex="5" class="forgot-password">Forgot Password?</a>
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	</center>
</body>
</html>