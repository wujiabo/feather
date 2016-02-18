<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		}
	};

	var zNodes = [ {
		id : 1,
		pId : 0,
		name : "系统管理",
		open : true
	}, {
		id : 11,
		pId : 1,
		name : "用户管理"
	}, {
		id : 12,
		pId : 1,
		name : "角色管理"
	}, {
		id : 13,
		pId : 1,
		name : "用户组管理"
	}, {
		id : 14,
		pId : 1,
		name : "权限管理"
	}, {
		id : 15,
		pId : 1,
		name : "菜单管理"
	}, {
		id : 2,
		pId : 0,
		name : "测试",
		open : true
	}, {
		id : 21,
		pId : 2,
		name : "测试1"
	}, {
		id : 22,
		pId : 2,
		name : "测试2"
	}];

	$(document).ready(function() {
		$.fn.zTree.init($("#treeMenu"), setting, zNodes);
	});
</script>

<ul id="treeMenu" class="ztree"></ul>
