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
<div class="title"><bean:message key="documents.title" /></div>
<div class="entry"><display:table id="documentsTable"
	name="requestScope.DocumentsForm.documents" pagesize="20" sort="list">
	<display:column property="title" sortable="true"
		href="showDocument.jsp" paramId="id" paramName="documents"
		paramProperty="documentId" titleKey="documents.column.title"></display:column>
	<display:column property="version" sortable="false" titleKey="documents.column.version"></display:column>
	<display:column property="createDate" sortable="true" titleKey="documents.column.createDate"></display:column>
	<display:column property="expirationDate" sortable="true" titleKey="documents.column.expirationDate"></display:column>
</display:table></div>
</div>

</body>
</html>