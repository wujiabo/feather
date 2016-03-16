<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>用户管理</title>
<script type="text/javascript">
	function back() {
		window.location.href = "${CONTEXT_PATH}/userMgmt/view";
	}
	var setting = {
		check : {
			enable : true,
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},
		data : {
			simpleData : {
				enable : true
			}
		}
	};

	function save() {
		var groupIds = "";
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var selectedNode = zTree.getCheckedNodes();
		for (var i = 0; i < selectedNode.length; i++) {
			if(!selectedNode[i].getCheckStatus().half){
				groupIds = groupIds + selectedNode[i].id + ",";
			}
		}
		$("#groupIds").val(groupIds);
		$("#form").submit();
	}

	$(document).ready(function() {
		var zNodes = eval('${groupJson}');
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.expandAll(true);
	});
</script>
</head>
<body>
	<form action="${CONTEXT_PATH}/userMgmt/group" method="post" id="form">
		<input type="hidden" name="userId" value="${userId}" /> <input
			type="hidden" id="groupIds" name="groupIds" />
		<div class="form-horizontal">
			<div class="form-group">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-primary" onclick="save()">Save</button>
					<button type="button" class="btn btn-default" onclick="back()">Back</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>