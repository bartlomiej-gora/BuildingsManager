<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
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
<div class="title"><bean:message key="message.box.title" /></div>
<div class="entry">
<p class="meta"><a href=" <html:rewrite page="/inbox.do"/>  " /> <bean:message
	key="inbox" />&nbsp;<a style="background: black; color: white"
	href="<html:rewrite page="/sentbox.do"/>"> <bean:message key="sent" />
</a></p>
<display:table name="sessionScope.sentBoxForm.messages" pagesize="20"
	sort="list" style="text-align: center; width:100%">
	<display:column property="title" sortable="true"
		style="text-align: center" titleKey="message.title"
		url="/showEmail.do" paramProperty="messageId" paramId="id"></display:column>
	<display:column property="to" titleKey="message.to" sortable="true"
		style="text-align: center"></display:column>
	<display:column property="sendDate" titleKey="message.send.date"
		sortable="true" style="text-align: center" decorator="pl.bgora.forms.decorator.DateDecorator"></display:column>
</display:table>
<p class="meta"><a class="permalink"
	href=" <html:rewrite page="/createEmail.do" /> "> <bean:message
	key="create.mail" /> </a></p>
</div>
</div>
</body>
</html>