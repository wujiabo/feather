<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("treeMenu");
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					return false;
				} else {
					window.location.href = '${ctx}' + treeNode.file;
					return true;
				}
			}
		}
	};

	var zNodes = eval('<%= session.getAttribute(com.wujiabo.opensource.feather.constants.Constants.CURRENT_MENU) %>');

	$(document).ready(function() {
		$.fn.zTree.init($("#treeMenu"), setting, zNodes);
	});
</script>

<ul id="treeMenu" class="ztree"></ul>
