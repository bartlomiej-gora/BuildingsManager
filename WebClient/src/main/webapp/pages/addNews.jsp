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
<html:form action="/addNews.do" method="post">
	<div class="post">
	<div class="title"><bean:message key="news.add.title" /></div>
	<div class="entry">
	<table>
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
			<td colspan="2"><html:textarea rows="20" cols="80" property="content"></html:textarea></td>
		</tr>
		<tr>
			<td colspan="2"><html:submit property="submit">
				<bean:message key="ok" />
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