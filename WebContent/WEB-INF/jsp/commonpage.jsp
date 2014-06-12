<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Airport Parking Main</title>
	</head>

	<body>

		<%@ include file="commonheader.jsp" %>

		<div id="sub-header">
			<table>
				<tr><td>Welcome to the Airport Parking Reservation System</td></tr>
			</table>
		</div>

		<div id="main-form">
			<table>
				<tr>
					<td><a href="${pageContext.request.contextPath}/vehicle/addvehicle.html">Add a vehicle</a></td>
				</tr>
				<tr>
					<td><a href="${pageContext.request.contextPath}/reservation/addreservation.html">Add a reservation</a></td>
				</tr>
				<tr>
					<td><a href="${pageContext.request.contextPath}/vehiclemanage/managevehicles.html">Manage your vehicles</a></td>
				</tr>
				<tr>
					<td><a href="${pageContext.request.contextPath}/reservationmanage/managereservations.html">Manage your reservations</a></td>
				</tr>
				<tr>
					<td><a href="${pageContext.request.contextPath}/language/languagepage.html">Language</a></td>
				</tr>
			</table>
		</div>

	</body>
</html>