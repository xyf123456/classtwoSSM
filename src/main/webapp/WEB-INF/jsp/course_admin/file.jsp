<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我不是真正的青鸟云豆网</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/statics/css/main.css" type="text/css" />
</head>
<body>
<div align="center">

<h1>上传附件</h1>
<form method="post" action="<%=request.getContextPath()%>/upload/doUpload" enctype="multipart/form-data">
<input type="file" name="file1"/>
<input type="submit"/>
</form>
</div>
</body>
</html>