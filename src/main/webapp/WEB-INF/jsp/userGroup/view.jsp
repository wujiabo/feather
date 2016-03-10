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
				|| event.target.id == "userGroupContent" || $(event.target).parents(
				"#userGroupContent").length > 0)) {
			hideMenu();
		}
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#userGroupTree"), setting, zNodes);
		
		$.fn.zTree.getZTreeObj("userGroupTree").selectNode($.fn.zTree.getZTreeObj("userGroupTree").getNodeByParam("id", '${userGroupPid}'));
	});

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
	function toEdit(userGroupId) {
		window.location.href = "${CONTEXT_PATH}/userGroupMgmt/edit/" + userGroupId;
	}
	function toAdd() {
		window.location.href = "${CONTEXT_PATH}/userGroupMgmt/add";
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

	<form action="${CONTEXT_PATH}/userGroupMgmt/view" method="post"
		id="form">
		<input type="hidden" name="currentPage" value="${currentPage}" />
		<input type="hidden" id="userGroupPid" name="userGroupPid" value="${userGroupPid}" />
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="collapse navbar-collapse">
					<div class="navbar-form navbar-left">
						<div class="input-group">
							<span class="input-group-addon">Parent UserGroup</span> <input
								name="userGroupParentName" value="${userGroupParentName}"
								type="text" readonly="readonly" id="userGroupParentName"
								onclick="showUserGroup(); return false;" class="form-control"
								placeholder="Parent UserGroup">
						</div>
						<div class="input-group">
							<span class="input-group-addon">UserGroup Code</span> <input
								name="userGroupCode" value="${userGroupCode}" type="text"
								class="form-control" placeholder="UserGroup Code">
						</div>
						<div class="input-group">
							<span class="input-group-addon">UserGroup Name</span> <input
								name="userGroupName" value="${userGroupName}" type="text"
								class="form-control" placeholder="UserGroup Name">
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
					<th>UserGroup Code</th>
					<th>UserGroup Name</th>
					<th>State</th>
					<th>Operation</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.pageList}" var="bean"
					varStatus="status">
					<tr>
						<th scope="row">${status.index + 1}</th>
						<td>${bean.userGroup_code}</td>
						<td>${bean.userGroup_name}</td>
						<td>${bean.state=='0'?'有效':'无效'}</td>
						<td>
							<button type="button" class="btn btn-default btn-xs"
								onclick="toEdit('${bean.userGroup_id}')">Edit</button>
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

		<div id="userGroupContent" class="userGroupContent"
			style="display: none; position: absolute;">
			<ul id="userGroupTree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
		</div>

	</form>
</body>
</html>