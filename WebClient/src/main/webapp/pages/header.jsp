<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<head>
<html:base />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="STYLESHEET"
	href=" <html:rewrite page="/css/style.css" ></html:rewrite> "
	type="text/css" />
	<title> <bean:message key="application.title" /> </title>
	
</head>
<body>
<div id="menu">

<ul>
	<li><a href="<html:rewrite page="/welcome.do" />"><bean:message
		key="page.home" /> </a></li>
	<li><a href="<html:rewrite page="/welcome.do?page=contact" /> "><bean:message
		key="page.contact" /> </a></li>
	<li><a href="<html:rewrite page="/welcome.do?page=myaccount" />"><bean:message
		key="page.myaccount" /> </a></li>
	<li><a href="<html:rewrite page="/welcome.do?page=about" />"><bean:message
		key="page.about" /> </a></li>
</ul>
</div>
<div id="logo">
<h1><bean:message key="application.title" /></h1>
</div>
<div id="splash"><img
	src="<html:rewrite page="/css/images/img05.jpg"/> " alt="" /></div>
</body>
</html>