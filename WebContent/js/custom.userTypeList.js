$(document).ready(function()
{
	//Add jquery stuff for tooltips
	$('.editable').simpletip({
		fixed: true,
		content: 'Click to edit'
	});
	
	var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	
	//These are called on document.ready
	getUserJsonData();
	getUserTypesJsonData();
	
	var userTypeData = null;
	
	function getUserTypesJsonData()
	{
		$.ajax
		({
			url: context + "/administrator/usertypelist.json",
			accepts: "application/json",
			type: "GET",
			dataType: "json",
			success: function(userTypeList)
			{
				userTypeData = userTypeList.jsonUserTypes.aData;
			}
		});
	}
	
	function getUserJsonData()
	{
		$.ajax
		({
			url: context + "/administrator/userlist.json",
			accepts: "application/json",
			type: "GET",
			dataType: "json",
			success: function(userList)
			{
				var userData = userList.jsonUsers.aData;
				populateUserTable(userData);
			}
		});
	}
	
	function populateUserTable(userData)
	{
		var oTable = 
			$('#customerList').dataTable(
			{
				"aaData": userData,
				"sScrollY": "150px",		
				"sScrollX": "120%",
				"sScrollXInner": "1800px",
				"bScrollCollapse": false,
				"bPaginate": false,
				"aoColumns":
				[
					{"sTitle": "User ID", "sClass": "center" },
					{"sTitle": "Username", "sClass": "center" },
					{"sTitle": "Company Name", "sClass": "center edit_company_name editable" },
					{"sTitle": "First Name", "sClass": "center" },
					{"sTitle": "Last Name", "sClass": "center" },
					{"sTitle": "Street", "sClass": "center" },
					{"sTitle": "City", "sClass": "center" },
					{"sTitle": "State", "sClass": "center" },
					{"sTitle": "Zip Code", "sClass": "center" },
					{"sTitle": "Primary Email", "sClass": "center" },
					{"sTitle": "Secondary Email", "sClass": "center" },
					{"sTitle": "Primary Phone", "sClass": "center" },
					{"sTitle": "Secondary Phone", "sClass": "center" },
					{"sTitle": "Account Created", "sClass": "center" },
					{"sTitle": "User Type", "sClass": "center edit_type editable" }
				]
			});
		
		$('.edit_type', oTable.fnGetNodes()).editable(context + '/administrator/usertypeedit.html', {
			//loadurl: '${pageContext.request.contextPath}/administrator/usertypelist.json',
			data: userTypeData,
			type: 'select',
			submit: 'OK',
			tooltip: 'Click to edit',
			"callback": function( sValue, y ) {
				var aPos = oTable.fnGetPosition( this );
				oTable.fnUpdate( sValue.replace(/\"/g, ''), aPos[0], aPos[1] );
			},
			"submitdata": function ( value, settings ) {
				return {
					"row_id": this.parentNode.childNodes[0].textContent,
					"column": oTable.fnGetPosition( this )[2]
				};
			},
			"height": "14px"
		});
		
		$('.edit_company_name', oTable.fnGetNodes()).editable(context + '/administrator/companynameedit.html', {
			type: 'textarea',
			cancel: 'Cancel',
			submit: 'OK',
			tooltip: 'Click to edit',
			"callback": function( sValue, y ) {
				var aPos = oTable.fnGetPosition( this );
				oTable.fnUpdate( sValue.replace(/\"/g, ''), aPos[0], aPos[1] );
			},
			"submitdata": function ( value, settings ) {
				return {
					"row_id": this.parentNode.childNodes[0].textContent,
					"column": oTable.fnGetPosition( this )[2]
				};
			},
			"height": "18px"
		});
	}
	
});