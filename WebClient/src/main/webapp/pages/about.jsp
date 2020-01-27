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
	<div class="title"><bean:message key="page.about.title"/></div>
	<div class="entry">
	<bean:message key="page.about.prolog"/><br />
	<bean:message key="page.about.used.technology"/><br />
	<bean:message key="page.about.used.client"/><br />
	<a href="http://www.springsource.org/">http://www.springsource.org/</a> <bean:message key="page.about.used.client.spring"/><br />
	<a href="http://struts.apache.org/index.html">http://struts.apache.org/index.html</a> <bean:message key="page.about.used.client.struts"/><br />
	<a href="http://www.freecsstemplates.org/">http://www.freecsstemplates.org/</a> <bean:message key="page.about.used.client.collaboration"/><br />
	<a href="http://tomcat.apache.org/">http://tomcat.apache.org/</a> <bean:message key="page.about.used.client.tomcat"/><br />
	<bean:message key="page.about.used.server"/><br />
	<a href="http://java.sun.com/products/ejb/">http://java.sun.com/products/ejb/</a> <bean:message key="page.about.used.server.ejb"/><br />
	<a href="http://www.jboss.org/jbossas">http://www.jboss.org/jbossas</a> <bean:message key="page.about.used.server.jboss"/><br />
	<bean:message key="page.about.used.tools"/><br />
	<a href="http://www.eclipse.org/">http://www.eclipse.org/</a> <bean:message key="page.about.used.tools.eclipse"/><br />
	<a href="http://springide.org/blog/">http://springide.org/blog/</a> <bean:message key="page.about.used.tools.springide"/><br />
	<a href="http://maven.apache.org/">http://maven.apache.org/</a> <bean:message key="page.about.used.tools.maven"/><br />
	<a href="http://m2eclipse.sonatype.org/">http://m2eclipse.sonatype.org/</a> <bean:message key="page.about.used.tools.m2eclipse"/><br />
	
	</div>
	</div>
</body>
</html>