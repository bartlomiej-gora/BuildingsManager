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
<body onload="onload()">
<div class="post">
<div class="title"><bean:message key="login.title" /></div>
<div class="entry">
<div style="color: red"><html:messages id="errors" message="false">
	<bean:write name="errors" />
</html:messages></div>
<html:form action="/login.do" method="post" >
	<table>
		<tr>
			<td align="right"><bean:message key="login.login" /></td>
			<td align="left"><html:text property="login"></html:text></td>
		</tr>
		<tr>
			<td align="right"><bean:message key="login.passwd" /></td>
			<td align="left"><html:password property="password"></html:password></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><html:submit>
				<bean:message key="login.submit" />
			</html:submit></td>
		</tr>
	</table>
</html:form> <html:javascript formName="loginForm" />
</div>
<script type="text/javascript">
	function onload() {
		var element = document.getElementsByName("login")[0];
		element.focus();
	}
</script></div>
</body>
</html>