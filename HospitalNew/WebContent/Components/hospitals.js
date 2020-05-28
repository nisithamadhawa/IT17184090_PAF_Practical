$(document).ready(function()
 { 
	$("#alertSuccess").hide();
	$("#alertError").hide();
	
 }); 
 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
 {  
	// Clear alerts--------------------- 
	$("#alertSuccess").text(""); 
	$("#alertSuccess").hide();
	$("#alertError").text(""); 
	$("#alertError").hide(); 
 
	// Form validation------------------- 
	var status = validateHospitalForm();
	if (status != true)
	{
		$("#alertError").text(status); 
		$("#alertError").show();  
		return; 
    }  
 
    // If valid------------------------- 
	var type = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT";  
	
	$.ajax( 
	{  
		url : "HospitalsAPI", 
		type : type,  
		data : $("#formHospital").serialize(),
		dataType : "text", 
		complete : function(response, status)
		{ 
			onHospitalSaveComplete(response.responseText, status); 
		} 
	});
 
 }); 

 function onHospitalSaveComplete(response, status) 
 {  	
	if (status == "success") 
		{  	
			var resultSet = JSON.parse(response);  
			
			if (resultSet.status.trim() == "success") 
			{  
				$("#alertSuccess").text("Successfully saved."); 
				$("#alertSuccess").show();  
				
				$("#divHospitalsGrid").html(resultSet.data); 
			} else if (resultSet.status.trim() == "error")
			{   
				$("#alertError").text(resultSet.data);  
				$("#alertError").show();   
			}  
			
    } else if (status == "error") 
	{  
		$("#alertError").text("Error while saving.");  
		$("#alertError").show(); 
	} else 
	{ 
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show(); 
	}  
		
     $("#hidHospitalIDSave").val("");  
	 $("#formHospital")[0].reset(); 
}  

//REMOVE------------------------------ 
$(document).on("click", ".btnRemove", function(event) 
{
	$.ajax( 
	{  
		url : "HospitalsAPI", 
		type : "DELETE", 
		data : "hospitalID=" + $(this).data("hospitalid"),
		dataType : "text",
		complete : function(response, status)  
	    {   
			onHospitalDeleteComplete(response.responseText, status); 
	    }  
     });
});  
 
function onHospitalDeleteComplete(response, status) 
{
	if (status == "success")
		{  
			var resultSet = JSON.parse(response);  
			
       if (resultSet.status.trim() == "success")  
		{   
	         $("#alertSuccess").text("Successfully deleted."); 
			 $("#alertSuccess").show();  
			 
              $("#divHospitalsGrid").html(resultSet.data); 
	    } else if (resultSet.status.trim() == "error") 
		{   
				$("#alertError").text(resultSet.data); 
				$("#alertError").show();   
		}  
 } else if (status == "error") 
 {  	
		$("#alertError").text("Error while deleting.");
		$("#alertError").show(); 
 }else  
 { 
		$("#alertError").text("Unknown error while deleting.."); 
		$("#alertError").show(); 
  } 
 } 



// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event)
{    
		$("#hidHospitalIDSave").val($(this).closest("tr").find('#hidHospitalIDUpdate').val());   
		$("#hospitalNo").val($(this).closest("tr").find('td:eq(0)').text());  
		$("#hospitalName").val($(this).closest("tr").find('td:eq(1)').text());   
		$("#hospitalAddress").val($(this).closest("tr").find('td:eq(2)').text());  
		$("#hospitalPhone").val($(this).closest("tr").find('td:eq(3)').text());
		$("#hospitalEmail").val($(this).closest("tr").find('td:eq(4)').text());  
		$("#hospitalPassword").val($(this).closest("tr").find('td:eq(5)').text());
 });  
 
// CLIENT-MODEL================================================================ 
function validateHospitalForm() 
{ 
    // NO
	if ($("#hospitalNo").val().trim() == "")
	 {   
		return "Insert Hospital No.";
	 }  
	 
	// NAME 
	if ($("#hospitalName").val().trim() == "")
	 { 
		return "Insert Hospital Name.";
	 }  
 
	// ADDRESS  
	if ($("#hospitalAddress").val().trim() == "") 
	 {  
		return "Insert Hospital Address."; 
	 }  
 
	// PHONE
	if ($("#hospitalPhone").val().trim() == "")
	 {   
		return "Insert Hospital Phone Number.";
	 }  
 
// is numerical value

    var tmpPhone = $("#hospitalPhone").val().trim();
    if (!$.isNumeric(tmpPhone))
     {
           return "Insert a numerical value for Phone Number.";
     } 
	// EMAIL 
	if ($("#hospitalEmail").val().trim() == "") 
	 {  
		return "Insert Hospital Email Address."; 
	  }  
 
	// PASSWORD
	if ($("#hospitalPassword").val().trim() == "")
	 {   
		return "Insert Hospital Password.";
	 }  
	 
      return true;
 } 
 