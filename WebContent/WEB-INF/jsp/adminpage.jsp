<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User administration</title>
		
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" />
	</head>
	
	<body>
		<%@ include file="commonheader.jsp" %>
	
		<div id="sub-header">
			<table>
				<tr><td>User administration</td></tr>
			</table>
		</div>

		<div id="main-form">
		
			<table id="customerList" class="display"></table>
		
		</div>

		<script type="text/javascript">
			$(document).ready(function()
			{
				//Add jquery stuff for tooltips
				$('.editable').simpletip({
					fixed: true,
					content: 'Click to edit'
				});
				
				//These are called on document.ready
				getUserJsonData();
				getUserTypesJsonData();
				
				var userTypeData = null;
				
				function getUserTypesJsonData()
				{
					$.ajax
					({
						url: "${pageContext.request.contextPath}/administrator/usertypelist.json",
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
						url: "${pageContext.request.contextPath}/administrator/userlist.json",
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
					
					$('.edit_type', oTable.fnGetNodes()).editable('${pageContext.request.contextPath}/administrator/usertypeedit.html', {
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
					
					$('.edit_company_name', oTable.fnGetNodes()).editable('${pageContext.request.contextPath}/administrator/companynameedit.html', {
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
		</script>

	</body>
</html>