<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>用户管理</title>
<script type="text/javascript">
	function back() {
		window.location.href = "${CONTEXT_PATH}/userMgmt/view";
	}
</script>
</head>
<body>
	<form action="${CONTEXT_PATH}/userMgmt/role" method="post" id="form">
		<input type="hidden" name="userId" value="${userId}" />
		<input type="hidden" name="roleIds" />
		<div class="form-horizontal">
			<div class="form-group">
				<select multiple class="form-control" size="10">
					<c:forEach items="${roleList}" var="bean">
						<option id="${bean.role_id}"
							${bean.flag=='0'?'selected="selected"':''}>${bean.role_name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Save</button>
					<button type="button" class="btn btn-default" onclick="back()">Back</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>