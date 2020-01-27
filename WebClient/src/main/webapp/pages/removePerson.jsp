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
<div class="title"></div>
<div class="entry"><html:form action="/removePerson.do"
	method="post">
	<table>
		<tr>
			<td colspan="2" align="center"><bean:message
				key="person.remove.preamble" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><b><bean:write
				name="removePersonForm" property="name" /></b></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><bean:message
				key="remove.question" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><html:submit property="submit">
				<bean:message key="yes" />
			</html:submit> <html:submit property="cancel">
				<bean:message key="no" />
			</html:submit></td>
		</tr>
	</table>
</html:form></div>
</div>
</body>
</html>