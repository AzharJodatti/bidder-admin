<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Bookie Setellment</title>
	
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
	
	<!-- Page level plugin JavaScript-->
	<script src="./vendor/chart.js/Chart.min.js"></script>
	<script src="./vendor/datatables/jquery.dataTables.js"></script>
	<script src="./vendor/datatables/dataTables.bootstrap4.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="./js/sb-admin.min.js"></script>
	<!-- Custom scripts for this page-->
	<script src="./js/sb-admin-datatables.min.js"></script>
	<script src="./js/sb-admin-charts.min.js"></script>
	
	<!-- Countedown timer -->
	<script src="./vendor/countdown/jquery-2.0.3.js"></script>
	<script src="./vendor/countdown/jquery.countdownTimer.js"></script>
	<link rel="stylesheet" type="text/css" href="./vendor/countdown/jquery.countdownTimer.css" />
</head>

<script type="text/javascript"> 

$(document).ready(function() {
	var script = document.createElement('script');
	script.src = "./vendor/countdown/jquery.countdownTimer.js";
	document.head.appendChild(script);
			
	script.onload = function () {
		getTimerDetails();
	}; 
}); 

function transferAmountToUser() {
	var userId = $("#selectUser").val();
	var coinTransferValue = $("#coinTransferValue").val();
	var errorMessage = [];
	var flag = true;
	
	var amountArray = [];
	var splittedUserId = null;
	if(userId != "") {
		amountArray = userId.split("_");
		if(amountArray!=null)
			splittedUserId = amountArray[0];
	}
	/* if(coinTransferValue !="") {
		var coinTransferValueTemp = coinTransferValue.indexOf(".");
		if(coinTransferValueTemp != -1) {
			errorMessage.push("\nDecimal Values not allowed. Value must be numeric.");
		}
	} */
	
	if(userId == "" || userId == null) {
		errorMessage.push("\nPlease Select User");
	}
	
	/* if(coinTransferValue == "" || coinTransferValue == null) {
		errorMessage.push("\nPlease enter value for bookie coins");
	} */
	
	if(errorMessage.length != 0) {
		flag = false;
	}
	
	if(flag) {
		$.ajax({
				type : "post",
				url : "transferAmountToUser",
				data:{
					userId:splittedUserId,
					coinTransferValue:coinTransferValue
				},		
				success : function(data) {
					if(data=='ERROR') {
						alert("Error")
					} else {
						alert(data);
						window.location.href="homePage.html";
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

function populateUserAmount() {
	var amount = $("#selectUser").val();
	var amountArray = [];
	var amountValue ="";
	if(amount != "") {
		amountArray = amount.split("_");
		if(amountArray!=null)
			amountValue = amountArray[1];
	}
	$("#coinTransferValue").val(amountValue);
}

</script>

<script>

function getTimerDetails() {
	$.ajax({
		type : "post",
		url : "getTimeDetails",
		data:{	
			userId:null
		},		
		success : function(data) {
			if(data=='ERROR') {
				alert("Error")
			} else {
				if(data != null) {
					var openTime = data.openTime;
					var closeTime = data.closeTime;
					if(openTime == undefined)
						openTime = "00:00";
					if(closeTime == undefined)
						closeTime = "00:00";
					calculateTime(openTime,closeTime);
				} else {
					calculateTime("00:00","00:00");
				}
				
			}
		},
		error: function(e) {
			alert("In error Block : " +e);
		}
	});
}

function calculateTime(openTime,closeTime) { 
	var openTimeArray = openTime.split(":");
	var closeTimeArray = closeTime.split(":");
	var hours = closeTimeArray[0] - openTimeArray[0];
	var minutes = closeTimeArray[1] - openTimeArray[1];
	showTimer(hours,minutes);
}

function showTimer(hours, minutes) {
	if((hours != 00 || hours != 0) || (minutes != 00 || minutes != 0)) {
		$("#hm_timer").addClass("style h4   text-success");
		$('#hm_timer').countdowntimer({
	        hours : hours,
	        minutes :minutes,
	        size : "md"
	    });
	} else {
		$("#hm_timer").text("Timeout");
		$("#hm_timer").addClass("style h4   text-success");
	}
};

</script>


<body class="fixed-nav sticky-footer bg-dark" id="page-top">

  <%String username = "", userRole ="", userId = "";
		if (session!= null && session.getAttribute("userName") != null) {
			username = session.getAttribute("userName").toString();
			userRole = session.getAttribute("userRole").toString();
			userId = session.getAttribute("userId").toString();
		} else {
			System.out.println("In else block : ");
		}
	%>
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="setellment.html">Bookie - Setellment Page</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Create Bookie">
          <a class="nav-link" href="createBookie.html">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Create Bookie</span>
          </a>
        </li>
        <c:if test="${userRole == 'bookie'}">
	        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Create Bookie">
	          <a class="nav-link" href="transferToUser.html">
	            <i class="fa fa-fw fa-dashboard"></i>
	            <span class="nav-link-text">Transfer To User</span>
	          </a>
	        </li>
        </c:if>
        
        <c:if test="${userRole == 'bookie'}">
	        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Setellment">
	          <a class="nav-link" href="setellment.html">
	            <i class="fa fa-fw fa-dashboard"></i>
	            <span class="nav-link-text">Setellment</span>
	          </a>
	        </li>
        </c:if>
      </ul>
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle mr-lg-2" id="messagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-envelope"></i>
            <span class="d-lg-none">Messages
              <span class="badge badge-pill badge-primary">12 New</span>
            </span>
            <span class="indicator text-primary d-none d-lg-block">
              <i class="fa fa-fw fa-circle"></i>
            </span>
          </a>
          <div class="dropdown-menu" aria-labelledby="messagesDropdown">
            <h6 class="dropdown-header">New Messages:</h6>

            <div class="dropdown-divider"></div>
            <a class="dropdown-item small" href="#">View all messages</a>
          </div>
        </li>
        
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle mr-lg-2" id="alertsDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-bell"></i>
            <span class="d-lg-none">Alerts
              <span class="badge badge-pill badge-warning">6 New</span>
            </span>
            <span class="indicator text-warning d-none d-lg-block">
              <i class="fa fa-fw fa-circle"></i>
            </span>
          </a>
          <div class="dropdown-menu" aria-labelledby="alertsDropdown">
            <h6 class="dropdown-header">New Alerts:</h6>

            <div class="dropdown-divider"></div>
          </div>
        </li>
        
         <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#">
            <%=username%>
          </a>
        </li>
        
        
        <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
            <i class="fa fa-fw fa-sign-out"></i>Logout</a>
        </li>
      </ul>
    </div>
  </nav>
  
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="setellment.html">Bookie</a>
        </li>
        <li class="breadcrumb-item active">Setellment</li>
      </ol>
        
      	<form name="bookieFrm" id="bookieFrm" action="" method="POST">
          <div class="card mb-3">
          <div class="card-header"> 
          		Setellment Details
          		<div id="countdowntimer" class="float-right"><span class="style h4 text-success" id="hm_timer"></span></div>
          </div>
	          <div class="card-body">
		          <div class="form-group">
		            <div class="form-row">
		              <div class="col-md-6">
		                <label for="selectUserLbl"> Total Open </label>
						<span class="style h4 text-success size_md" id="total_open">  
							<c:if test="${not empty transactionsDetailsList}">
								<c:set var="totalOpenTypeCount" scope="page" value="0"/>
								<c:forEach items="${transactionsDetailsList}" var="transDetails" varStatus="transloopCounter">
									<c:if test="${transDetails.type eq 'open'}">  
										<c:set var="totalOpenTypeCount" scope="page" value="${totalOpenTypeCount+1}"/>
									</c:if>
								</c:forEach>
							</c:if>
							${totalOpenTypeCount}
						</span>
		              </div>
		               <div class="col-md-6">
		                <label for="selectUserLbl"> Todays Lucky Number</label>
						<span class="style h4 text-success  size_md" id="todays_lucky_number_txt">
							<c:set var="todaysLuckyNumber" scope="page" value="----"/>
							<c:if test="${not empty luckyNumberDetails}">
									<c:set var="todaysLuckyNumber" scope="page" value="${luckyNumberDetails.open}"/>
							</c:if>
							${todaysLuckyNumber}
						</span>
		              </div>
		            </div>
		            </div>
		            <div class="form-group">
		            <div class="form-row">
		              <div class="col-md-6">
		                <label for="selectUserLbl"> Select User</label>
						<select id="selectUser" name="selectUser" class="form-control" onchange="populateUserAmount()">
							<option value=""> -- Select User--</option>
							<c:if test="${not empty transactionsDetailsList}">
								<c:forEach items="${transactionsDetailsList}" var="transactionDetails" varStatus="loopCounter">
									<c:if test="${transactionDetails.type eq 'open' && todaysLuckyNumber eq transactionDetails.number}">  
										<c:set var="mapValue" value="${transactionsDTO.calculatedAmountMap[transactionDetails.playedBy]}"/>
										<option value="${transactionDetails.playedBy}_${transactionsDTO.calculatedAmountMap[transactionDetails.playedBy]}"> ${transactionDetails.playedBy} </option>
										<input type="hidden" name="calculatedAmt_${transactionDetails.playedBy}" val="${mapValue}" />
										
									</c:if>
								</c:forEach>
							</c:if>
						</select>
		              </div>
		              <div class="col-md-6">
		                <label for="exampleInputLastName">Coin Transfer Value</label>
		                <input class="form-control" id="coinTransferValue" name="coinTransferValue"  value="" type="number">
		              </div>
		            </div>
		          </div>
		          
		         <div class="form-group"> 
		          <div class="form-row">
	      			<div class="col-md-4">
	      				<input type="button" class="btn btn-primary btn-block" name="saveBookieBtn" id="saveBookieBtn" value="Save" onclick="transferAmountToUser();" />
	      			</div>    
	      			<div class="col-md-4">
	      				<input type="button" class="btn btn-primary btn-block" name="continueBtn" id="continueBtn" value="Continue" onclick="#" />
	      			</div>
	      			<div class="col-md-4">
	      				<input type="button" class="btn btn-primary btn-block" name="backBtn" id="backBtn" value="Back" onclick="back()" />
	      			</div>
		          </div>
	          	</div>
	          </div>
        </div>
        </form>
      	
	  <!-- form tag code end-->
	  </div><!-- /.container-fluid-->
   	</div><!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright © Invictusdynamics 2018</small>
        </div>
      </div>
    </footer>
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>
    
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="logout.html">Logout</a>
          </div>
        </div>
      </div>
    </div>
    
  </div>
</body>
</html>