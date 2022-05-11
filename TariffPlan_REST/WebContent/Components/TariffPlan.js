$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateTariffForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
}
	// If valid------------------------
var type = ($("#hidPlanIDSave").val() == "") ? "POST" : "PUT";	
	$.ajax(
		{
			url: "TariffPlanAPI",
			type: type,
			data: $("#formPlan").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onItemSaveComplete(response.responseText, status);
				
			}
		});
});

function onItemSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divTariffPlanGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidPlanIDSave").val("");
	$("#formPlan")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {

	$("#hidPlanIDSave").val($(this).closest("tr").find('#hidTariffIDUpdate').val());
	$("#tariffPlanId").val($(this).closest("tr").find('td:eq(0)').text());
	$("#tariffBlock").val($(this).closest("tr").find('td:eq(1)').text());
	$("#unitRate").val($(this).closest("tr").find('td:eq(2)').text());
	$("#fixedCharge").val($(this).closest("tr").find('td:eq(3)').text());
});

//REMOVE==============================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "TariffPlanAPI",
			type: "DELETE",
			data: "TariffID=" + $(this).data("itemid"),
			dataType: "text",
			complete: function(response, status) {
				onPlanDeleteComplete(response.responseText, status);
			}
		});
});

function onPlanDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divTariffPlanGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENT-MODEL================================================================
function validateTariffForm() {
	// ID
	if ($("#tariffPlanId").val().trim() == "") {
		return "Insert Tariff Plan ID.";
	}
	// BLOCK
	if ($("#tariffBlock").val().trim() == "") {
		return "Insert tariff Block.";
	}

	// unitRate -------------------------------
	if ($("#unitRate").val().trim() == "") {
		return "Insert Unit Rate.";
	}
	// is numerical value
	var tmpPrice = $("#unitRate").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for Unit Rate.";
	}

	// Fixed Charge------------------------
	if ($("#fixedCharge").val().trim() == "") {
		return "Insert fixed Charge.";
	}
	// is numerical value
	var tmpPrice2 = $("#fixedCharge").val().trim();
	if (!$.isNumeric(tmpPrice2)) {
		return "Insert a numerical value for fixed Charge.";
	}

	
	return true;
}