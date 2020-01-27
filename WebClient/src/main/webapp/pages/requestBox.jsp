<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.github.bgora.beans.entity.User"%>

<%@page import="com.github.bgora.beans.entity.constants.RoleTypes"%>
<%@page import="pl.bgora.utils.ActionParams"%>
<html
		xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<head>
<html:base />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="STYLESHEET"
	href=" <html:rewrite page="/css/default.css" ></html:rewrite> "
	type="text/css" />
<%
	User user = (User) session.getAttribute(ActionParams.CURRENT_USER);
%>
</head>
<body>
<div class="post">
<div class="title"><bean:message key="page.request.box.title" /></div>
<div class="entry"><display:table id="requestTable"
	requestURI="/requestbox.do"
	name="sessionScope.requestBoxForm.processes" pagesize="20" sort="list"
	style="text-align: center; width:100%">
	<display:column sortable="true" property="createDate"
		style="text-align: center" titleKey="request.date" decorator="pl.bgora.forms.decorator.DateDecorator"></display:column>
	<display:column sortable="true" property="processName" paramId="id"
		paramProperty="id" url="/viewRequest.do" style="text-align: center"
		titleKey="process.name"></display:column>
	<display:column sortable="true" property="status"
		style="text-align: center" titleKey="request.status"></display:column>
	<display:column sortable="true" property="placement"
		style="text-align: center" titleKey="request.placement"></display:column>
</display:table> <%
 	if (user.getRole().getRole().equals(RoleTypes.CLIENT)) {
 %>
<p class="meta"><a class="permalink"
	href=" <html:rewrite page="/addRequest.do"/> "><bean:message
	key="request.add" /></a></p>
<%
	}
%>
</div>
</div>
</body>
</html>