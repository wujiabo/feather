<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>Feather:<sitemesh:title /></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link type="image/x-icon" href="${ctx}/static/images/favicon.ico"
	rel="shortcut icon">
<link href="${ctx}/static/css/bootstrap.min.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx}/static/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css" rel="stylesheet" />
<script src="${ctx}/static/js/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/static/zTree/js/jquery.ztree.all-3.5.min.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/rest.js" type="text/javascript"></script>
<style type="text/css">
/* Sticky footer styles
-------------------------------------------------- */
html {
	position: relative;
	min-height: 100%;
}

body {
	/* Margin bottom by footer height */
	margin-bottom: 60px;
}

.footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	/* Set the fixed height of the footer here */
	height: 60px;
	background-color: #f5f5f5;
}

/* Custom page CSS
-------------------------------------------------- */
/* Not required for template or sticky footer method. */
body>.container {
	padding: 60px 15px 0;
}

.container .text-muted {
	margin: 20px 0;
}

.footer>.container {
	padding-right: 15px;
	padding-left: 15px;
}

code {
	font-size: 80%;
}

label.error {
	color: red;
}
</style>
<sitemesh:head />
</head>

<body>
	<%@ include file="/WEB-INF/layouts/header.jsp"%>

	<!-- Begin page content -->
	<div class="container">
		<shiro:user>
			<c:if test="${not empty message}">
				<div class="alert alert-success">
					<button data-dismiss="alert" class="close">×</button>${message}</div>
			</c:if>
			<c:if test="${not empty error}">
				<div class="alert alert-danger">
					<button data-dismiss="alert" class="close">×</button>${error}</div>
			</c:if>
			<sitemesh:body />
		</shiro:user>
	</div>

	<%@ include file="/WEB-INF/layouts/footer.jsp"%>
</body>
</html>