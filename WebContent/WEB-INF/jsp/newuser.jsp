<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Create new account</title>
		
		<style type="text/css">
			*
			{
				font-family: Arial, Helvetica, sans-serif;
			}
			div.loginHeader
			{
				width: 100%;
				height: 80px;
				color: white;
				background-color: #000099;
			}
			td.titleTop
			{
				font-size: 21pt;
				height: 44px;
				vertical-align: bottom;
				padding-left: 25px;
			}
			td.titleBottom
			{
				height: 38px;
				vertical-align: top;
				letter-spacing: 3px;
				padding-left: 25px;
			}
			div#sub-header
			{
				padding-left: 25px;
				padding-top: 25px;
				font-size: 21pt;
			}
			div#new-user-error
			{
				padding-top: 30px;
				color: red;
				background-color: white;
			}
			div#main-form
			{
				padding-top: 10px;
				padding-left: 25px;
			}
			div.newUserLink a
			{
				padding-left: 82px;
			}
		</style>
	</head>
	
	<body>
		<div class="loginHeader">
			<table>
				<tr><td class="titleTop">Airport Parking</td></tr>
				<tr><td class="titleBottom">Reservation System</td></tr>
			</table>
		</div>
		<div id="sub-header">
			<table>
				<tr><td>Please add a new user</td></tr>
			</table>
		</div>
		<div id="new-user-error">${error}</div>
		<div id="main-form">
			<form:form modelAttribute="newuser" method="post" action="${pageContext.request.contextPath}/anonymous/submitnewuser.html">
				<table>
					<tr>
						<td>User Name</td>
						<td><form:input path="username" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><form:password path="password" showPassword="true" /></td>
					</tr>
					<tr>
						<td>Company Name</td>
						<td><input id="newCompanyName" name="companyName" type="text"/></td>
					</tr>
					<tr>
						<td>First Name</td>
						<td><input id="newFirstName" name="firstName" type="text"/></td>
					</tr>
					<tr>
						<td>Last Name</td>
						<td><input id="newLastName" name="lastName" type="text"/></td>
					</tr>
					<tr>
						<td>Street Address</td>
						<td><input id="newStreet" name="street" type="text"/></td>
					</tr>
					<tr>
						<td>City</td>
						<td><input id="newCity" name="city" type="text"/></td>
					</tr>
					<tr>
						<td>State</td>
						<td>
							<form:select path="state" itemLabel="name" itemValue="code" items="${states}" />
						</td>
					</tr>
					<tr>
						<td>Zip</td>
						<td><input id="newZipCode" name="zipCode" type="text"/></td>
					</tr>
					<tr>
						<td>Primary Phone</td>
						<td><input id="newPrimaryPhone" name="primaryPhone" type="text"/></td>
						<td><form:checkbox path="primaryPhoneAcceptsText" label="accepts text messages"/></td>
					</tr>
					<tr>
						<td>Secondary Phone</td>
						<td><input id="newSecondaryPhone" name="secondaryPhone" type="text"/></td>
						<td><form:checkbox path="secondaryPhoneAcceptsText" label="accepts text messages"/></td>
					</tr>
					<tr>
						<td>Primary Email</td>
						<td><input id="newPrimaryEmail" name="primaryEmail" type="text"/></td>
					</tr>
					<tr>
						<td>Secondary Email</td>
						<td><input id="newSecondaryEmail" name="secondaryEmail" type="text"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" value="Create User"/><a href="${pageContext.request.contextPath}/main/common.html"><input type="button" name="cancel" value="Cancel"/></a></td>
					</tr>
				</table>
			</form:form>
		</div>
	</body>
</html>