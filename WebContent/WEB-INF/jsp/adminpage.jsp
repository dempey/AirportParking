<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User administration</title>
		
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" />
	</head>
	
	<body>
		<%@ include file="commonheader.jsp" %>
	
		<div id="sub-header">
			<table>
				<tr><td>User administration</td></tr>
			</table>
		</div>

		<div id="main-form">
		
			<table id="customerList" class="display"></table>
		
		</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom.userTypeList.js"></script>
	</body>
</html>