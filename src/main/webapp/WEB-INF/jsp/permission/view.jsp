<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>权限管理</title>
<script type="text/javascript">
	var setting = {
		view : {
			dblClickExpand : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			beforeClick : beforeClick,
			onClick : onClick
		}
	};

	var zNodes = eval('${permissionJson}');

	function beforeClick(treeId, treeNode) {
		var check = (treeNode && !treeNode.isParent);
		return true;
	}

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("permissionTree"), nodes = zTree
				.getSelectedNodes(), v = "",ids = "";
		nodes.sort(function compare(a, b) {
			return a.id - b.id;
		});
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].name + ",";
			ids += nodes[i].id + ",";
		}
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		if (ids.length > 0)
			ids = ids.substring(0, ids.length - 1);
		

		var permissionObj = $("#permissionParentName");
		permissionObj.attr("value", v);
		
		var permissionIdObj = $("#permissionPid");
		permissionIdObj.attr("value", ids);
		hideMenu();
	}

	function showMenu() {
		var permissionObj = $("#permissionParentName");
		var permissionOffset = $("#permissionParentName").offset();
		$("#permissionContent").css({
			left : permissionOffset.left + "px",
			top : permissionOffset.top + permissionObj.outerHeight() + "px"
		}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#permissionContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "permissionParentName"
				|| event.target.id == "permissionContent" || $(event.target).parents(
				"#permissionContent").length > 0)) {
			hideMenu();
		}
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#permissionTree"), setting, zNodes);
	});
</script>
<style type="text/css">
ul.ztree {
	margin-top: 10px;
	border: 1px solid #617775;
	background: #f0f6e4;
	width: 220px;
	height: 360px;
	overflow-y: scroll;
	overflow-x: auto;
}
</style>
</head>
<body>

	<form action="${CONTEXT_PATH}/permissionMgmt/view" method="post"
		id="form">
		<input type="hidden" name="currentPage" value="${currentPage}" />
		<input type="hidden" id="permissionPid" name="permissionPid" value="${permissionPid}" />
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="collapse navbar-collapse">
					<div class="navbar-form navbar-left">
						<div class="input-group">
							<span class="input-group-addon">Parent Permission</span> <input
								name="permissionParentName" value="${permissionParentName}"
								type="text" readonly="readonly" id="permissionParentName"
								onclick="showMenu(); return false;" class="form-control"
								placeholder="Parent Permission">
						</div>
						<div class="input-group">
							<span class="input-group-addon">Permission Code</span> <input
								name="permissionCode" value="${permissionCode}" type="text"
								class="form-control" placeholder="Permission Code">
						</div>
						<div class="input-group">
							<span class="input-group-addon">Permission Name</span> <input
								name="permissionName" value="${permissionName}" type="text"
								class="form-control" placeholder="Permission Name">
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
					<th>Permission Code</th>
					<th>Permission Name</th>
					<th>State</th>
					<th>Operation</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.pageList}" var="bean"
					varStatus="status">
					<tr>
						<th scope="row">${status.index + 1}</th>
						<td>${bean.permission_code}</td>
						<td>${bean.permission_name}</td>
						<td>${bean.state=='0'?'有效':'无效'}</td>
						<td>
							<button type="button" class="btn btn-default btn-xs"
								onclick="toEdit('${bean.permission_id}')">Edit</button>
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

		<div id="permissionContent" class="permissionContent"
			style="display: none; position: absolute;">
			<ul id="permissionTree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
		</div>

		<c:if test="${not empty message}">
			<div class="alert alert-success">
				<button data-dismiss="alert" class="close">×</button>${message}</div>
		</c:if>
	</form>
</body>
</html>