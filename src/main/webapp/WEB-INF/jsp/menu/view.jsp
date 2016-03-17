<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>菜单管理</title>
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

	var zNodes = eval('${menuJson}');

	function beforeClick(treeId, treeNode) {
		var check = (treeNode && !treeNode.isParent);
		return true;
	}

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("menuTree"), nodes = zTree
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

		var menuObj = $("#menuParentName");
		menuObj.attr("value", v);

		var menuIdObj = $("#menuPid");
		menuIdObj.attr("value", ids);
		hideMenu();
	}

	function showMenu() {
		var menuObj = $("#menuParentName");
		var menuOffset = $("#menuParentName").offset();
		$("#menuContent").css({
			left : menuOffset.left + "px",
			top : menuOffset.top + menuObj.outerHeight() + "px"
		}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuParentName"
				|| event.target.id == "menuContent" || $(event.target).parents(
				"#menuContent").length > 0)) {
			hideMenu();
		}
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#menuTree"), setting, zNodes);

		var zTree = $.fn.zTree.getZTreeObj("menuTree");
		zTree.selectNode(zTree.getNodeByParam("id", '${menuPid}'));
		zTree.expandAll(true);
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
	function toEdit(menuId) {
		window.location.href = "${CONTEXT_PATH}/menuMgmt/edit/" + menuId;
	}
	function toAdd() {
		window.location.href = "${CONTEXT_PATH}/menuMgmt/add";
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

	<form action="${CONTEXT_PATH}/menuMgmt/view" method="post" id="form">
		<input type="hidden" name="currentPage" value="${currentPage}" /> <input
			type="hidden" id="menuPid" name="menuPid" value="${menuPid}" />

		<div class="row">
			<div class="col-lg-4">
				<div class="input-group">
					<span class="input-group-addon">Parent Menu</span> <input
						name="menuParentName" value="${menuParentName}" type="text"
						readonly="readonly" id="menuParentName"
						onclick="showMenu(); return false;" class="form-control"
						placeholder="Parent Menu">
				</div>
			</div>
			<div class="col-lg-4">
				<div class="input-group">
					<span class="input-group-addon">Menu Url</span> <input
						name="menuUrl" value="${menuUrl}" type="text" class="form-control"
						placeholder="Menu Url">
				</div>
			</div>
			<div class="col-lg-4">
				<div class="input-group">
					<span class="input-group-addon">Menu Name</span> <input
						name="menuName" value="${menuName}" type="text"
						class="form-control" placeholder="Menu Name">
				</div>
			</div>
		</div>
		<br>

		<div class="collapse navbar-collapse">
			<div class="navbar-right">
				<button type="submit" class="btn btn-primary">Search</button>
				<button type="button" class="btn btn-default" onclick="toAdd()">Add</button>
			</div>
		</div>
		<br>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>Menu Url</th>
					<th>Menu Name</th>
					<th>Seq</th>
					<th>State</th>
					<th>Operation</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.pageList}" var="bean"
					varStatus="status">
					<tr>
						<th scope="row">${status.index + 1}</th>
						<td>${bean.menu_url}</td>
						<td>${bean.menu_name}</td>
						<td>${bean.seq}</td>
						<td>${bean.state=='0'?'有效':'无效'}</td>
						<td>
							<button type="button" class="btn btn-default btn-xs"
								onclick="toEdit('${bean.menu_id}')">Edit</button>
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

		<div id="menuContent" class="menuContent"
			style="display: none; position: absolute;">
			<ul id="menuTree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
		</div>

	</form>
</body>
</html>