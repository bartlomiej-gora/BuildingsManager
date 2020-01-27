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
<div class="title"><bean:message key="page.city.edit" /></div>
<div class="entry"><html:form action="/cityEdit.do" method="post">
	<table>
		<tr>
			<td colspan="2"><bean:message key="page.city.actualCities" /></td>
			<td><bean:message key="page.city.editName" /></td>
		</tr>
		<tr>
			<td rowspan="1" colspan="2"><html:select property="city"
				size="20" style="height: 200px; width: 220px"
				onclick="onCitySelect()">
				<html:options property="cities" />
			</html:select></td>
			<td valign="top"><html:text property="editedName"></html:text></td>
			<td valign="top"><html:submit property="editCity"
				onclick="checkCityNull()">
				<bean:message key="apply" />
			</html:submit></td>
		</tr>
		<tr>
			<td><html:text property="newNameText"></html:text></td>
			<td><html:submit property="addCity">
				<bean:message key="page.city.add" />
			</html:submit></td>
		</tr>
		<tr>
			<td colspan="3">
			<div style="color: red;"><html:messages id="errors"
				message="false">
				<bean:write name="errors" />
			</html:messages></div>
			</td>
		</tr>

	</table>

</html:form></div>
</div>
<script type="text/javascript">
	function onCitySelect() {
		var list = document.getElementsByName('city')[0];
		var selected = list.selectedIndex;
		var cityName = list.options[selected].value;

		var nameField = document.getElementsByName('editedName')[0];
		nameField.value = cityName;
	}
</script>

</body>
</html>