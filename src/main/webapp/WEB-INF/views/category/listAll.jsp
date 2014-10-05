<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列出所有Category</title>
</head>
<body>
<table border="1" width="500">
	<tr>
		<td>code</td>
		<td>name</td>
	</tr>
	<c:forEach items="${categories }" var="category">
	<tr>
		<td>${category.name }</td>
		<td>${category.code }</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>