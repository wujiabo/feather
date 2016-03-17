<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>用户组管理</title>
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

	var zNodes = eval('${groupJson}');

	function beforeClick(treeId, treeNode) {
		var check = (treeNode && !treeNode.isParent);
		return true;
	}

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("groupTree"), nodes = zTree
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

		var groupObj = $("#groupParentName");
		groupObj.attr("value", v);

		var groupIdObj = $("#groupPid");
		groupIdObj.attr("value", ids);
		hideMenu();
	}

	function showGroup() {
		var groupObj = $("#groupParentName");
		var groupOffset = $("#groupParentName").offset();
		$("#groupContent").css({
			left : groupOffset.left + "px",
			top : groupOffset.top + groupObj.outerHeight() + "px"
		}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#groupContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "groupParentName"
				|| event.target.id == "groupContent" || $(event.target)
				.parents("#groupContent").length > 0)) {
			hideMenu();
		}
	}

	$(document)
			.ready(
					function() {
						$.fn.zTree.init($("#groupTree"), setting, zNodes);

						var zTree = $.fn.zTree.getZTreeObj("groupTree");
						zTree.expandAll(true);

						$("#form")
								.validate(
										{
											rules : {
												groupCode : {
													required : true,
													minlength : 2
												},
												groupName : {
													required : true,
													minlength : 2
												},
												state : "required"
											},
											messages : {
												groupCode : {
													required : "Please provide a group code",
													minlength : "Your screen name must be at least 2 characters long"
												},
												groupName : {
													required : "Please enter a group name",
													minlength : "Your group name must consist of at least 2 characters"
												},
												state : "Please select at least 1 state"
											}
										});
					});
	function back() {
		window.location.href = "${CONTEXT_PATH}/groupMgmt/view";
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
	<form action="${CONTEXT_PATH}/groupMgmt/update" method="post"
		id="form">
		<input type="hidden" name="groupId"
			value="${group.groupId}" /> <input type="hidden"
			name="updateType" value="${updateType}" /> <input type="hidden" id="groupPid"
			name="groupPid" />
		<div class="form-horizontal">
			<c:if test="${updateType=='add'}">
				<div class="form-group">
					<label for="parentGroupName" class="col-sm-2 control-label">Parent
						Group</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="groupParentName" readonly="readonly"
							onclick="showGroup(); return false;" name="groupParentName"
							placeholder="Parent Group" />
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<label for="groupCode" class="col-sm-2 control-label">Group
					Code</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="groupCode"
						value="${group.groupCode}" placeholder="Group Code" />
				</div>
			</div>
			<div class="form-group">
				<label for="groupName" class="col-sm-2 control-label">Group
					Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="groupName"
						value="${group.groupName}" placeholder="Group Name" />
				</div>
			</div>
			<div class="form-group">
				<label for="state" class="col-sm-2 control-label">State</label>
				<div class="col-sm-10">
					<select class="form-control" name="state">
						<option value="">请选择</option>
						<option value="0" ${group.state=='0'?'selected':''}>有效</option>
						<option value="1" ${group.state=='1'?'selected':''}>无效</option>
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
		<div id="groupContent" class="groupContent"
			style="display: none; position: absolute;">
			<ul id="groupTree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
		</div>
	</form>
</body>
</html>