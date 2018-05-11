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
	<title>Agent Recharge</title>
	
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

  function saveAgentRecharge() {
	var userId = $("#selectUser").val();
	var rechargeBy = $("#hiddenUserId").val();
	var coinAmount = $("#coinAmountTxt").val();
	
	var errorMessage = [];
	var flag = true;
	
	if(userId == undefined || userId == null || userId == "") {
		errorMessage.push("\nPlease Select User");
	}
		
	if(coinAmount == undefined || coinAmount == "" || coinAmount== null ) {
		errorMessage.push("\nPlease Enter recharge Amount.");
	}
	
	if(coinAmount != "") {
		var coinAmountTemp = coinAmount.indexOf(".");
		if(coinAmountTemp != -1) {
			errorMessage.push("\nDecimal Values not allowed. Value must be numeric.");
		}
	}
	
	if(errorMessage.length != 0) {
		flag = false;
	}
	
	if(flag) {
		$.ajax({
			type : "post",
			url : "saveRechargeDetails",
			data:{
				userId:userId,
				rechargeBy:rechargeBy,
				coinAmount:coinAmount
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
		alert(errorMessage.toString())
	}
}


function back() {
	document.getElementById("agentRechargeform").action = "homePage.html";
	document.forms["agentRechargeform"].submit();
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

  <%String username = "",userRole="",userId = "";
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
    <a class="navbar-brand" href="createAgent.html">Agent</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Create User">
          <a class="nav-link" href="createAgent.html">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Create Agent</span>
          </a>
        </li>
        
        <c:if test="${userRole == 'agent'}">
	        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Agent Recharge">
	          <a class="nav-link" href="agentRecharge.html">
	            <i class="fa fa-fw fa-dashboard"></i>
	            <span class="nav-link-text">Agent Recharge</span>
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
          <a href="agentRecharge.html">Recharge</a>
        </li>
        <li class="breadcrumb-item active">Save Recharge Details</li>
      </ol>
        
      <!-- form tag code start-->
      	
      	<form name="agentRechargeform" id="agentRechargeform" action="" method="POST">
          <div class="card mb-3">
          <div class="card-header"> 
          		Recharge Details
          		<div id="countdowntimer" class="float-right"><span class="style h4 text-success" id="hm_timer"></span></div>
          </div>
	          <div class="card-body">
		         
		        <div class="form-group">
 		         <div class="form-group">
		         	<div class="form-row">
			          	<div class="col-md-6">
							<label for="selectUserLbl"> Select User</label>
							<select id="selectUser" name="selectUser" class="form-control">
								<option value=""> -- Select User--</option>
								<c:if test="${not empty usersDetailsList}">
									<c:forEach items="${usersDetailsList}" var="usersDetails" varStatus="loopCounter">
										<option value="${usersDetails.userLoginId}">${usersDetails.name}</option>
									</c:forEach>
								</c:if>
							</select>
							<label for="coinAmountLbl"> Coin Amount </label>
							<input class="form-control" id="coinAmountTxt" name="coinAmountTxt" aria-describedby="nameHelp" value="" placeholder="Enter Coin Amount"/>			                
							<input type="hidden" id="hiddenUserId" name="hiddenUserId" value="${userId}"/>
		              	</div>
		         	</div>
		         </div> 
		         
		         <div class="form-group"> 
		          <div class="form-row">
	      			<div class="col-md-4">
	      				<input type="button" class="btn btn-primary btn-block" name="saveAgentBtn" id="saveAgentBtn" value="Save" onclick="saveAgentRecharge();" />
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