<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/parking.css" />

<div class="parkingTitle">
	<table>
		<tr><td class="titleTop">Airport Parking</td></tr>
		<tr><td class="titleBottom">Reservation System</td></tr>
	</table>
</div>

<div class="parkingHeader">
	<table>
		<tr class="parkingHeaderRows">
			<td>
				<a href="${pageContext.request.contextPath}/main/common.html" class="parkingHeaderLinks">Main</a>
			</td>
			<td>
				<a href="${pageContext.request.contextPath}/auth/logout.html" class="parkingHeaderLinks">Logout</a>
			</td>
			<td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a href="${pageContext.request.contextPath}/administrator/admin.html" class="parkingHeaderLinks">Administration</a>
				</sec:authorize>
			</td>
		</tr>
	</table>
</div>