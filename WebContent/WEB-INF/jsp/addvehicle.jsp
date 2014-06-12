<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add A Vehicle</title>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.0.js"></script>

		<script type="text/javascript">
			$(document).ready(function()
			{
				$('#make').focus();
			});
		</script>
	</head>
	
	<body>
		<%@ include file="commonheader.jsp" %>
	
		<div id="sub-header">
			<table>
				<tr><td>Add a vehicle</td></tr>
			</table>
		</div>
		
		<div id="main-form">
			<div id="new-vehicle-error">${error}</div>
			<form:form modelAttribute="newvehicle" method="post" action="${pageContext.request.contextPath}/vehicle/submitnewvehicle.html">
				<table>
					<tr>
						<td>Make</td>
						<td><form:input path="make" id="make" /></td>
					</tr>
					<tr>
						<td>Model</td>
						<td><form:input path="model" id="model" /></td>
					</tr>
					<tr>
						<td>Color</td>
						<td>
							<form:select path="color" id="color">
								<form:options items="${colors}" />
							</form:select>
						</td>
					</tr>
					<tr>
						<td>Plate Number</td>
						<td><form:input path="plateNumber" id="plateNumber" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
							<input type="submit" value="Add This Vehicle" id="submit"/><a href="${pageContext.request.contextPath}/main/common.html"><input type="button" name="cancel" value="Cancel" id="cancel"/></a>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</body>
</html>