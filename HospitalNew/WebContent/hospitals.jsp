<%@page import="model.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html>
<html>
<head>
	<script src="Components/jquery-3.2.1.min.js" ></script>
	<script src="Components/hospitals.js"></script>
	<link rel="stylesheet" href="Views/bootstrap.min.css"> 
	<meta charset="ISO-8859-1">
	<title>Hospital Management</title>
</head>
<body>
	<div class="container">
	<div Class="row">
	<div class="col">
	 <h1>Hospital management</h1>
	 
 	<form id="formHospital" name="formHospital" > 
  		Hospital no: 
  		<input id="hospitalNo" name="hospitalNo" type="text" class = "form-control form-control-sm"><br>
  		
 		Hospital name:
 		 <input id="hospitalName" name="hospitalName" type="text" class = "form-control form-control-sm"><br> 
 		 
  		Hospital address:
  		 <input id="hospitalAddress" name="hospitalAddress" type="text" class = "form-control form-control-sm"><br>
  		 
  		Hospital phone: 
  		<input id="hospitalPhone" name="hospitalPhone" type="text" class = "form-control form-control-sm"><br>
  		
  		Hospital email:
  		 <input id="hospitalEmail" name="hospitalEmail" type="text" class = "form-control form-control-sm"><br>
  		 
  		Hospital password:
  		 <input id="hospitalPassword" name="hospitalPassword" type="text" class = "form-control form-control-sm"><br>
  		 
 		<input id="btnSave" name="btnSave" type="button" value="Save" class = "btn btn-primary">
 		<input type="hidden" id="hidHospitalIDSave" name="hidHospitalIDSave" value="">
 		
 		
 		
 	</form>
 	<div id="alertSuccess" class="alert alert-success"></div>
 	<div id="alertError" class="alert alert-danger"></div>
 	
 <br>
 <div id="divHospitalsGrid">
 	  <%
 		    Hospital hospitalObjectect = new Hospital();  
 	 	 	out.print(hospitalObjectect.readHospitals());
 	   %>
 </div>
             </div>
       </div>
 </div>
</body>
</html>

