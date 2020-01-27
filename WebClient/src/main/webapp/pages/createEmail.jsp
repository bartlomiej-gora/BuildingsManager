<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.github.bgora.beans.entity.Attachement"%>
<%@page import="pl.bgora.forms.CreateEmailForm"%>
<%@page import="pl.bgora.utils.Constants"%>
<%@page import="java.util.List"%><html
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
	CreateEmailForm emailForm = (CreateEmailForm) session
			.getAttribute(Constants.CREATE_EMAIL_FORM);
%>
</head>
<body>
<html:form action="/createEmail.do" method="post"
	enctype="multipart/form-data">
	<div class="post">
	<div class="title"><bean:message key="create.message" /></div>
	<div class="entry">
	<table>
		<tr>
			<td><bean:message key="message.to" /></td>
			<td><html:select property="choosedTo" >
				<html:options property="users" />
			</html:select></td>
		</tr>
		<tr>
			<td><bean:message key="title" /></td>
		</tr>
		<tr>
			<td colspan="2"><html:text property="title"></html:text></td>
		</tr>
		<tr>
			<td><bean:message key="content" /></td>
		</tr>
		<tr>
			<td colspan="2"><html:textarea property="content" rows="20"
				cols="80"></html:textarea></td>
		</tr>
		<tr>
			<td colspan="2"><bean:message key="attachements" /></td>
		</tr>
		<%
			List<Attachement> attachements = emailForm.getAttachements();
				for (Attachement attach : attachements) {
					String temp = "/createEmail.do?remove=true&attachName=" +attach.getFilename();
		%>
		<tr>
			<td><%=attach.getFilename()%></td>
			<td><a
				href=" <html:rewrite page ="<%=temp%>" />"> <bean:message key="remove"/>
			</a></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td colspan="2"><bean:message key="filename" /> <html:file
				property="attachement"></html:file> <html:submit property="addFile">
				<bean:message key="add.file" />
			</html:submit></td>
		</tr>
		<tr>
			<td colspan="2"><html:submit property="submit">
				<bean:message key="send" />
			</html:submit> <html:submit property="cancel">
				<bean:message key="cancel" />

			</html:submit></td>
		</tr>
	</table>

	</div>
	</div>
</html:form>
</body>
</html>