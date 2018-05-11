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
	<title>Lucky Number</title>
	
	
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
	
	<!-- Bootstrap core JavaScript-->
	<script src="./vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script src="./vendor/jquery-easing/jquery.easing.min.js"></script>
	<!-- Page level plugin JavaScript-->
	<script src="./vendor/chart.js/Chart.min.js"></script>
	<script src="./vendor/datatables/jquery.dataTables.js"></script>
	<script src="./vendor/datatables/dataTables.bootstrap4.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="./js/sb-admin.min.js"></script>
	
	<!-- Countedown timer -->
	<script src="./vendor/countdown/jquery-2.0.3.js"></script>
	<!-- <script src="./vendor/countdown/jquery.countdownTimer.js"></script> -->
	<link rel="stylesheet" type="text/css" href="./vendor/countdown/jquery.countdownTimer.css" />

</head>

<script type="text/javascript"> 

$(document).ready(function() {
	var script = document.createElement('script');
	script.src = "./vendor/countdown/jquery.countdownTimer.js";
	document.head.appendChild(script);
			
	script.onload = function () {
		/* calculateTime(); */
		getTimerDetails();
	}; 
});

function saveLuckyNumber() {
	var openValue = $("#openValueTxt").val();
	var closeValue = $("#closeValueTxt").val();
	var regionValue = $("#region_select").val();
	var errorMessage = [];
	var flag = true;
	
	if(openValue != "" || closeValue != "") {
		var openValueTemp = openValue.indexOf(".");
		var closeValueTemp = closeValue.indexOf(".");
		if(openValueTemp != -1 || closeValueTemp != -1) {
			errorMessage.push("\nDecimal Values not allowed. Value must be numeric.");
		}
	}
	
	if(regionValue == "" || regionValue == null) {
		errorMessage.push("\nPlease select Region value.");
	}
	
	if(openValue == "" || openValue == null) {
		errorMessage.push("\nPlease enter open value");
	}
	
	/* if(closeValue == "" || closeValue == null) {
		errorMessage.push("\nPlease enter close value");
	} */
	if(closeValue == "" || closeValue == null) {
		closeValue = null;
	}
	if(errorMessage.length != 0) {
		flag = false;
	}
	
	if(flag) {
		$.ajax({
				type : "post",
				url : "saveLuckyNumber",
				data:{
					openValue:openValue,
					closeValue:closeValue,
					regionValue:regionValue
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

function checkTime(userId) {
	$.ajax({
		type : "post",
		url : "checkTimeDetails",
		data:{
			userId:userId
		},		
		success : function(data) {
			if(data=='ERROR') {
				alert("Error")
			} else {
				if(data == true) {
					alert("Time alreay Set. U can not modify");
					window.location.href="homePage.html";
				} else {
					saveTimeDetails();
				}
			}
		},
		error: function(e) {
			alert("In error Block : " +e);
		}
	});
}

function saveTimeDetails() {
	
	var openTime = $("#openTimeTxt").val();
	var closeTime = $("#closeTimeTxt").val();
	var region = $("#regionInputSelect").val();
	var errorMessage = [];
	var flag = true;
	
	if(region == "" || region == null) {
		errorMessage.push("\nPlease select Region value.");
	}
	
	if(openTime == "" || openTime == null) {
		errorMessage.push("\nPlease Enter Open Time.");
	}
	
	if(closeTime == "" || closeTime == null) {
		errorMessage.push("\nPlease Enter close Time.");
	}
	
	if(errorMessage.length != 0) {
		flag = false;
	}
	
	if(flag) {
		$.ajax({
				type : "post",
				url : "saveTime",
				data:{
					openTime:openTime,
					closeTime:closeTime,
					region:region
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



function backFromLuckyNum() {
	document.getElementById("luckNumberFrm").action = "homePage.html";
	document.forms["luckNumberFrm"].submit();
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

  <%String username = "", userId="";
		if (session!= null && session.getAttribute("userName") != null) {
			username = session.getAttribute("userName").toString();
			userId = session.getAttribute("userId").toString();
		} else {
			System.out.println("In else block : ");
		}
	%>
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="luckyNumber.html">Lucky Number</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="luckyNumber.html">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Create Lucky Number</span>
          </a>
        </li>
        
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
          <a href="luckyNumber.html">Lucky Number</a>
        </li>
        <li class="breadcrumb-item active">Save Lucky Number</li>
      </ol>
        
      <!-- form tag code start-->
      	
      	<form name="luckNumberFrm" id="luckNumberFrm" action="" method="POST">
          <div class="card mb-3">
          <div class="card-header"> 
          		Lucky Number Details
          		<div id="countdowntimer" class="float-right"><span class="style h4 text-success" id="hm_timer"></span></div>
          </div>
	          <div class="card-body">
		          <%-- <div class="form-group">
		          	<img src="<c:url value="./images/timer.png"/>" title="Set Open / Close Time" data-toggle="modal" data-target="#timerModal" style="width:70px;height:70px;"/>
		          </div> --%>
		          <div id="countdown"></div>
		          <div class="form-group">
		            <div class="form-row">
		              <div class="col-md-6">
		                <label for="exampleInputName">Open</label>
		                <input class="form-control" id="openValueTxt" name= "openValueTxt" type="number" aria-describedby="nameHelp" placeholder="Enter Open value">
		              </div>
		              <div class="col-md-6">
		                <label for="exampleInputLastName">Close</label>
		                <input class="form-control" id="closeValueTxt" name="closeValueTxt"  type="number" aria-describedby="nameHelp" placeholder="Enter Close value">
		              </div>
		            </div>
		          </div>
		         
		         <div class="form-group">
		         	<div class="form-row">
			          	<div class="col-md-6">
		                	<label for="regionInputSelect">Select Region</label>
								<select id="region_select" class="form-control">
									<option value=""> -- Select Region -- </option>
									<option value="mumbai"> Mumbai </option>
									<option value="kalyan"> Kalyan </option>
								</select>		                
		              	</div>
		         	</div>
		         </div>
		         <div class="form-group"> 
		          <div class="form-row">
	      			<div class="col-md-4">
	      				<input type="button" class="btn btn-primary btn-block" name="login_btn" id="login_btn" value="Save" onclick="saveLuckyNumber();" />
	      				<!-- <a class="btn btn-primary btn-block" href="#">Save</a> -->
	      			</div>    
	      			<div class="col-md-4">
	      				<input type="button" class="btn btn-primary btn-block" name="continueBtn" id="continueBtn" value="Continue" onclick="#" />
	      				<!-- <a class="btn btn-primary btn-block" href="#">Continue</a> -->
	      			</div>
	      			<div class="col-md-4">
	      				<input type="button" class="btn btn-primary btn-block" name="backBtn" id="backBtn" value="Back" onclick="backFromLuckyNum()" />
	      				<!-- <a class="btn btn-primary btn-block" href="#">Continue</a> -->
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
    	
     <!-- Timer Model-->
    <div class="modal fade" id="timerModal" tabindex="-1" role="dialog" aria-labelledby="timerModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="tModalLabel">Enter Open & Close Time</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <form action="" id="timerForm" id="timerForm" method="POST">
	          <div class="modal-body">
	          	<label for="regionInputSelectLable">Select Region</label>
					<select id="regionInputSelect" class="form-control">
						<option value=""> -- Select Region -- </option>
						<option value="mumbai"> Mumbai </option>
						<option value="kalyan"> Kalyan </option>
					</select>
					
					<label for="forOpenTime">Open Time</label>						
					<input class="form-control" id="openTimeTxt" name="openTimeTxt" type="time" aria-describedby="nameHelp" placeholder="Enter Open Time" />
					
					<label for="forOpenTime">Close Time</label>
					<input class="form-control" id="closeTimeTxt" name="closeTimeTxt" type="time" aria-describedby="nameHelp" placeholder="Enter Close Time" />
					
			  </div>
		  </form>	
          <!-- <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div> -->
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <!-- <a class="btn btn-primary" onclick="saveTimeDetails()">Save</a> -->
            <a class="btn btn-primary" onclick="checkTime(<%=userId %>)">Save</a>
          </div>
        </div>
      </div>
    </div>
    
  </div>
</body>
</html>