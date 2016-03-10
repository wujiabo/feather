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

	var zNodes = eval('${userGroupJson}');

	function beforeClick(treeId, treeNode) {
		var check = (treeNode && !treeNode.isParent);
		return true;
	}

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("userGroupTree"), nodes = zTree
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

		var userGroupObj = $("#userGroupParentName");
		userGroupObj.attr("value", v);

		var userGroupIdObj = $("#userGroupPid");
		userGroupIdObj.attr("value", ids);
		hideMenu();
	}

	function showUserGroup() {
		var userGroupObj = $("#userGroupParentName");
		var userGroupOffset = $("#userGroupParentName").offset();
		$("#userGroupContent").css({
			left : userGroupOffset.left + "px",
			top : userGroupOffset.top + userGroupObj.outerHeight() + "px"
		}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#userGroupContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "userGroupParentName"
				|| event.target.id == "userGroupContent" || $(event.target)
				.parents("#userGroupContent").length > 0)) {
			hideMenu();
		}
	}

	$(document)
			.ready(
					function() {
						$.fn.zTree.init($("#userGroupTree"), setting, zNodes);

						$.fn.zTree.getZTreeObj("userGroupTree").selectNode(
								$.fn.zTree.getZTreeObj("userGroupTree")
										.getNodeByParam("id",
												'${userGroupPid}'));

						$("#form")
								.validate(
										{
											rules : {
												userGroupCode : {
													required : true,
													minlength : 2
												},
												userGroupName : {
													required : true,
													minlength : 2
												},
												state : "required"
											},
											messages : {
												userGroupCode : {
													required : "Please provide a userGroup code",
													minlength : "Your screen name must be at least 2 characters long"
												},
												userGroupName : {
													required : "Please enter a userGroup name",
													minlength : "Your userGroup name must consist of at least 2 characters"
												},
												state : "Please select at least 1 state"
											}
										});
					});
	function back() {
		window.location.href = "${CONTEXT_PATH}/userGroupMgmt/view";
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
	<form action="${CONTEXT_PATH}/userGroupMgmt/update" method="post"
		id="form">
		<input type="hidden" name="userGroupId"
			value="${userGroup.userGroupId}" /> <input type="hidden"
			name="updateType" value="${updateType}" /> <input type="hidden" id="userGroupPid"
			name="userGroupPid" />
		<div class="form-horizontal">
			<c:if test="${updateType=='add'}">
				<div class="form-group">
					<label for="parentUserGroupName" class="col-sm-2 control-label">Parent
						UserGroup</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="userGroupParentName" readonly="readonly"
							onclick="showUserGroup(); return false;" name="userGroupParentName"
							placeholder="Parent UserGroup" />
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<label for="userGroupCode" class="col-sm-2 control-label">UserGroup
					Code</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="userGroupCode"
						value="${userGroup.userGroupCode}" placeholder="UserGroup Code" />
				</div>
			</div>
			<div class="form-group">
				<label for="userGroupName" class="col-sm-2 control-label">UserGroup
					Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="userGroupName"
						value="${userGroup.userGroupName}" placeholder="UserGroup Name" />
				</div>
			</div>
			<div class="form-group">
				<label for="state" class="col-sm-2 control-label">State</label>
				<div class="col-sm-10">
					<select class="form-control" name="state">
						<option value="">请选择</option>
						<option value="0" ${userGroup.state=='0'?'selected':''}>有效</option>
						<option value="1" ${userGroup.state=='1'?'selected':''}>无效</option>
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
		<div id="userGroupContent" class="userGroupContent"
			style="display: none; position: absolute;">
			<ul id="userGroupTree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
		</div>
	</form>
</body>
</html>