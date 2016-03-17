<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>权限管理</title>
<script type="text/javascript">
	var setting = {
		view : {
			dblClickExpand : false,
			showIcon : false
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
				.getSelectedNodes(), v = "", ids = "";
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

	function showPermission() {
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
				|| event.target.id == "permissionContent" || $(event.target)
				.parents("#permissionContent").length > 0)) {
			hideMenu();
		}
	}

	$(document)
			.ready(
					function() {
						$.fn.zTree.init($("#permissionTree"), setting, zNodes);
						var zTree = $.fn.zTree.getZTreeObj("permissionTree");
						zTree.expandAll(true);

						$("#form")
								.validate(
										{
											rules : {
												permissionCode : {
													required : true,
													minlength : 2
												},
												permissionName : {
													required : true,
													minlength : 2
												},
												state : "required"
											},
											messages : {
												permissionCode : {
													required : "Please provide a permission code",
													minlength : "Your screen name must be at least 2 characters long"
												},
												permissionName : {
													required : "Please enter a permission name",
													minlength : "Your permission name must consist of at least 2 characters"
												},
												state : "Please select at least 1 state"
											}
										});
					});
	function back() {
		window.location.href = "${CONTEXT_PATH}/permissionMgmt/view";
	}
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
	<form action="${CONTEXT_PATH}/permissionMgmt/update" method="post"
		id="form">
		<input type="hidden" name="permissionId"
			value="${permission.permissionId}" /> <input type="hidden"
			name="updateType" value="${updateType}" /> <input type="hidden" id="permissionPid"
			name="permissionPid" />
		<div class="form-horizontal">
			<c:if test="${updateType=='add'}">
				<div class="form-group">
					<label for="parentPermissionName" class="col-sm-2 control-label">Parent
						Permission</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="permissionParentName" readonly="readonly"
							onclick="showPermission(); return false;" name="permissionParentName"
							placeholder="Parent Permission" />
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<label for="permissionCode" class="col-sm-2 control-label">Permission
					Code</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="permissionCode"
						value="${permission.permissionCode}" placeholder="Permission Code" />
				</div>
			</div>
			<div class="form-group">
				<label for="permissionName" class="col-sm-2 control-label">Permission
					Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="permissionName"
						value="${permission.permissionName}" placeholder="Permission Name" />
				</div>
			</div>
			<div class="form-group">
				<label for="state" class="col-sm-2 control-label">State</label>
				<div class="col-sm-10">
					<select class="form-control" name="state">
						<option value="">请选择</option>
						<option value="0" ${permission.state=='0'?'selected':''}>有效</option>
						<option value="1" ${permission.state=='1'?'selected':''}>无效</option>
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
		<div id="permissionContent" class="permissionContent"
			style="display: none; position: absolute;">
			<ul id="permissionTree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
		</div>
	</form>
</body>
</html>