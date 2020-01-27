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
<div class="title"><bean:message key="page.contact.title" /></div>
<div class="entry"><bean:message key="page.contact.company.name" /><br />
<bean:message key="page.contact.company.street" /><br />
<bean:message key="page.contact.company.city" /><br />
<bean:message key="page.contact.company.phones" /><br />
(telefon): 123-45-67<br />
(tel./fax): 000-11-22<br />
(telefon): 777-66-55<br />
</div>
</div>

</body>
</html>