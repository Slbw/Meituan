<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="myTag" prefix="mytag"%>
<%@include file="/public/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${ctx}/generator/createCode">
		选择表：<mytag:select id="tableName" name="tableName" querySql="SELECT table_name code,table_name value FROM information_schema.tables WHERE table_schema='meituan'"/>
		<input type="submit" value="生成">
	</form>
</body>
</html>