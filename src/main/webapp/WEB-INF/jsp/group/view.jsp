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
				|| event.target.id == "groupContent" || $(event.target).parents(
				"#groupContent").length > 0)) {
			hideMenu();
		}
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#groupTree"), setting, zNodes);
		
		$.fn.zTree.getZTreeObj("groupTree").selectNode($.fn.zTree.getZTreeObj("groupTree").getNodeByParam("id", '${groupPid}'));
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
	function toEdit(groupId) {
		window.location.href = "${CONTEXT_PATH}/groupMgmt/edit/" + groupId;
	}
	function toRole(groupId) {
		window.location.href = "${CONTEXT_PATH}/groupMgmt/role/" + groupId;
	}
	function toAdd() {
		window.location.href = "${CONTEXT_PATH}/groupMgmt/add";
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

	<form action="${CONTEXT_PATH}/groupMgmt/view" method="post"
		id="form">
		<input type="hidden" name="currentPage" value="${currentPage}" />
		<input type="hidden" id="groupPid" name="groupPid" value="${groupPid}" />
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="collapse navbar-collapse">
					<div class="navbar-form navbar-left">
						<div class="input-group">
							<span class="input-group-addon">Parent Group</span> <input
								name="groupParentName" value="${groupParentName}"
								type="text" readonly="readonly" id="groupParentName"
								onclick="showGroup(); return false;" class="form-control"
								placeholder="Parent Group">
						</div>
						<div class="input-group">
							<span class="input-group-addon">Group Code</span> <input
								name="groupCode" value="${groupCode}" type="text"
								class="form-control" placeholder="Group Code">
						</div>
						<div class="input-group">
							<span class="input-group-addon">Group Name</span> <input
								name="groupName" value="${groupName}" type="text"
								class="form-control" placeholder="Group Name">
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
					<th>Group Code</th>
					<th>Group Name</th>
					<th>State</th>
					<th>Operation</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.pageList}" var="bean"
					varStatus="status">
					<tr>
						<th scope="row">${status.index + 1}</th>
						<td>${bean.group_code}</td>
						<td>${bean.group_name}</td>
						<td>${bean.state=='0'?'有效':'无效'}</td>
						<td>
							<button type="button" class="btn btn-default btn-xs"
								onclick="toEdit('${bean.group_id}')">Edit</button>
							<button type="button" class="btn btn-default btn-xs"
								onclick="toRole('${bean.group_id}')">Role</button>
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

		<div id="groupContent" class="groupContent"
			style="display: none; position: absolute;">
			<ul id="groupTree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
		</div>

	</form>
</body>
</html>