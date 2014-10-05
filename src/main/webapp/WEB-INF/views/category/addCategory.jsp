<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#categoryForm").submit(function(e){
		
		var name=$("#name").val();
		var code=$("#code").val();
		var valid=true;
		//检测code
		if(code.length<=0||code.length>10)
		{
			$("#codeError").html("code不能为空，且长度不能大于10");
			e.preventDefault();//禁止数据提交到服务器
		}
		//检测name
		if(name.length<=0||name.length>100)
		{
			$("#nameError").html("name不能为空，且长度不能大于100");
			e.preventDefault();//禁止数据提交到服务器
		}
		if(valid)
		{
			$.ajax({
			  	url: "isDuplicate.json",
			  	data: {
			    		name: name,
			    		code: code
			  	},
			  	async: false, //设置Ajax请求为同步方式
			  	success: function( json ) {
			    		if(json.codeError==true)
			    		{
			    			$("#codeError").html("code重复(Ajax验证)");
			    			e.preventDefault();//禁止数据提交到服务器
			    		}
			    		if(json.nameError==true)
			    		{
			    			$("#nameError").html("name重复(Ajax验证)");
			    			e.preventDefault();//禁止数据提交到服务器
			    		}
			    		
			  	}
			});
		}
	});
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加Category</title>
</head>
<body>
<form id="categoryForm" action="add.do" method="POST">
<p>name:<input id="name" type="text" name="name" value="${name }"><span id="nameError">${nameError}</span></p>
<p>code:<input id="code" type="text" name="code" value="${code }"><span id="codeError">${codeError}</span></p>
<p><input type="submit" value="提交"></p>
</form>
</body>
</html>