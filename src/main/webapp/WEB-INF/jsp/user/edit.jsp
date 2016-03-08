<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>用户管理</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<form action="">
		<div class="form-horizontal">
			<div class="form-group">
				<label for="userName" class="col-sm-2 control-label">User
					Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="userName"
						value="${user.userName}" placeholder="User Name"
						${updateType=='edit'?'disabled':''} />
				</div>
			</div>
			<div class="form-group">
				<label for="screenName" class="col-sm-2 control-label">Screen
					Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="screenName"
						value="${user.screenName}" placeholder="Screen Name" />
				</div>
			</div>
			<div class="form-group">
				<label for="state" class="col-sm-2 control-label">State</label>
				<div class="col-sm-10">
					<select class="form-control" id="state">
						<option value="">请选择</option>
						<option value="0" ${user.state=='0'?'selected':''}>有效</option>
						<option value="1" ${user.state=='1'?'selected':''}>无效</option>
					</select>
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Save</button>
					<button type="button" class="btn btn-default">Back</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>