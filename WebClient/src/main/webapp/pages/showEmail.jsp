<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.github.bgora.beans.entity.Attachement"%>
<%@page import="com.github.bgora.beans.entity.Message"%>
<%@page import="pl.bgora.forms.ShowEmailForm"%>
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
	ShowEmailForm emailForm = (ShowEmailForm) session
			.getAttribute(Constants.SHOW_EMAIL_FORM);
	Message message = emailForm.getMessage();
%>
</head>
<body>
<html:form action="/showEmail.do" method="post"
	enctype="multipart/form-data">
	<div class="post">
	<div class="title"><bean:message key="show.message" /></div>
	<div class="entry">
	<table>
		<tr>
			<td><bean:message key="message.from" /></td>
			<td><bean:write name="showEmailForm" property="from" /></td>
		</tr>
		<tr>
			<td><bean:message key="title" /></td>
		</tr>
		<tr>
			<td colspan="2"><html:text property="title" disabled="true"></html:text></td>
		</tr>
		<tr>
			<td><bean:message key="content" /></td>
		</tr>
		<tr>
			<td colspan="2"><html:textarea property="content" rows="20"
				cols="80" disabled="true"></html:textarea></td>
		</tr>
		<tr>
			<td colspan="2"><bean:message key="attachements" /></td>
		</tr>
		<%
			List<Attachement> attachements = emailForm.getAttachements();
				for (Attachement attach : attachements) {
					String temp = "/getAttachement.do?messageId="+message.getMessageId()+"&attachName="
							+ attach.getFilename();
		%>
		<tr>
			<td><%=attach.getFilename()%></td>
			<td><a href=" <html:rewrite page ="<%=temp%>" />" target="_blank"> <bean:message
				key="download" /> </a></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td colspan="2"><html:submit property="cancel">
				<bean:message key="close" />

			</html:submit></td>
		</tr>
	</table>

	</div>
	</div>
</html:form>
</body>
</html>