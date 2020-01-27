<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="pl.bgora.forms.EditFlatForm"%>
<%@page import="pl.bgora.forms.EditFlatForm.Mode"%>

<html
		xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<head>
<html:base />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="STYLESHEET"
	href=" <html:rewrite page="/css/default.css" ></html:rewrite> "
	type="text/css" />
</head>
<%
	EditFlatForm editForm = (EditFlatForm) session
			.getAttribute("editFlatForm");
%>
<body>
<div class="post">
<div class="title">
<%
	if (editForm.getMode() == Mode.EDIT) {
%> <%
 	} else if (editForm.getMode() == Mode.ADD) {
 %> <%
 	}
 %>
</div>
<div class="entry"><html:form action="/editFlat.do" method="post">
	<table>
		<tr>
			<td><bean:message key="flat.number" /></td>
			<td><html:text name="editFlatForm" property="flatNumber"></html:text></td>
		</tr>
		<tr>
			<td><bean:message key="flat.rooms" /></td>
			<td><html:text name="editFlatForm" property="rooms"></html:text></td>
		</tr>
		<tr>
			<td><bean:message key="flat.metters" /></td>
			<td><html:text name="editFlatForm" property="metters"></html:text></td>
		</tr>
		<tr>
			<td><bean:message key="flat.has.balcony" /></td>
			<td><html:checkbox name="editFlatForm" property="balcony"></html:checkbox></td>
		</tr>
		<tr>
			<td><bean:message key="flat.has.toilet" /></td>
			<td><html:checkbox name="editFlatForm" property="separateToilet"></html:checkbox></td>
		</tr>
		<tr>
			<td><bean:message key="flat.description" /></td>
			<td><html:textarea name="editFlatForm" property="description"
				style="height:200px"></html:textarea></td>
		</tr>
		<tr>
			<td><html:submit property="apply">
				<bean:message key="apply" />
			</html:submit></td>
			<td><html:submit property="back">
				<bean:message key="cancel" />
			</html:submit></td>
		</tr>
	</table>

</html:form></div>
</div>
</body>
</html>