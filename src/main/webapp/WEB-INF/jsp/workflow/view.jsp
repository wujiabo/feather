<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.io.InputStream"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>流程管理</title>
<script type="text/javascript">
	function back() {
		window.location.href = "${CONTEXT_PATH}/workflowMgmt/process";
	}
</script>
</head>
<body>
	<form action="${CONTEXT_PATH}/workflowMgmt/view" method="post"
		id="form">
		<div class="form-horizontal">
			<div class="form-group">
				<img
					src="${CONTEXT_PATH}/workflowMgmt/picture/${processDefId}/picture" />
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-default" onclick="back()">Back</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>