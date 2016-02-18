<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<table style="width: 100%">
	<tr>
		<td><a href="${ctx}">Feather</a></td>

		<td align="right"><shiro:user>[ <shiro:principal /> ]<a
					href="${pageContext.request.contextPath}/logout">退出</a>
			</shiro:user></td>
	</tr>
</table>
