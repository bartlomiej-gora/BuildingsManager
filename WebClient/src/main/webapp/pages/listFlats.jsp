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
<html:form action="/listFlats.do" method="post">
	<div class="post">
	<div class="title"><bean:message key="page.list.address" /></div>
	<div class="entry"><display:table id="flatsTable"
		name="requestScope.listFlatsForm.flats" pagesize="20" sort="list" style="text-align: center; width:100%">
		<display:column sortable="true" property="address"
			style="text-align: center;" titleKey="address"></display:column>
		<display:column titleKey="choose" paramId="flat_id" paramProperty="id"
			url="/listFlats.do">
			<bean:message key="choose" />
		</display:column>
	</display:table> <p class="meta"><a class="permalink"
	href=" <html:rewrite page="/personEdit.do"/> "><bean:message
	key="back" /></a></p></div>
	</div>
</html:form>
</body>
</html>