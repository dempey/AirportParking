$(document).ready(function()
{
	var context = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	
	var lastTargetId = '';

	$('area').click(function(event)
	{
		//check the class first
		var className = $('#' + event.target.id).attr('class');
		if(className != 'disabled')
		{				
			var slotNumber = event.target.id;
			$('#formSelectedSlot').val(slotNumber);				
			$('#displaySelectedSlot').text(slotNumber);

			var targetId = '#' + slotNumber;
			event.preventDefault();
			var data = $(targetId).data('maphilight') || {};
			data.alwaysOn = !data.alwaysOn;
			$(targetId).data('maphilight', data).trigger('alwaysOn.maphilight');
		
			if(!data.alwaysOn)
			{
				//clear the form and display values
				$('#formSelectedSlot').val('');				
				$('#displaySelectedSlot').text('');
			}
			else if(lastTargetId != targetId)
			{
				var data = $(lastTargetId).data('maphilight') || {};
				data.alwaysOn = false;
				$(lastTargetId).data('maphilight', data).trigger('alwaysOn.maphilight');
			}
			lastTargetId = targetId;
		}
	});
	
	getReservationJsonData( $('#fromDate').val(), $('#toDate').val());

	//This is called to populate reservation data
	function getReservationJsonData(fromDate, toDate)
	{
		$.ajax
		({
			url: context + "/reservation/existingReservations.json",
			accepts: "application/json",
			type: "GET",
			data: { 'fromDate': fromDate, 'toDate': toDate },
			dataType: "json",
			success: function(reservationList)
			{
				var reservationData = reservationList.jsonReservations.aData;
				$.each(reservationData, function(index, value)
				{					
					var targetId = '#' + value;
					var data = $(targetId).data('maphilight') || {};
					//data.preventDefault();
					data.alwaysOn = true;
					$(targetId).data('maphilight', data).trigger('alwaysOn.maphilight');
					data.fillColor = '707070';
					$(targetId).data('maphilight', data).trigger('fillColor.maphilight');
					data.strokeColor = '282828';
					$(targetId).data('maphilight', data).trigger('strokeColor.maphilight');
					//add empty 'disabled' class as marker to be used by click event
					$(targetId).addClass('disabled');
				});
			}
		});
	}
});

$(function()
{
	$('.map').maphilight({
		alwaysOn: false,
		fillColor: '00FF00',
		stroke: true,
		fade: true,
		strokeColor: '003333'
	});
});