<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.github.bgora.beans.entity.User"%>
<%@page import="com.github.bgora.beans.entity.constants.RoleTypes"%>
<%@page import="pl.bgora.utils.ActionParams"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%
	User user = (User) session.getAttribute(ActionParams.CURRENT_USER);
%>
<head>
<html:base />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="STYLESHEET"
	href=" <html:rewrite page="/css/default.css" ></html:rewrite> "
	type="text/css" />
</head>
<body>
<div id="sidebar">
<%
	if (user == null) {
%>
<ul>
	<li>
	<h2><bean:message key="message.welcome.title" /></h2>
	<p><bean:message key="message.welcome.content" /></p>
	</li>
</ul>
<%
	} else if (user.getRole().getRole().equals(RoleTypes.CLIENT)) {
%>
<ul>
	<li>
	<h2><bean:message key="sidebar.menu.title" /></h2>
	<ul>
		<li><a href=" <html:rewrite page="/inbox.do"/> "><bean:message
			key="sidebar.menu.mailbox" /> </a></li>
		<li><a href=" <html:rewrite page="/requestbox.do"/> "><bean:message
			key="sidebar.menu.request" /> </a></li>
		<li><a href=" <html:rewrite page="/myProfile.do" /> "><bean:message
			key="sidebar.menu.editprofile" /> </a></li>
		<li><a href=" <html:rewrite page="/logout.do" /> "><bean:message
			key="sidebar.menu.logout" /> </a></li>
	</ul>
	</li>
</ul>
<%
	} else if (user.getRole().getRole().equals(RoleTypes.ADMIN)) {
%>
<ul>
	<li>
	<h2><bean:message key="sidebar.menu.title" /></h2>
	<ul>
		<li><a href="<html:rewrite page="/inbox.do"/> "><bean:message
			key="sidebar.menu.mailbox" /> </a></li>
		<li><a href=" <html:rewrite page="/usersAdmin.do"/> "><bean:message
			key="sidebar.menu.admin.users" /> </a></li>
		<li><a href=" <html:rewrite page="/buildingAdmin.do"/> "><bean:message
			key="sidebar.menu.admin.buildings" /> </a></li>
		<li><a href=" <html:rewrite page="/personsAdmin.do"/> "><bean:message
			key="sidebar.menu.admin.persons" /> </a></li>
		<li><a href=" <html:rewrite page="/myProfile.do" />"><bean:message
			key="sidebar.menu.editprofile" /></a></li>
		<li><a href=" <html:rewrite page="/cityEdit.do" />"><bean:message
			key="sidebar.menu.cityEdit" /></a></li>
		<li><a href=" <html:rewrite page="/logout.do" /> "><bean:message
			key="sidebar.menu.logout" /> </a></li>
	</ul>
	</li>
</ul>
<%
	} else if (user.getRole().getRole().equals(RoleTypes.USER)) {
%>
<ul>
	<li>
	<h2><bean:message key="sidebar.menu.title" /></h2>
	<ul>
		<li><a href=" <html:rewrite page="/inbox.do"/> "><bean:message
			key="sidebar.menu.mailbox" /> </a></li>
		<li><a href=" <html:rewrite page="/requestbox.do"/> "><bean:message
			key="sidebar.menu.request" /> </a></li>
		<li><a href=" <html:rewrite page="/myProfile.do" /> "><bean:message
			key="sidebar.menu.editprofile" /> </a></li>
		<li><a href=" <html:rewrite page="/logout.do" /> "><bean:message
			key="sidebar.menu.logout" /> </a></li>
	</ul>
	</li>
</ul>
<%
	}
%>
</div>
</body>
</html>