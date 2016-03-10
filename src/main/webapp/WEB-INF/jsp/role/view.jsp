<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>角色管理</title>
<script type="text/javascript">
	function pagination(pageValue) {
		var currentPage = $("input[name='currentPage']").val();
		if (pageValue == 'F') {
			$("input[name='currentPage']").val('1');
		} else if (pageValue == 'P') {
			$("input[name='currentPage']").val(parseInt(currentPage) - 1);
		} else if (pageValue == 'N') {
			$("input[name='currentPage']").val(parseInt(currentPage) + 1);
		} else if (pageValue == 'L') {
			$("input[name='currentPage']").val('${pageBean.totalPage}');
		}
		$("#form").submit();
	}
	function toEdit(roleId) {
		window.location.href = "${CONTEXT_PATH}/roleMgmt/edit/" + roleId;
	}
	function toPermission(roleId) {
		window.location.href = "${CONTEXT_PATH}/roleMgmt/permission/" + roleId;
	}
	function toMenu(roleId) {
		window.location.href = "${CONTEXT_PATH}/roleMgmt/menu/" + roleId;
	}
	function toAdd() {
		window.location.href = "${CONTEXT_PATH}/roleMgmt/add";
	}
</script>
</head>
<body>

	<form action="${CONTEXT_PATH}/roleMgmt/view" method="post" id="form">
		<input type="hidden" name="currentPage" value="${currentPage}" />
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="collapse navbar-collapse">
					<div class="navbar-form navbar-left">
						<div class="input-group">
							<span class="input-group-addon">Role Code</span> <input
								name="roleCode" value="${roleCode}" type="text"
								class="form-control" placeholder="Role Code">
						</div>
						<div class="input-group">
							<span class="input-group-addon">Role Name</span> <input
								name="roleName" value="${roleName}" type="text"
								class="form-control" placeholder="Role Name">
						</div>
					</div>
					<div class="navbar-form navbar-right">
						<button type="submit" class="btn btn-primary">Search</button>
						<button type="button" class="btn btn-default" onclick="toAdd()">Add</button>
					</div>
				</div>
			</div>
		</nav>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>Role Code</th>
					<th>Role Name</th>
					<th>State</th>
					<th>Operation</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.pageList}" var="bean"
					varStatus="status">
					<tr>
						<th scope="row">${status.index + 1}</th>
						<td>${bean.role_code}</td>
						<td>${bean.role_name}</td>
						<td>${bean.state=='0'?'有效':'无效'}</td>
						<td>
							<button type="button" class="btn btn-default btn-xs"
								onclick="toEdit('${bean.role_id}')">Edit</button>
							<button type="button" class="btn btn-default btn-xs"
								onclick="toPermission('${bean.role_id}')">Permission</button>
							<button type="button" class="btn btn-default btn-xs"
								onclick="toMenu('${bean.role_id}')">Menu</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="collapse navbar-collapse">
			<div class="navbar-right">
				<ul class="pagination">
					<li><a href="javascript:void(0)"
						${pageBean.isFirst?'':'onclick=pagination("F")'}
						aria-label="Previous"><span aria-hidden="true">««</span></a></li>
					<li><a href="javascript:void(0)"
						${pageBean.isFirst?'':'onclick=pagination("P")'}>«</a></li>
					<li><a href="javascript:void(0)">共${pageBean.totalCount}条
							${currentPage} / ${pageBean.totalPage}</a></li>
					<li><a href="javascript:void(0)"
						${pageBean.isLast?'':'onclick=pagination("N")'}>»</a></li>
					<li><a href="javascript:void(0)"
						${pageBean.isLast?'':'onclick=pagination("L")'} aria-label="Next"><span
							aria-hidden="true">»»</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</body>
</html>