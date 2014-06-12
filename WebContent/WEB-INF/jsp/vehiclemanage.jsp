<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User Vehicle Management</title>

		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.0.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jeditable.js"></script>
		<script type="text/javascript">
			var oTable;

			$(document).ready(function()
			{
				oTable =
					$('#vehicleList').dataTable(
					{
						"sDom": 'Rlfrtip',
						"sScrollY": "150px",
						"sScrollX": "100%",
						"sScrollXInner": "870px",
						"bScrollCollapse": false,
						"bPaginate": false,
						"bProcessing": true,
						"bServerSide": false,
						"sAjaxSource": "${pageContext.request.contextPath}/vehiclemanage/vehiclelist.json",
						"aoColumns":
						[
							{"mDataProp": "make", "sTitle": "Make", "sClass": "center", "sWidth": "110px"},
							{"mDataProp": "model", "sTitle": "Model", "sClass": "center", "sWidth": "110px"},
							{"mDataProp": "color", "sTitle": "Color", "sClass": "center", "sWidth": "110px"},
							{"mDataProp": "plateNumber", "sTitle": "Plate Number", "sClass": "center", "sWidth": "110px"}
						]
					});

				/* Add a click handler to the rows - this could be used as a callback */
				$("#vehicleList tbody").click(function(event) {
					$(oTable.fnSettings().aoData).each(function (){
						$(this.nTr).removeClass('row_selected');
					});
					$(event.target.parentNode).addClass('row_selected');
				});

				/* Add a click handler for the delete row */
				$('#delete').click( function() {
					var anSelected = fnGetSelected( oTable );
					//do your ajax call here to delete on the server
					$.ajax
					({
						url: "${pageContext.request.contextPath}/vehiclemanage/deletevehicle.html",
						type: "POST",
						data: "make="+anSelected[0].cells[0].innerHTML+"&model="+anSelected[0].cells[1].innerHTML+"&plateNumber="+anSelected[0].cells[3].innerHTML,
						success: function(userList)
						{
							console.log('I reached the success callback after vehicle deletion');
						}
					});
					oTable.fnDeleteRow( anSelected[0] );
				});

				/* Init the table */
				oTable = $('#vehicleList').dataTable( );
			});

			/* Get the rows which are currently selected */
			function fnGetSelected( oTableLocal )
			{
				var aReturn = new Array();
				var aTrs = oTableLocal.fnGetNodes();

				for ( var i=0 ; i<aTrs.length ; i++ )
				{
					if ( $(aTrs[i]).hasClass('row_selected') )
					{
						aReturn.push( aTrs[i] );
					}
				}
				return aReturn;
			}
		</script>
	</head>

	<body>
		<%@ include file="commonheader.jsp" %>

		<div id="sub-header">
			<table>
				<tr><td>Vehicle Management</td></tr>
			</table>
		</div>

		<div style="padding-left: 25px; padding-top: 25px">
			<a href="javascript:void(0)" id="delete">Delete selected row</a>
		</div>

		<div id="main-form" style="width: 870px">
			<table id="vehicleList" class="display"></table>
		</div>

	</body>
</html>