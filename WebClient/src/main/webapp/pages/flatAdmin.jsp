<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="pl.bgora.forms.FlatAdminForm"%><html
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
</head>
<%
	FlatAdminForm adminForm = (FlatAdminForm) session
			.getAttribute("flatsAdminForm");
%>
<body>
<html:form action="/editFlat.do" method="post">
	<div class="post"> <div class="title"><bean:message
		key="page.admin.flats.title" /> <%=" (" + adminForm.getBuildingName() + ")"%></div>

	<div class="entry"><display:table id="flatsTable"
		name="sessionScope.flatsAdminForm.flats" pagesize="20" sort="list"
		requestURI="/flatsAdmin.do" style="text-align: center; width:100%">
		<display:column sortable="true" property="flatNumber"
			paramProperty="id" paramId="id" url="/editFlat.do"
			style="text-align: center;" titleKey="flat.number"></display:column>
		<display:column sortable="true" property="rooms"
			style="text-align: center;" titleKey="flat.rooms"></display:column>
		<display:column property="metters" sortable="true"
			style="text-align: center;" titleKey="flat.metters"></display:column>
		<display:column property="separateToilet" sortable="false"
			style="text-align: center;" titleKey="flat.has.toilet"
			decorator="pl.bgora.forms.decorator.DisplayTagCheckBoxDecorator">
		</display:column>
		<display:column property="balcony" sortable="false"
			style="text-align: center;" titleKey="flat.has.balcony"
			decorator="pl.bgora.forms.decorator.DisplayTagCheckBoxDecorator">
		</display:column>
		<display:column titleKey="delete" style="text-align: center;"
			sortable="false" paramId="id" paramProperty="id" url="/removeFlat.do">
			<bean:message key="remove" />
		</display:column>

	</display:table> <p class="meta"><a class="permalink"
		href=" <html:rewrite page="/editFlat.do?newFlat=true"/> "><bean:message
		key="page.flat.add" /></a></p> <html:submit property="back">
		<bean:message key="back" />
	</html:submit></div> </div>
</html:form>
</body>
</html>