<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<title>Login Page</title>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap core JavaScript-->
<script src="./vendor/jquery/jquery.min.js"></script>
<script src="./vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="./vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Bootstrap core CSS-->
<link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom fonts for this template-->
<link href="./vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<!-- Custom styles for this template-->
<link href="./css/sb-admin.css" rel="stylesheet">

</head>

<script type="text/javascript">
	$(document).ready(function() {
		$('#password').bind('copy paste cut', function(e) {
			e.preventDefault();
		});
	});
	history.forward();
</script>

<script type="text/javascript">

function showErr() {
 	var foo = "Your login attempt has failed. <br> The user id or password may be incorrect!! <br> Please contact the administrator at your company for Help.";
	document.write("<font color='red'><p>" + foo +".</p></font>");
	
}

function checkVal(){
	var usr = "", pwd = "";
	usr = document.getElementById("username").value;
	pwd = document.getElementById("password").value;
	$("#username").css({
		borderColor : ''
	});

	$("#password").css({
		borderColor : ''
	});
	$("#usernamelbl").text("");
	$("#passwordlbl").text("");

	if (usr == null || usr == "") {
		$("#username").css({
			borderColor : 'Red'
		});
		$("#usernamelbl").text("User Id Required");
		$("#usernamelbl").css("color", "red");
		$("#username").focus();

		if (pwd == null || pwd == "") {
			$("#password").css({
				borderColor : 'Red'
			});
			$("#passwordlbl").text("Password Required");
			$("#passwordlbl").css("color", "red");
			$("#username").focus();
		}
		return false;
	}

	if (pwd == null || pwd == "") {

		if (usr == null || usr == "") {
			$("#username").css({
				borderColor : 'Red'
			});
			$("#usernamelbl").text("User Id Required");
			$("#usernamelbl").css("color", "red");
			$("#username").focus();
		}

		$("#password").css({
			borderColor : 'Red'
		});
		$("#passwordlbl").text("Password Required");
		$("#passwordlbl").css("color", "red");
		$("#password").focus();
		return false;
	}
	if ((pwd == "") && (usr == "")) {
		$("#username").focus();
	} else
		document.forms["loginform"].submit();
}

</script>

<body onload="" class="bg-dark">

<div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Welcome to Bidding Application 
      	<div class="dropdown-divider"></div>
      	<c:if test="${not empty message}">${message}</c:if>
      </div>
      
      <div class="card-body">
        <form name="loginform" id="loginform" action="login.html" method="POST">
          <div class="form-group">
            <label for="exampleInputEmail1">User Name</label>
            <input type="text" id="username" name="username" tabindex='1' value="" placeholder="Username" class="form-control" />
          </div>
          
          <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" id="password" name="password" tabindex='2' value="" class="form-control" placeholder="Password" onkeydown="if (event.keyCode == 13) document.getElementById('login_btn').click()" />
          </div>
          
         <!--  <div class="form-group">
            <div class="form-check">
              <label class="form-check-label">
                <input class="form-check-input" type="checkbox"> Remember Password</label>
            </div>
          </div> -->
          <!-- <a class="btn btn-primary btn-block" href="index.html">Login</a> -->
          <input type="button" class="btn btn-primary btn-block" tabindex='3' name="login_btn" id="login_btn" value="Login" onclick="checkVal();" />
        </form>
        
     <!--    <div class="text-center">
          <a href="signUp.html" id="register-form-link" class="d-block small mt-3">Register an Account</a>
        </div> -->
      </div>
    </div>
</div>
</body>
</html>
