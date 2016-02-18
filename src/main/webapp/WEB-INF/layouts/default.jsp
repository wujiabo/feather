<%@ page contentType="text/html;charset=UTF-8"%>
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
<link href="${ctx}/static/css/default.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx}/static/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css" rel="stylesheet" />
<script src="${ctx}/static/js/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script src="${ctx}/static/zTree/js/jquery.ztree.core-3.5.min.js"
	type="text/javascript"></script>

<sitemesh:head />
</head>

<body>
	<table
		style="width: 100%; height: 100%; border-style: dashed; border-width: 1px;">
		<tr>
			<td colspan="2" align="center"><%@ include
					file="/WEB-INF/layouts/header.jsp"%></td>
		</tr>
		<tr>
			<td width="10%" style="border-style: dashed; border-width: 1px;">
				<shiro:user><%@ include
						file="/WEB-INF/layouts/menu.jsp"%></shiro:user>
			</td>
			<td style="border-style: dashed; border-width: 1px;"><shiro:user>
					<sitemesh:body />
				</shiro:user></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><%@ include
					file="/WEB-INF/layouts/footer.jsp"%></td>
		</tr>
	</table>
</body>
</html>