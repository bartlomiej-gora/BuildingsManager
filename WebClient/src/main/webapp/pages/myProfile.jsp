<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<div class="post">
<h2 class="title"><bean:message key="page.myaccount.title" /></h2>
<div style="color: red"><html:messages id="errors" message="false">
	<bean:write name="errors" />
</html:messages></div>
<div style="color: green;"><html:messages id="msg" message="true">
	<bean:write name="msg" />
</html:messages></div>
<div class="entry"><html:form action="/myProfile.do" method="post">
	<table>
		<tr>
			<td><bean:message key="page.myaccount.login" /></td>
			<td><bean:write name="profileForm" property="login" /></td>
		</tr>
		<tr>
			<td><bean:message key="page.myaccount.oldpasswd" /></td>
			<td><html:password name="profileForm" property="oldPasswd"></html:password></td>
		</tr>
		<tr>
			<td><bean:message key="page.myaccount.newpasswd" /></td>
			<td><html:password name="profileForm" property="newPasswd"></html:password></td>
		</tr>
		<tr>
			<td><bean:message key="page.myaccount.confirmpasswd" /></td>
			<td><html:password name="profileForm" property="confirmPasswd"></html:password></td>
		</tr>
		<tr>
			<td colspan="2"><html:submit property="submit">
				<bean:message key="apply" />
			</html:submit></td>
		</tr>
	</table>

</html:form></div>
</div>
</body>
</html>