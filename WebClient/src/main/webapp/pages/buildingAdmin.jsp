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
<div class="title"><bean:message key="page.admin.buildings" /></div>
<div class="entry"><display:table
	name="requestScope.buildingAdminForm.buildings" pagesize="20"
	sort="list" requestURI="/buildingAdmin.do"
	style="text-align: center; width:100%">
	<display:column sortable="true" titleKey="page.building.street"
		property="streetName" paramId="id" paramProperty="id"
		url="/editBuilding.do" style="text-align: center"></display:column>
	<display:column sortable="true" titleKey="page.building.flatsCount"
		style="text-align: center" property="flatCount"></display:column>
	<display:column sortable="false" style="text-align: center"
		titleKey="page.building.remove" paramId="id" paramProperty="id"
		url="/removeBuilding.do" style="text-align: center">
		<bean:message key="page.users.remove" />
	</display:column>
</display:table>
<p class="meta"><a class="permalink"
	href=" <html:rewrite page="/editBuilding.do?newBuilding=true"/> "><bean:message
	key="page.building.add" /></a></p>
</div>
</div>
</body>
</html>