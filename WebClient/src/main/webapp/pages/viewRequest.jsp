<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.github.bgora.beans.entity.ProcessPathElement"%>
<%@page import="com.github.bgora.beans.entity.User"%>
<%@page import="com.github.bgora.beans.entity.constants.ProposalStatus"%>
<%@page import="com.github.bgora.beans.entity.constants.RoleTypes"%>
<%@page import="com.sun.corba.se.impl.orbutil.closure.Constant"%>
<%@page import="pl.bgora.forms.ViewRequestForm"%>
<%@page import="pl.bgora.utils.ActionParams"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
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
<body>
<%
	ViewRequestForm requestForm = (ViewRequestForm) session
			.getAttribute("viewRequestForm");
	List<ProcessPathElement> history = requestForm.getHistory();
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	User user = (User) session.getAttribute(ActionParams.CURRENT_USER);
	RoleTypes role = user.getRole().getRole();
	ProposalStatus status = requestForm.getProccess().getStatus();
%>
<div class="post">
<div class="title">
<%
	if (role.equals(RoleTypes.USER)) {
%> <bean:message key="title.request.proccessing" /> <%
 	} else {
 %> <bean:message key="title.request.view" /> <%
 	}
 %>
</div>
<div class="entry"><html:form action="/viewRequest.do">
	<table>
		<tr>
			<td colspan="2"><bean:write name="viewRequestForm"
				property="proccessName" /></td>
		</tr>
		<%
			if (role.equals(RoleTypes.USER)) {
		%>
		<tr>
			<td><bean:message key="author" /></td>
			<td><bean:write name="viewRequestForm" property="author" /></td>
		</tr>
		<%
			} else {
		%>
		<tr>
			<td><bean:message key="status" /></td>
			<td><bean:write name="viewRequestForm" property="status" /></td>
		</tr>
		<tr>
			<td><bean:message key="placement" /></td>
			<td><bean:write name="viewRequestForm" property="placement" /></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td><bean:message key="request.content" /></td>
		</tr>
		<tr>
			<td colspan="2"><html:textarea name="viewRequestForm"
				property="content" readonly="true"
				style="height:200px; width: 400px"></html:textarea></td>
		</tr>
		<tr>
			<%
				if (role.equals(RoleTypes.USER)) {
						if (!status.equals(ProposalStatus.REFUSED)) {
			%>
			<td><bean:message key="process.request" /></td>
			<td colspan="2"><html:submit property="refuse">
				<bean:message key="refuse" />
			</html:submit> <html:submit property="accept">
				<bean:message key="accept" />
			</html:submit></td>
			<%
				} else {
			%>
			<td><html:submit property="accept">
				<bean:message key="accept" />
			</html:submit></td>
			<%
					}
				}
			%>
			<td><html:submit property="back">
				<bean:message key="close" />
			</html:submit></td>
		</tr>
		<%
			if (role.equals(RoleTypes.USER)) {
		%>
		<tr>
			<td><bean:message key="request.comment" /></td>
		</tr>
		<tr>
			<td colspan="2"><html:textarea name="viewRequestForm"
				property="comment" style="height: 200px; width: 400px"></html:textarea></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td><bean:message key="history" /></td>
		</tr>
		<%
			for (ProcessPathElement element : history) {
		%>
		<tr>
			<td><%=fmt.format(element.getCreateDate())%></td>
			<td><%=element.getActuallyResponsible().getLogin()%></td>
		</tr>
		<tr>
			<td colspan="2"><textarea rows="60" cols="80"
				style="height: 200px; width: 400px" readonly="readonly"><%=element.getUserComment()%></textarea></td>
		</tr>
		<%
			}
		%>
	</table>


</html:form></div>
</div>
</body>
</html>