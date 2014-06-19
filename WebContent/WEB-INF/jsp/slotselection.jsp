<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Select A Parking Slot</title>
</head>
<body>
	<%@ include file="commonheader.jsp" %>
	
	<div id="sub-header">
		<table>
			<tr><td>Select a parking slot</td></tr>
		</table>
	</div>
	
	<div id="main-form">
		<form:form modelAttribute="newreservation" method="post" action="${pageContext.request.contextPath}/reservation/validation.html">
			Selected parking slot:&nbsp;<label title="Selected Parking Slot" id="displaySelectedSlot"></label>
			<form:hidden path="slotNumber" id="formSelectedSlot"/>
			<form:hidden path="fromDate" id="fromDate"/>
			<form:hidden path="toDate" id="toDate"/>
			<form:hidden path="vehicleId" id="vehicleId"/>
			<div class="map" >
				<img class="map highlighted" width="952" height="737" usemap="#parking_lot_map" src="../images/Parking_Lot_Diagram_1.jpg" >
			</div>
			<map id="parking_lot_map" name="parking_lot_map" >
				<!-- SECTION 'A' -->
				<area id="A1" shape="poly" coords="148,91,184,129,205,130,170,92," href="#" alt="A1" title="A1" />
				<area id="A2" shape="poly" coords="185,90,224,130,245,130,205,91," href="#" alt="A2" title="A2" />
				<area id="A3" shape="poly" coords="223,91,263,130,283,131,245,91," href="#" alt="A3" title="A3" />
				<area id="A4" shape="poly" coords="262,92,300,130,321,131,283,93," href="#" alt="A4" title="A4" />
				<area id="A5" shape="poly" coords="299,91,338,130,362,131,321,92," href="#" alt="A5" title="A5" />
				<area id="A6" shape="poly" coords="338,92,376,131,397,132,358,92," href="#" alt="A6" title="A6" />
				<area id="A7" shape="poly" coords="374,91,412,131,437,132,396,92," href="#" alt="A7" title="A7" />
				<area id="A8" shape="poly" coords="411,93,434,94,474,132,451,131," href="#" alt="A8" title="A8" />
				<area id="A9" shape="poly" coords="451,92,490,130,512,131,471,91," href="#" alt="A9" title="A9" />
				<area id="A10" shape="poly" coords="487,91,527,130,552,131,510,92," href="#" alt="A10" title="A10" />
				<area id="A11" shape="poly" coords="527,91,565,130,588,132,548,93," href="#" alt="A11" title="A11" />
				<area id="A12" shape="rect" coords="601,92,623,134" href="#" alt="A12" title="A12" />
				<area id="A13" shape="rect" coords="630,92,652,134" href="#" alt="A13" title="A13" />
				<area id="A14" shape="rect" coords="659,93,681,135" href="#" alt="A14" title="A14" />
	
				<!-- SECTION 'B' -->
				<area id="B1" shape="rect" coords="236,196,281,218" href="#" alt="B1" title="B1" />
				<area id="B2" shape="rect" coords="290,196,334,218" href="#" alt="B2" title="B2" />
	
				<area id="B3" shape="rect" coords="236,222,281,244" href="#" alt="B3" title="B3" />
				<area id="B4" shape="rect" coords="290,222,334,244" href="#" alt="B4" title="B4" />
	
				<area id="B5" shape="rect" coords="236,248,281,271" href="#" alt="B5" title="B5" />
				<area id="B6" shape="rect" coords="290,248,334,271" href="#" alt="B6" title="B6" />
	
				<area id="B7" shape="rect" coords="236,275,281,297" href="#" alt="B7" title="B7" />
				<area id="B8" shape="rect" coords="290,275,334,297" href="#" alt="B8" title="B8" />
	
				<area id="B9" shape="rect" coords="236,301,281,323" href="#" alt="B9" title="B9" />
				<area id="B10" shape="rect" coords="290,301,334,323" href="#" alt="B10" title="B10" />
	
				<area id="B11" shape="rect" coords="236,327,281,350" href="#" alt="B11" title="B11" />
				<area id="B12" shape="rect" coords="290,327,334,350" href="#" alt="B12" title="B12" />
	
				<area id="B13" shape="rect" coords="236,352,281,376" href="#" alt="B13" title="B13" />
				<area id="B14" shape="rect" coords="290,352,334,376" href="#" alt="B14" title="B14" />
	
				<area id="B15" shape="rect" coords="236,380,281,404" href="#" alt="B15" title="B15" />
				<area id="B16" shape="rect" coords="290,380,334,404" href="#" alt="B16" title="B16" />
	
				<area id="B17" shape="rect" coords="236,408,281,430" href="#" alt="B17" title="B17" />
				<area id="B18" shape="rect" coords="290,408,334,430" href="#" alt="B18" title="B18" />
	
				<area id="B19" shape="rect" coords="236,434,281,456" href="#" alt="B19" title="B19" />
				<area id="B20" shape="rect" coords="290,434,334,456" href="#" alt="B20" title="B20" />
	
				<area id="B21" shape="rect" coords="236,460,281,482" href="#" alt="B21" title="B21" />
				<area id="B22" shape="rect" coords="290,460,334,482" href="#" alt="B22" title="B22" />
	
				<area id="B23" shape="rect" coords="236,486,281,509" href="#" alt="B23" title="B23" />
				<area id="B24" shape="rect" coords="290,486,334,509" href="#" alt="B24" title="B24" />
	
				<area id="B25" shape="rect" coords="236,513,281,536" href="#" alt="B25" title="B25" />
				<area id="B26" shape="rect" coords="290,513,334,536" href="#" alt="B26" title="B26" />
	
				<area id="B27" shape="rect" coords="236,540,281,561" href="#" alt="B27" title="B27" />
				<area id="B28" shape="rect" coords="290,540,334,561" href="#" alt="B28" title="B28" />
	
				<area id="B29" shape="rect" coords="236,565,281,592" href="#" alt="B29" title="B29" />
				<area id="B30" shape="rect" coords="290,565,334,592" href="#" alt="B30" title="B30" />
	
				<area id="B31" shape="rect" coords="236,596,281,618" href="#" alt="B31" title="B31" />
				<area id="B32" shape="rect" coords="290,596,334,618" href="#" alt="B32" title="B32" />
	
				<area id="B33" shape="rect" coords="236,622,281,646" href="#" alt="B33" title="B33" />
				<area id="B34" shape="rect" coords="290,622,334,646" href="#" alt="B34" title="B34" />
	
				<area id="B35" shape="rect" coords="236,650,281,672" href="#" alt="B35" title="B35" />
				<area id="B36" shape="rect" coords="290,650,334,672" href="#" alt="B36" title="B36" />
	
				<!-- SECTION 'C' -->
				<area id="C1" shape="rect" coords="470,196,515,218" href="#" alt="C1" title="C1" />
				<area id="C2" shape="rect" coords="524,196,568,218" href="#" alt="C2" title="C2" />
	
				<area id="C3" shape="rect" coords="470,222,515,244" href="#" alt="C3" title="C3" />
				<area id="C4" shape="rect" coords="524,222,568,244" href="#" alt="C4" title="C4" />
	
				<area id="C5" shape="rect" coords="470,248,515,271" href="#" alt="C5" title="C5" />
				<area id="C6" shape="rect" coords="524,248,568,271" href="#" alt="C6" title="C6" />
	
				<area id="C7" shape="rect" coords="470,275,515,297" href="#" alt="C7" title="C7" />
				<area id="C8" shape="rect" coords="524,275,568,297" href="#" alt="C8" title="C8" />
	
				<area id="C9" shape="rect" coords="470,301,515,323" href="#" alt="C9" title="C9" />
				<area id="C10" shape="rect" coords="524,301,568,323" href="#" alt="C10" title="C10" />
	
				<area id="C11" shape="rect" coords="470,327,515,350" href="#" alt="C11" title="C11" />
				<area id="C12" shape="rect" coords="524,327,568,350" href="#" alt="C12" title="C12" />
	
				<area id="C13" shape="rect" coords="470,352,515,376" href="#" alt="C13" title="C13" />
				<area id="C14" shape="rect" coords="524,352,568,376" href="#" alt="C14" title="C14" />
	
				<area id="C15" shape="rect" coords="470,380,515,404" href="#" alt="C15" title="C15" />
				<area id="C16" shape="rect" coords="524,380,568,404" href="#" alt="C16" title="C16" />
	
				<area id="C17" shape="rect" coords="470,408,515,430" href="#" alt="C17" title="C17" />
				<area id="C18" shape="rect" coords="524,408,568,430" href="#" alt="C18" title="C18" />
	
				<area id="C19" shape="rect" coords="470,434,515,456" href="#" alt="C19" title="C19" />
				<area id="C20" shape="rect" coords="524,434,568,456" href="#" alt="C20" title="C20" />
	
				<area id="C21" shape="rect" coords="470,460,515,482" href="#" alt="C21" title="C21" />
				<area id="C22" shape="rect" coords="524,460,568,482" href="#" alt="C22" title="C22" />
	
				<area id="C23" shape="rect" coords="470,486,515,509" href="#" alt="C23" title="C23" />
				<area id="C24" shape="rect" coords="524,486,568,509" href="#" alt="C24" title="C24" />
	
				<area id="C25" shape="rect" coords="470,513,515,536" href="#" alt="C25" title="C25" />
				<area id="C26" shape="rect" coords="524,513,568,536" href="#" alt="C26" title="C26" />
	
				<area id="C27" shape="rect" coords="470,540,515,561" href="#" alt="C27" title="C27" />
				<area id="C28" shape="rect" coords="524,540,568,561" href="#" alt="C28" title="C28" />
	
				<area id="C29" shape="rect" coords="470,565,515,592" href="#" alt="C29" title="C29" />
				<area id="C30" shape="rect" coords="524,565,568,592" href="#" alt="C30" title="C30" />
	
				<area id="C31" shape="rect" coords="470,596,515,618" href="#" alt="C31" title="C31" />
				<area id="C32" shape="rect" coords="524,596,568,618" href="#" alt="C32" title="C32" />
	
				<area id="C33" shape="rect" coords="470,622,515,646" href="#" alt="C33" title="C33" />
				<area id="C34" shape="rect" coords="524,622,568,646" href="#" alt="C34" title="C34" />
	
				<area id="C35" shape="rect" coords="470,650,515,672" href="#" alt="C35" title="C35" />
				<area id="C36" shape="rect" coords="524,650,568,672" href="#" alt="C36" title="C36" />
	
				<!-- SECTION 'D' -->
				<area id="D1" shape="rect" coords="688,575,709,618" href="#" alt="D1" title="D1" />
				<area id="D2" shape="rect" coords="688,627,709,668" href="#" alt="D2" title="D2" />
	
				<area id="D3" shape="rect" coords="713,575,735,618" href="#" alt="D3" title="D3" />
				<area id="D4" shape="rect" coords="713,627,735,668" href="#" alt="D4" title="D4" />
	
				<area id="D5" shape="rect" coords="739,575,762,618" href="#" alt="D5" title="D5" />
				<area id="D6" shape="rect" coords="739,627,762,668" href="#" alt="D6" title="D6" />
	
				<area id="D7" shape="rect" coords="766,575,788,618" href="#" alt="D7" title="D7" />
				<area id="D8" shape="rect" coords="766,627,788,668" href="#" alt="D8" title="D8" />
	
				<area id="D9" shape="rect" coords="792,575,816,618" href="#" alt="D9" title="D9" />
				<area id="D10" shape="rect" coords="792,627,816,668" href="#" alt="D410" title="D10" />
	
				<area id="D11" shape="rect" coords="820,575,869,618" href="#" alt="D11" title="D11" />
				<area id="D12" shape="rect" coords="820,627,869,668" href="#" alt="D12" title="D12" />
	
				<area id="D13" shape="rect" coords="873,575,922,618" href="#" alt="D13" title="D13" />
				<area id="D14" shape="rect" coords="873,627,922,668" href="#" alt="D14" title="D14" />
	
				<area id="allMap" shape="rect" coords="1054,814,1056,816" href="#" alt="Image Map" title="Image Map" />
			</map>
			<div id="submitDiv" style="position: relative; padding-top: 25px;">
				<input type="submit" value="Next" alt="Validate your selection"/><a href="${pageContext.request.contextPath}/main/common.html"><input type="button" name="cancel" value="Cancel"/></a>
			</div>
		</form:form>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom.slotSelector.js"></script>
	
</body>
</html>