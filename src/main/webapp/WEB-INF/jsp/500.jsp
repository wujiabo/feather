<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>500</title>
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico"
	rel="shortcut icon">
<style type="text/css" media="screen">
body {
	background-color: #f1f1f1;
	margin: 0;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.container {
	margin: 50px auto 40px auto;
	width: 600px;
	text-align: center;
}

a {
	color: #4183c4;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

h1 {
	width: 800px;
	position: relative;
	left: -100px;
	letter-spacing: -1px;
	line-height: 60px;
	font-size: 60px;
	font-weight: 100;
	margin: 0px 0 50px 0;
	text-shadow: 0 1px 0 #fff;
}

p {
	color: rgba(0, 0, 0, 0.5);
	margin: 20px 0;
	line-height: 1.6;
}


#suggestions {
	margin-top: 35px;
	color: #ccc;
}

#suggestions a {
	color: #666666;
	font-weight: 200;
	font-size: 14px;
	margin: 0 10px;
}
</style>
</head>
<body>
	<div class="container">
		<h1>500</h1>
		<p>
			<strong>Feather error</strong>
		</p>

		<div id="suggestions">
			<a href="${ctx}">Feather</a> &mdash; <a
				href="${ctx}">@Feather</a>
		</div>
	</div>
</body>
</html>