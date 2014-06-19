$(document).ready(function()
{
	var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	
	var oTable =
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
			"sAjaxSource": context + "/vehiclemanage/vehiclelist.json",
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
			url: context + "/vehiclemanage/deletevehicle.html",
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