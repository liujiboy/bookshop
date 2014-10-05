<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加Category</title>
</head>
<body>
<form action="add.do" method="POST">
<p>name:<input type="text" name="name" value="${name }">${nameError}</p>
<p>code:<input type="text" name="code" value="${code }">${codeError}</p>
<p><input type="submit" value="提交"></p>
</form>
</body>
</html>