<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="pl.bgora.forms.EditBuildingForm"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<head>
<html:base />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="STYLESHEET"
	href=" <html:rewrite page="/css/default.css" ></html:rewrite> "
	type="text/css" />
<%
	EditBuildingForm editForm = (EditBuildingForm) session
			.getAttribute("editBuildingForm");
%>
</head>
<body>
<div class="post">
<div class="title">
<%
	if (editForm.getMode().equals(EditBuildingForm.Mode.ADD)) {
%> <bean:message key="page.building.add" /> <%
 	} else if (editForm.getMode().equals(EditBuildingForm.Mode.EDIT)) {
 %> <bean:message key="page.building.edit" /> <%
 	}
 %>
</div>
<div class="entry"><html:form action="/editBuilding.do" method="post">
	<table>
		<tr>
			<td><bean:message key="page.bulding.city" /></td>
			<td><html:select name="editBuildingForm" property="city"
				style="width: 220px">
				<html:options name="editBuildingForm" property="cities" />
			</html:select></td>
		</tr>
		<tr>
			<td><bean:message key="page.builging.streetName" /></td>
			<td><html:text name="editBuildingForm" property="streetName"
				style="width: 220px"></html:text></td>
		</tr>
		<tr>
			<td><bean:message key="page.building.streetNumber" /></td>
			<td><html:text name="editBuildingForm" property="streetNumber"
				style="width: 220px"></html:text></td>
		</tr>
		<tr>
			<td><bean:message key="page.building.flatsNumber" /></td>
			<td><bean:write name="editBuildingForm" property="flatCount" /></td>
		</tr>
		<tr>
			<td><html:submit property="showFlats">
				<bean:message key="page.building.showFlats" />
			</html:submit></td>
		</tr>
		<tr>
			<td colspan="2"><html:submit property="submit">
				<bean:message key="apply" />
			</html:submit><html:cancel property="cancel">
				<bean:message key="cancel" />
			</html:cancel></td>
		</tr>

	</table>
</html:form></div>
</div>
</body>
</html>