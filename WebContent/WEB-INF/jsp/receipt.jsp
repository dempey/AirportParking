<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Reservation Receipt</title>
	</head>
	
	<body>
		<%@ include file="commonheader.jsp" %>
	
		<div id="sub-header">
			<table>
				<tr><td>Reservation Successfully Created</td></tr>
				<tr><td style="font-size: 12pt;">Please print this receipt for your records</td></tr>
			</table>
		</div>
		
		<div id="main-form">
			<table>
				<tr>
					<td style="vertical-align: top; padding-top: 17px;">Parking Lot Location</td>
					<td>
						<p>
						<label id="parkingLotName" >${parkingLotName}</label><br/>
						<label id="parkingLotStreet" >${parkingLotStreet}</label><br/>
						<label id="parkingLotCity" >${parkingLotCity}</label>,&nbsp;
						<label id="parkingLotState" >${parkingLotState}</label>&nbsp;&nbsp;
						<label id="parkingLotZip" >${parkingLotZip}</label>
						</p>
					</td>
				</tr>
				<tr class="reservationInfo">
					<td>Parking Slot</td>
					<td>
						<label id="displayableSlotNumber" >${parkingSlot}</label>
					</td>
				</tr>
				<tr class="reservationInfo">
					<td>Begin Date</td>
					<td>
						<label id="fromDate">${fromDate}</label>
					</td>
				</tr>
				<tr class="reservationInfo">
					<td>End Date</td>
					<td>
						<label id="displayableToDate">${toDate}</label>
					</td>
				</tr>
				<tr class="reservationInfo">
					<td>Vehicle</td>
					<td>
						<label id="vehicleMake" >${vehicleMake}</label>&nbsp;
						<label id="vehicleModel" >${vehicleModel}</label>&nbsp;
						(<label id="vehiclePlate" >${vehiclePlate}</label>)
					</td>
				</tr>
				<tr>
					<td>
					<table class="reservationCharges">
						<tr class="reservationInfo">
							<td>Days to be reserved</td><td>${numberDays}</td>
						</tr>
						<tr class="reservationInfo">
							<td>Base price (&nbsp;<font class="dollar">${pricePerDay}</font> per day X ${numberDays} days&nbsp;)</td><td><font class="dollar">${basePrice}</font></td>
						</tr>
						<tr class="reservationInfo">
							<td>Taxes (&nbsp;<font class="percent">${taxRate}</font>&nbsp;)</td><td><font class="dollar">${totalTax}</font></td>
						</tr>
						<tr class="reservationInfo">
							<td>Total price</td><td><b class="dollar">${totalPrice}</b></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" name="print" value="Print" onclick="javascript:window.print()"/>
					</td>
				</tr>
			</table>
		</div>
		
		<script type="text/javascript">
			$(document).ready(function()
			{
				$('.dollar').formatNumber({format:"$#,###.00", locale:"us"});
				$('.percent').formatNumber({format:"#.0%", locale:"us"});
			});
		</script>
		
	</body>
</html>