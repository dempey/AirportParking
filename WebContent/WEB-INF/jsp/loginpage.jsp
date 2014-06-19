<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Welcome to Airport Parking</title>

		<style type="text/css">
			*
			{
				font-family: Arial, Helvetica, sans-serif;
			}
			a
			{
				text-decoration: none;
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
			div#login-error
			{
				padding-top: 30px;
				color: red;
				background-color: white;
			}
			div#main-form
			{
				padding-top: 25px;
				padding-left:25px;
			}
			div.newUserLink
			{
				padding-top: 40px;
			}
			div.newUserLink a
			{
				padding-left: 105px;
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
		<div id="login-error">${error}</div>
		<div id="main-form">
			<form action="../j_spring_security_check" method="post">
				<table>                                                          
					<tr>
						<td><label for="j_username" id="username">Username</label></td>
						<td><input id="j_username" name="j_username" type="text" /></td>
					</tr>
				
					<tr>
						<td><label for="j_password" id="password">Password</label></td>
						<td><input id="j_password" name="j_password" type="password" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" value="Login" id="submit" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="newUserLink">
			<a href="${pageContext.request.contextPath}/anonymous/newuser.html">Create new account</a>
		</div>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.0.js"></script>
		<script type="text/javascript">
			$(document).ready(function()
			{
				$('#username').focus();
			});
		</script>
		
	</body>
</html>