<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.github.bgora.beans.entity.News"%>
<%@page import="com.github.bgora.beans.entity.User"%>
<%@page import="com.github.bgora.beans.entity.constants.RoleTypes"%>
<%@page import="pl.bgora.forms.WelcomeForm"%>
<%@page import="pl.bgora.utils.ActionParams"%>
<%@page import="java.text.SimpleDateFormat"%>
<html
		xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<head>
<html:base />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="STYLESHEET"
	href=" <html:rewrite page="/css/default.css" ></html:rewrite> "
	type="text/css" />
<%
	WelcomeForm welcomeForm = (WelcomeForm) request
			.getAttribute("welcomeForm");
	int listSize = welcomeForm.getRecentNews().size();
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	User user = (User) session.getAttribute(ActionParams.CURRENT_USER);
%>
</head>
<body>
<html:form action="addNews.do" method="post">
	<div class="post">
	<div class="title"><bean:message key="news.title"/> </div>
	<%
		for (int i = 0; i < listSize; i++) {
				News news = welcomeForm.getRecentNews().get(i);
	%>
	<div class="post">
	<h2 class="title"><%=news.getTitle()%></h2>
	<div class="entry"><%=news.getContent()%></div>
	<p class="meta"><span class="posted"> <%=(String) fmt.format(news.getNewsDate()) + " "
							+ news.getUser().getLogin()%> </span></p>

	</div>
	<%
		}

			if (user != null
					&& (user.getRole().getRole().equals(RoleTypes.ADMIN) || user
							.getRole().getRole().equals(RoleTypes.USER))) {
	%>
	<html:submit property="addNews">
		<bean:message key="add" />
	</html:submit>
	<%
		}
	%>
	</div>
</html:form>
</body>
</html>