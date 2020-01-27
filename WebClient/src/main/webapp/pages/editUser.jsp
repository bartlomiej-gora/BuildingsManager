<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="pl.bgora.forms.EditUserForm"%>
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
<%
	EditUserForm editForm = (EditUserForm) session
			.getAttribute("editUserForm");
%>
</head>
<body>
<div class="post">
<div class="title">
<%
	if (editForm.getMode().equals(EditUserForm.UserFormMode.EDIT)) {
%> <bean:message key="page.edit.user" /> <%
 	} else if (editForm.getMode().equals(EditUserForm.UserFormMode.ADD)) {
 %> <bean:message key="page.add.user" /> <%
 	}
 %>
</div>
<div class="entry">
<div style="color: red"><html:messages id="msg" message="true">
	<bean:write name="msg" />
</html:messages></div>
<html:form action="/editUser.do" method="post"
	onsubmit="return validateLoginForm(this);">
	<table>
		<tr>
			<td><bean:message key="page.myaccount.login" /></td>
			<td><html:text name="editUserForm" property="login" /></td>
		</tr>
		<tr>
			<td><bean:message key="page.myaccount.newpasswd" /></td>
			<td><html:password name="editUserForm" property="newPasswd"></html:password></td>
		</tr>
		<tr>
			<td><bean:message key="page.myaccount.confirmpasswd" /></td>
			<td><html:password name="editUserForm" property="confirmPasswd"></html:password></td>
		</tr>
		<tr>
			<td><bean:message key="page.user.role" /></td>
			<td><html:select name="editUserForm" property="role">
				<html:options property="roles" />
			</html:select></td>
		</tr>
		<tr>
			<td><bean:message key="page.user.group" /></td>
			<td><html:select name="editUserForm" property="group">
				<html:options property="groups" />
			</html:select></td>
		</tr>
		<tr>
			<td colspan="2">
			<table>
				<tr>
					<th colspan="2"><bean:message key="page.user.person" /></th>
				</tr>
				<tr>
					<td><bean:message key="person.name" /></td>
					<td><html:text name="editUserForm" property="name"
						disabled="true"></html:text></td>
				</tr>
				<tr>
					<td><bean:message key="person.surname" /></td>
					<td><html:text name="editUserForm" property="surname"
						disabled="true"></html:text></td>
				</tr>
				<tr>
					<td colspan="1" align="right"><html:submit
						property="findPerson">
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
</html:form> <html:javascript formName="editUserForm" /></div>
</div>
</body>
</html>