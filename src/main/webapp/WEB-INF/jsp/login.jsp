<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>登录</title>
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico"
	rel="shortcut icon">
<script src="${ctx}/static/js/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<style>
.error {
	color: red;
}
</style>
</head>
<body>

	<form action="" method="post">
		<div align="center">
			<div class="error">${error}</div>
			<table>
				<tr>
					<td>用户名</td>
					<td><input type="text" name="username"
						value="<shiro:principal/>"></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td>自动登录</td>
					<td><input type="checkbox" name="rememberMe" value="true"><input
						type="submit" value="登录"></td>
				</tr>
			</table>
		</div>

	</form>
</body>
</html>