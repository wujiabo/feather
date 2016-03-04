<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>用户管理</title>
</head>
<body>
	<form action="">
		<table border="1">
			<tr>
				<td>user name</td>
				<td>screen name</td>
				<td>state</td>
				<td>operation</td>
			</tr>
			<c:forEach items="${pageBean.pageList}" var="bean">
				<tr>
					<td>${bean.user_name}</td>
					<td>${bean.screen_name}</td>
					<td>${bean.state}</td>
					<td></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>