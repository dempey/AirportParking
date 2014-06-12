<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Create A Reservation</title>
	
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/dateRange.css" />
	
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.0.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dateRange.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function()
			{
				$('#dateRangePicker').dateRange(
				{
					selected: function(dates)
					{
						var from = dates[0];
						var to = dates[1];
						//do something here like ajax call?
						$('#fromDate').val(from);
						$('#toDate').val(to);
					}
				});
			});
		</script>
	</head>
	
	<body>
		<%@ include file="commonheader.jsp" %>
	
		<div id="sub-header">
			<table>
				<tr><td>Create a new reservation</td></tr>
			</table>
		</div>
		
		<div id="main-form">
			<form:form modelAttribute="newreservation" method="post" action="${pageContext.request.contextPath}/reservation/selectslot.html">
				<div id="selectablesDiv" style="padding-bottom: 150px;">
					<form:hidden id="fromDate" path="fromDate" />
					<form:hidden id="toDate" path="toDate" />
					<table style="width: 200%;">
						<tr>
							<td><form:select path="vehicleId" items="${vehicles}" /></td>
						</tr>
						<tr>
							<td><input type="text" id="dateRangePicker" /></td>
						</tr>
					</table>
				</div>
				<div id="submitDiv" style="position: relative; padding-top: 30px;">
					<input type="submit" value="Next" alt="Select parking slot"/><a href="${pageContext.request.contextPath}/main/common.html"><input type="button" name="cancel" value="Cancel"/></a>
				</div>
			</form:form>
		</div>
	</body>
	
</html>