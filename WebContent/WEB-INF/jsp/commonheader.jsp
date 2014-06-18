<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/parking.css" />

<!-- Add all scripts here so they aren't included on multiple pages -->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jeditable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simpletip-1.3.1.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jshashtable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.numberformatter-1.2.3.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dateRange.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.maphilight.js"></script>

<!-- End scripts -->

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