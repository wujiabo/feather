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
	function save() {
		var roleIds = "";
		$("#roles option:selected").each(function() {
			roleIds = roleIds + $(this).val() + ",";
		});
		$("#roleIds").val(roleIds);
		$("#form").submit();
	}
</script>
</head>
<body>
	<form action="${CONTEXT_PATH}/userMgmt/role" method="post" id="form">
		<input type="hidden" name="userId" value="${userId}" /> <input
			type="hidden" id="roleIds" name="roleIds" />
		<div class="form-horizontal">
			<div class="form-group">
				<select id="roles" multiple class="form-control" size="10">
					<c:forEach items="${roleList}" var="bean">
						<option value="${bean.role_id}"
							${bean.flag=='0'?'selected="selected"':''}>${bean.role_name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-primary" onclick="save()">Save</button>
					<button type="button" class="btn btn-default" onclick="back()">Back</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>