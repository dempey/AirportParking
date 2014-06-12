<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jshashtable.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.numberformatter-1.2.3.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function()
			{
				$('.dollar').formatNumber({format:"$#,###.00", locale:"us"});
				$('.percent').formatNumber({format:"#.0%", locale:"us"});
			});
		</script>
	</head>
	<body>
		<%@ include file="commonheader.jsp" %>
	
		<div id="sub-header">
			<table>
				<tr><td>Confirmation Page</td></tr>
			</table>
		</div>
		
		<div id="main-form">
			<form:form modelAttribute="newreservation" method="post" action="${pageContext.request.contextPath}/reservation/receipt.html">
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
							<form:hidden path="slotNumber" id="hiddenSlotNumber" value="${parkingSlot}"/>
						</td>
					</tr>
					<tr class="reservationInfo">
						<td>Begin Date</td>
						<td>
							<label id="fromDate">${fromDate}</label>
							<form:hidden id="fromDate" path="fromDate" />
						</td>
					</tr>
					<tr class="reservationInfo">
						<td>End Date</td>
						<td>
							<label id="displayableToDate">${toDate}</label>
							<form:hidden id="toDate" path="toDate" />
						</td>
					</tr>
					<tr class="reservationInfo">
						<td>Vehicle</td>
						<td>
							<label id="vehicleMake" >${vehicleMake}</label>&nbsp;
							<label id="vehicleModel" >${vehicleModel}</label>&nbsp;
							(<label id="vehiclePlate" >${vehiclePlate}</label>)
							<form:hidden id="vehicleId" path="vehicleId" />
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
							<input type="submit" value="Finalize" alt="Finalize and submit payment"/><a href="${pageContext.request.contextPath}/main/common.html"><input type="button" name="cancel" value="Cancel"/></a>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</body>
</html>