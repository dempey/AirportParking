<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User Reservation Management</title>

		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" />
	</head>

	<body>
		<%@ include file="commonheader.jsp" %>

		<div id="sub-header">
			<table>
				<tr><td>Reservation Management</td></tr>
			</table>
		</div>

		<div style="padding-left: 25px; padding-top: 25px">
			<a href="javascript:void(0)" id="delete">Delete selected row</a>
		</div>

		<div id="main-form" style="width: 870px">
			<table id="reservationList" class="display"></table>
		</div>

		<script type="text/javascript">
			var oTable;

			$(document).ready(function()
			{

				/* Init the table */
				oTable =
					$('#reservationList').dataTable(
					{
						"sDom": 'Rlfrtip',
						"sScrollY": "150px",
						"sScrollX": "100%",
						"sScrollXInner": "870px",
						"bScrollCollapse": false,
						"bPaginate": false,
						"bProcessing": true,
						"bServerSide": false,
						"sAjaxSource": "${pageContext.request.contextPath}/reservationmanage/reservationlist.json",
						"aoColumns":
						[
						 	{"mDataProp": "beginDate", "sTitle": "Begin Date", "sClass": "center", "sWidth": "110px"},
						 	{"mDataProp": "endDate", "sTitle": "End Date", "sClass": "center", "sWidth": "110px"},
						 	{"mDataProp": "parkingSlot", "sTitle": "Parking Slot", "sClass": "center", "sWidth": "130px"},
						 	{"mDataProp": "vehicle", "sTitle": "Vehicle"}
						]
					});

					/* Add a click handler to the rows - this could be used as a callback */
					$("#reservationList tbody").click(function(event) {
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
							url: "${pageContext.request.contextPath}/reservationmanage/deletereservation.html",
							type: "POST",
							data: "beginDate="+anSelected[0].cells[0].innerHTML+"&endDate="+anSelected[0].cells[1].innerHTML+"&slotNumber="+anSelected[0].cells[2].innerHTML,
							success: function(userList)
							{}
						});
						oTable.fnDeleteRow( anSelected[0] );
					});

					/* Init the table */
					oTable = $('#reservationList').dataTable( );
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

	</body>
</html>