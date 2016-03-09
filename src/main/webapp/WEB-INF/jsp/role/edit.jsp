<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>角色管理</title>
<script type="text/javascript">
	function back(){
		window.location.href = "${CONTEXT_PATH}/roleMgmt/view";
	}
	$().ready(function() {
		$("#form").validate({
			rules: {
				roleCode: {
					required: true,
					minlength: 2
				},
				roleName: {
					required: true,
					minlength: 2
				},
				state: "required"
			},
			messages: {
				roleCode: {
					required: "Please provide a role code",
					minlength: "Your screen name must be at least 2 characters long"
				},
				roleName: {
					required: "Please enter a role name",
					minlength: "Your role name must consist of at least 2 characters"
				},
				state: "Please select at least 1 state"
			}
		});
	});
</script>
</head>
<body>
	<form action="${CONTEXT_PATH}/roleMgmt/update" method="post" id="form">
		<input type="hidden" name="roleId" value="${role.roleId}" />
		<input type="hidden" name="updateType" value="${updateType}" />
		<div class="form-horizontal">
			<div class="form-group">
				<label for="roleCode" class="col-sm-2 control-label">Role
					Code</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="roleCode"
						value="${role.roleCode}" placeholder="Role Code"
						${updateType=='edit'?'disabled':''} />
				</div>
			</div>
			<div class="form-group">
				<label for="roleName" class="col-sm-2 control-label">Role
					Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="roleName"
						value="${role.roleName}" placeholder="Role Name" />
				</div>
			</div>
			<div class="form-group">
				<label for="state" class="col-sm-2 control-label">State</label>
				<div class="col-sm-10">
					<select class="form-control" name="state">
						<option value="">请选择</option>
						<option value="0" ${role.state=='0'?'selected':''}>有效</option>
						<option value="1" ${role.state=='1'?'selected':''}>无效</option>
					</select>
				</div>
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