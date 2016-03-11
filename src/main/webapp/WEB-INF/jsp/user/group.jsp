<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>用户管理</title>
<script type="text/javascript">
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

	$(document).ready(function() {
		var zNodes = eval('${groupJson}');
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
</script>
</head>
<body>
	<ul id="treeDemo" class="ztree"></ul>
</body>
</html>