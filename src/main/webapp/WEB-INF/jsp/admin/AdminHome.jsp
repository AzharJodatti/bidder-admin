<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>
	<script type="text/javascript" src="<c:url value="./js/jquery.js"/>"></script>
	<script type="text/javascript" src="<c:url value="./js/bootstrap.min.js"/>"></script>
	<link href="<c:url value="./css/style.css" />" rel="stylesheet">
	<link href="<c:url value="./css/bootstrap.min.css" />" rel="stylesheet">
</head>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<html>

<title>Admin Home</title>

<script type="text/javascript">

function saveCoin() {
	var coinValue = "";
	coinValue = $("#coinTxtbx").val();
	var errorMessage = [];
	var flag = true;
	
	if(coinValue != "") {
		var coinValueTemp = coinValue.indexOf(".");
		if(coinValueTemp != -1) {
			errorMessage.push("\nDecimal Values not allowed. Value must be numeric.");
		}
	}
	
	if(coinValue == "" || coinValue == null) {
		errorMessage.push("Please Enter coin value.");
	}

	if(errorMessage.length != 0) {
		flag = false;
	}
	
	if(flag) {
		$.ajax({
				type : "post",
				url : "saveCoin",
				data:{
					coinValue:coinValue
				},		
				success : function(data) {
					if(data=='ERROR') {
						alert("Error")
					} else {
						alert(data);
						window.location.href="adminPannel.html";
					}
				},
				error: function(e) {
					alert("In error Block : " +e);
				}
			});
	} else {
		alert(errorMessage.toString());
	}
}

function saveLuckyNumber() {
	var luckyNumberValue = "";
	var regionValue = "";
	luckyNumberValue = $("#luckyNumber_txtbx").val();
	regionValue= $("#region_select").val();
	var errorMessage = [];
	var flag = true;
	
	if(luckyNumberValue != "") {
		var luckyNumberValueTemp = luckyNumberValue.indexOf(".");
		if(luckyNumberValueTemp != -1) {
			errorMessage.push("\nDecimal Values not allowed. Value must be numeric.");
		}
	}
	
	if(regionValue == "" || regionValue == null) {
		errorMessage.push("Please select Region value.");
	}
	
	if(luckyNumberValue == "" || luckyNumberValue == null) {
		errorMessage.push("\nPlease enter lucky number");
	}
	
	if(errorMessage.length != 0) {
		flag = false;
	}
	
	if(flag) {
		$.ajax({
				type : "post",
				url : "saveLuckyNumber",
				data:{
					luckyNumberValue:luckyNumberValue,
					regionValue:regionValue
				},		
				success : function(data) {
					if(data=='ERROR') {
						alert("Error")
					} else {
						alert(data);
						window.location.href="adminPannel.html";
					}
				},
				error: function(e) {
					alert("In error Block : " +e);
				}
			});
	} else {
		alert(errorMessage.toString());
	}
}

function backFromCoin() {
	document.getElementById("coinFrm").action = "homePage.html";
	document.forms["coinFrm"].submit();
}	

function backFromLuckyNum() {
	document.getElementById("luckNumberFrm").action = "homePage.html";
	document.forms["luckNumberFrm"].submit();
}	
</script>

<body>
	<%
		/* if (session.getAttribute("userName") != null) {
			System.out.println(session.getAttribute("userName"));
		} */
	%>
	<center>${message}</center>
	<center> 
		<h2> Admin Activity </h2><br>
	</center>
		<div class="container">
	    	<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<div class="panel panel-login">
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<form name="coinFrm" id="coinFrm" action="" method="POST">
										
										<div class="form-group"> <h2> Create Coin </h2> </div>
										
										<div class="form-group">
											<input type="number" id="coinTxtbx" name="coinTxtbx" tabindex='1' value="" placeholder="Coin Value" class='loginPassTxtBox form-control' />
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-sm-6 col-sm-offset-3">
													<input type="button" class="submitbutton btn form-control btn btn-login" tabindex='3' name="login_btn" id="login_btn" value="Save Coin" onclick="saveCoin();" />
													<input type="button" class="form-control btn btn-login" tabindex='4' name="back_btn" id="back_btn" value="Back" onclick="backFromCoin()" />
												</div>
											</div>
										</div>
									</form>
									
									<form name="luckNumberFrm" id="luckNumberFrm" action="" method="POST">
										
										<div class="form-group"> <h2> Create Lucky Number </h2> </div> 
										<br>
										
										<div class="form-group"></>
											<select id="region_select" class="submitbutton btn form-control btn btn-login">
												<option value=""> -- Select Region -- </option>
												<option value="mumbai"> Mumbai </option>
												<option value="kalyan"> Kalyan </option>
											</select>
										</div>
										
										<div class="form-group">
											<input type="number" id="luckyNumber_txtbx" name="luckyNumber_txtbx" tabindex='1' value="" placeholder="Lucky Number" class='loginPassTxtBox form-control' />
										</div>
										<div class="form-group">
											<div class="row">
												<div class="col-sm-6 col-sm-offset-3">
													<input type="button" class="submitbutton btn form-control btn btn-login" tabindex='3' name="login_btn" id="login_btn" value="Save Lucky Number" onclick="saveLuckyNumber();" />
													<input type="button" class="form-control btn btn-login" tabindex='4' name="back_btn" id="back_btn" value="Back" onclick="backFromLuckyNum()" />
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
	</form>
	
</body>
</html>