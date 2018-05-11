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

<title>Bookie Home</title>

<script type="text/javascript">

	function saveBookieDetails() {
	var bookieName = $("#bookienameTxtbx").val();
	var coinTransferValue = $("#coinTransferValue").val();
	var commission = $("#commission").val();
	var errorMessage = [];
	var flag = true;
	
	if(commission !="" || coinTransferValue !="") {
		var commissionTemp = commission.indexOf(".");
		var coinTransferValueTemp = coinTransferValue.indexOf(".");
		if(commissionTemp != -1 || coinTransferValueTemp != -1) {
			errorMessage.push("\nDecimal Values not allowed. Value must be numeric.");
		}
	}
	
	if(bookieName == "" || bookieName == null) {
		errorMessage.push("\nPlease enter Bookie Name");
	}
	
	if(coinTransferValue == "" || coinTransferValue == null) {
		errorMessage.push("\nPlease enter value for bookie coins");
	}
	
	if(commission == "" || commission == null) {
		errorMessage.push("\nPlease enter value for commission");
	}
	
	if(errorMessage.length != 0) {
		flag = false;
	}
	
	if(flag) {
		$.ajax({
				type : "post",
				url : "saveBookie",
				data:{
					bookieName:bookieName,
					coinTransferValue:coinTransferValue,
					commission:commission
				},		
				success : function(data) {
					if(data=='ERROR') {
						alert("Error")
					} else {
						alert(data);
						window.location.href="bookieHome.html";
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

function back() {
	document.getElementById("bookieFrm").action = "homePage.html";
	document.forms["bookieFrm"].submit();
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
		<h2> Bookie Dashboard </h2><br>
	</center>
		<div class="container">
	    	<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<div class="panel panel-login">
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<form name="bookieFrm" id="bookieFrm" action="" method="POST">
										
										<div class="form-group"> <h2> Add Bookie Details</h2> </div>
										
										<div class="form-group">
											<input type="text" id="bookienameTxtbx" name="bookienameTxtbx" tabindex='1' value="" placeholder="Bookie Name" class='loginPassTxtBox form-control' />
										</div>
										<div class="form-group">
											<input type="number" id="coinTransferValue" name="coinTransferValue" tabindex='1' value="" placeholder="Coin Transfer Value" class='loginPassTxtBox form-control' />
										</div>
										<div class="form-group">
											<input type="number" id="commission" name="commission" tabindex='1' value="" placeholder="Commission" class='loginPassTxtBox form-control' />
										</div>
										
										<div class="form-group">
											<div class="row">
												<div class="col-sm-6 col-sm-offset-3">
													<input type="button" class="submitbutton btn form-control btn btn-login" tabindex='1' name="bookie_btn" id="bookie_btn" value="Save Bookie" onclick="saveBookieDetails();" />
													<input type="button" class="form-control btn btn-login" tabindex='1' name="back_btn" id="back_btn" value="Back" onclick="back()" />
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