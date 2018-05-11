<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Home Page</title>

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
<script src="./vendor/jquery/jquery.min.js"></script>
<script src="./vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="./vendor/jquery-easing/jquery.easing.min.js"></script>
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
		
		$('#password').bind('copy paste cut', function(e) {
			e.preventDefault();
		});
	});
	history.forward();

	function logout() {
		document.getElementById("homeForm").action = "logout.html";
		document.forms["homeForm"].submit();
	}

	function adminPannel() {
		document.getElementById("homeForm").action = "adminPannel.html";
		document.forms["homeForm"].submit();
	}
	
	function createBookie() {
		document.getElementById("homeForm").action = "bookieHome.html";
		document.forms["homeForm"].submit();
	}
	
	function timerAlert() {
		alert("You are not autorized person to modify the timer.");
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
		$('#hm_timer').countdowntimer({
	        hours : hours,
	        minutes :minutes,
	        size : "md"
	    });
	} else {
		$("#hm_timer").text("Timeout");
		$("#hm_timer").addClass("style colorDefinition size_md");
	}
};

/*save time details Start*/
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
/*save time details end*/
</script>


<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<center>${message}</center>
  <%String username = "", userRole = "", userId = "";
		if (session!= null && session.getAttribute("userName") != null) {
			username = session.getAttribute("userName").toString();
			userRole = session.getAttribute("userRole").toString();
			userId = session.getAttribute("userId").toString();
		} else {
			System.out.println("In else block : Session is empty or null");
		}
	%>
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="homePage.html">Admin Dashboard</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="homePage.html">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Dashboard</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
          <a class="nav-link" href="coins.html">
            <i class="fa fa-fw fa-area-chart"></i>
            <span class="nav-link-text">Coins</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
          <a class="nav-link" href="luckyNumber.html">
            <i class="fa fa-fw fa-area-chart"></i>
            <span class="nav-link-text">Lucky Number</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="createBookie.html">
            <i class="fa fa-fw fa-file"></i>
            <span class="nav-link-text">Bookie</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="createAgent.html">
            <i class="fa fa-fw fa-file"></i>
            <span class="nav-link-text">Agent</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="createUser.html">
            <i class="fa fa-fw fa-file"></i>
            <span class="nav-link-text">User</span>
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
            <!-- <div class="dropdown-divider"></div>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">
              <strong>John Doe</strong>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">I've sent the final files over to you for review. When you're able to sign off of them let me know and we can discuss distribution.</div>
            </a> -->
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
           <!--  <div class="dropdown-divider"></div> -->
            <!-- <a class="dropdown-item" href="#">
              <span class="text-success">
                <strong>
                  <i class="fa fa-long-arrow-up fa-fw"></i>Status Update</strong>
              </span>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">This is an automated server response message. All systems are online.</div>
            </a> -->
            <!-- <div class="dropdown-divider"></div> -->
            <div class="dropdown-divider"></div>
          </div>
        </li>
  <!--       <li class="nav-item">
          <form class="form-inline my-2 my-lg-0 mr-lg-2">
            <div class="input-group">
              <input class="form-control" type="text" placeholder="Search for...">
              <span class="input-group-append">
                <button class="btn btn-primary" type="button">
                  <i class="fa fa-search"></i>
                </button>
              </span>
            </div>
          </form>
        </li> -->
        
        <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#">
            <%=username%> (<%=userRole %>)
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
          <a href="homePage.html">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">My Dashboard</li>
      </ol>
      
      <ol class="float-right breadcrumb">
    	<li>
	        <c:if test="${not empty userRole && userRole == 'admin'}">
	        	<img src="./images/timer.png" title="Set Open / Close Time" data-toggle="modal" data-target="#timerModal" style="width:70px;height:70px;">
	        </c:if>
	        <c:if test="${not empty userRole && userRole != 'admin'}">
				<img src="./images/timer.png" title="Set Open / Close Time" data-toggle="modal" data-target="#" style="width:70px;height:70px;" onclick="timerAlert()">	        	
	        </c:if>
    	</li>
	  </ol>
      <!-- Write form tag code -->
      
       <!-- Icon Cards-->
      <div class="row">
<!--          <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-warning o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-list"></i>
              </div>
              <div class="mr-5">
              	Down Time
              </div>
            </div>
            <span class="float-left">
            	<div id="countdowntimer">
	           		<span id="hm_timer"></span>
              	</div>
              </span>
          </div>
        </div> -->
        
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-primary o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-comments"></i>
              </div>
              <!-- <div class="mr-5">10 Bookie!</div> -->
              <div class="mr-5">
              	<c:if test="${not empty bookieDetailList}">${fn:length(bookieDetailList)} Bookies! </c:if>	
              </div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        
		<div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-danger o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-support"></i>
              </div>
              <div class="mr-5">
              	<c:if test="${not empty agentDetailsList}">${fn:length(agentDetailsList)} Agents! </c:if>
              </div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-success o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-shopping-cart"></i>
              </div>
              <div class="mr-5">
              	<c:if test="${not empty usersDetailsList}">${fn:length(usersDetailsList)} Users! </c:if>
              </div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
         <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-warning o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-list"></i>
              </div>
              <div class="mr-5">
              	Down Time
              </div>
            </div>
            <span class="float-right">
            	<div id="countdowntimer">
	           		<span id="hm_timer"></span>
              	</div>
              </span>
          </div>
        </div>

        
      </div>
   <!-- /.container-fluid-->
	  </div>
    <!-- /.content-wrapper-->
   	</div>
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
              <span aria-hidden="true">X</span>
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
              <span aria-hidden="true">X</span>
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
    <!-- Bootstrap core JavaScript-->
    <script src="./vendor/jquery/jquery.min.js"></script>
    <script src="./vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="./vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="./vendor/chart.js/Chart.min.js"></script>
    <script src="./vendor/datatables/jquery.dataTables.js"></script>
    <script src="./vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="./js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="./js/sb-admin-datatables.min.js"></script>
    <script src="./js/sb-admin-charts.min.js"></script>
  </div>
</body>
</html>
