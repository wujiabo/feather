<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>权限管理</title>
<script type="text/javascript">
	$(document).ready(function() {
		var zTree;
		var setting = {
				view: {
					dblClickExpand: false,
					showLine: true,
					selectedMulti: false
				},
				data: {
					simpleData: {
						enable:true,
						idKey: "id",
						pIdKey: "pId",
						rootPId: ""
					}
				},
				callback: {
					beforeClick: function(treeId, treeNode) {
						var zTree = $.fn.zTree.getZTreeObj("tree");
						if (treeNode.isParent) {
							zTree.expandNode(treeNode);
							return false;
						} else {
						//	demoIframe.attr("src",treeNode.file + ".html");
							return true;
						}
					}
				}
			};
		
		var zNodes = eval('${permissionJson}');
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting, zNodes);
		var zTree = $.fn.zTree.getZTreeObj("tree");

	});
	function toEdit(permissionId) {
		window.location.href = "${CONTEXT_PATH}/permissionMgmt/edit/"
				+ permissionId;
	}
	function toAdd() {
		window.location.href = "${CONTEXT_PATH}/permissionMgmt/add";
	}
</script>
</head>
<body>

	<form action="${CONTEXT_PATH}/permissionMgmt/view" method="post"
		id="form">
		<input type="hidden" name="currentPage" value="${currentPage}" />
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="collapse navbar-collapse">
					<div class="navbar-form navbar-left">
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


		<ul id="tree" class="ztree" style="width: 260px; overflow: auto;"></ul>

		<c:if test="${not empty message}">
			<div class="alert alert-success">
				<button data-dismiss="alert" class="close">×</button>${message}</div>
		</c:if>
	</form>
</body>
</html>