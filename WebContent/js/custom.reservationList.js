$(document).ready(function()
{

	var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	
	/* Init the table */
	var oTable =
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
			"sAjaxSource": context + "/reservationmanage/reservationlist.json",
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
				url: context + "/reservationmanage/deletereservation.html",
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