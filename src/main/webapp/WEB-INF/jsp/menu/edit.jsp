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
				|| event.target.id == "menuContent" || $(event.target)
				.parents("#menuContent").length > 0)) {
			hideMenu();
		}
	}

	$(document)
			.ready(
					function() {
						$.fn.zTree.init($("#menuTree"), setting, zNodes);
						var zTree = $.fn.zTree.getZTreeObj("menuTree");
						zTree.expandAll(true);

						$("#form")
								.validate(
										{
											rules : {
												menuUrl : {
													required : true,
													minlength : 2
												},
												menuName : {
													required : true,
													minlength : 2
												},
												state : "required"
											},
											messages : {
												menuUrl : {
													required : "Please provide a menu url",
													minlength : "Your screen name must be at least 2 characters long"
												},
												menuName : {
													required : "Please enter a menu name",
													minlength : "Your menu name must consist of at least 2 characters"
												},
												state : "Please select at least 1 state"
											}
										});
					});
	function back() {
		window.location.href = "${CONTEXT_PATH}/menuMgmt/view";
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
	<form action="${CONTEXT_PATH}/menuMgmt/update" method="post"
		id="form">
		<input type="hidden" name="menuId"
			value="${menu.menuId}" /> <input type="hidden"
			name="updateType" value="${updateType}" /> <input type="hidden" id="menuPid"
			name="menuPid" />
		<div class="form-horizontal">
			<c:if test="${updateType=='add'}">
				<div class="form-group">
					<label for="parentMenuName" class="col-sm-2 control-label">Parent
						Menu</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="menuParentName" readonly="readonly"
							onclick="showMenu(); return false;" name="menuParentName"
							placeholder="Parent Menu" />
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<label for="menuUrl" class="col-sm-2 control-label">Menu
					Url</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="menuUrl"
						value="${menu.menuUrl}" placeholder="Menu Url" />
				</div>
			</div>
			<div class="form-group">
				<label for="menuName" class="col-sm-2 control-label">Menu
					Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="menuName"
						value="${menu.menuName}" placeholder="Menu Name" />
				</div>
			</div>
			<div class="form-group">
				<label for="state" class="col-sm-2 control-label">State</label>
				<div class="col-sm-10">
					<select class="form-control" name="state">
						<option value="">请选择</option>
						<option value="0" ${menu.state=='0'?'selected':''}>有效</option>
						<option value="1" ${menu.state=='1'?'selected':''}>无效</option>
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
		<div id="menuContent" class="menuContent"
			style="display: none; position: absolute;">
			<ul id="menuTree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
		</div>
	</form>
</body>
</html>