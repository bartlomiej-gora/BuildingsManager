<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="pl.bgora.forms.PersonEditForm"%><html
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
	PersonEditForm editForm = (PersonEditForm) session
			.getAttribute("personEditForm");
%>
</head>
<body>
<div class="post">
<div class="title">
<%
	if (editForm.getMode().equals(PersonEditForm.Mode.ADD)) {
%> <bean:message key="page.person.add.title" /> <%
 	} else if (editForm.getMode().equals(PersonEditForm.Mode.EDIT)) {
 %><bean:message key="page.person.edit.title" /> <%
 	}
 %>
</div>
<div class="entry"><html:form action="/personEdit.do"
	method="post">
	<!--Dalsza część pliku....  -->
	<table>
		<tr>
			<td><bean:message key="person.name" /></td>
			<td><html:text name="personEditForm" property="name"></html:text></td>
		</tr>
		<tr>
			<td><bean:message key="person.surname" /></td>
			<td><html:text name="personEditForm" property="surname"></html:text></td>
		</tr>
		<tr>
			<td><bean:message key="person.pesel" /></td>
			<td><html:text name="personEditForm" property="pesel"></html:text></td>
		</tr>
		<tr>
			<td colspan="2">
			<table>
				<tr>
					<th><bean:message key="person.address" /></th>
				</tr>
				<tr>
					<td><bean:message key="person.address" /></td>
					<td><html:textarea name="personEditForm" property="address" disabled="true"></html:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="1" align="right"><html:submit
						property="findAddress">
						<bean:message key="search" />
					</html:submit></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2"><html:submit property="apply">
				<bean:message key="ok" />
			</html:submit> <html:cancel property="cancel">
				<bean:message key="cancel" />
			</html:cancel></td>
		</tr>
	</table>
</html:form></div>
</div>
</body>
</html>