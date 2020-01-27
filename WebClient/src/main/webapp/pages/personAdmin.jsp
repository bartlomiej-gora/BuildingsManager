<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<body>
<div class="post">
<div class="title"><bean:message key="page.person.admin.title" /></div>
<div class="entry"><display:table id="personsTable"
	requestURI="/personsAdmin.do"
	name="requestScope.personAdminForm.persons" pagesize="20" sort="list"
	style="text-align: center; width:100%">
	<display:column sortable="true" property="surname" paramId="id"
		paramProperty="id" url="/personEdit.do" style="text-align: center"
		titleKey="table.person.surname"></display:column>
	<display:column sortable="true" property="name" paramId="id"
		paramProperty="id" url="/personEdit.do" style="text-align: center"
		titleKey="table.person.name"></display:column>
	<display:column sortable="true" property="streetName"
		style="text-align: center" titleKey="table.person.streetName"></display:column>
	<display:column titleKey="remove" paramId="id" paramProperty="id"
		url="/personRemove.do">
		<bean:message key="remove" />
	</display:column>
</display:table>
<p class="meta"><a class="permalink"
	href=" <html:rewrite page="/personEdit.do?newPerson=true"/> "><bean:message
	key="page.person.add" /></a></p>
</div>
</div>

</body>
</html>