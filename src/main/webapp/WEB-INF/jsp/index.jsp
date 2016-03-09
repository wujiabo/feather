<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>欢迎</title>
</head>
<body>
	<div class="well well-lg">Welcome <shiro:principal /> !!!</div>
</body>
</html>