 <%@page import="Resources.TariffPlan"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/TariffPlan.css">
<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/TariffPlan.js"></script>
<meta charset="ISO-8859-1">
<title>TariffPlans </title>
</head>
<body style="   ">
	<div class="container">


  		<div class="row">
				<h1 style="padding: 10px; background-color: #008AD9; font-weight: bold;" >Tariff-Plan Management</h1>
				<form id="formPlan" name="formPlan">
					<label for="tariffPlanId">TariffPlan ID:</label> 
					<input id="tariffPlanId" name="tariffPlanId" type="text" class="form-control form-control-sm"> 
					<br>
					<label for="tariffBlock">Tariff block:</label> 
					<input id="tariffBlock" name="tariffBlock" type="text" class="form-control form-control-sm"> 
					<br> 
					<label for="unitRate">Unit Rate:</label> 
					<input id="unitRate" name="unitRate" type="text" class="form-control form-control-sm"> 
					<br> 
					<label for="fixedCharge">Fixed Charge:</label> 
					<input id="fixedCharge" name="fixedCharge" type="text" class="form-control form-control-sm"> 
					<br> 
			    	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidPlanIDSave" name="hidPlanIDSave" value="">
				</form>
					<br>
					<br>
					<br>
		    	<div id="alertSuccess" class="alert alert-success" style="font-weight: bold"></div>
				<div id="alertError" class="alert alert-danger" style="font-weight: bold"></div>
    </div>
    <br><br><br><br>
    <div class="row">
 
				<div id="divTariffPlanGrid">
					<%
					TariffPlan Tplan = new TariffPlan();
			out.print(Tplan.GetTariffPlan());
		%>
				</div>
    </div>
  
  </div>
		        

</body>
</html>