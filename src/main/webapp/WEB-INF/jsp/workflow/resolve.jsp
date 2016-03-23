<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>流程实例</title>
<script type="text/javascript">
	function back() {
		window.location.href = "${CONTEXT_PATH}/workflowMgmt/todoTask";
	}
	$().ready(function() {
	});
</script>
</head>
<body>
	<form action="${CONTEXT_PATH}/workflowMgmt/resolve" method="post"
		id="form">
		<input type="hidden" name="taskId" value="${taskId}" />
		<div class="form-horizontal">

			<div class="form-group">
				<label for="variables" class="col-sm-2 control-label">Variables</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="variables" rows="5"
						placeholder="Variables">{"owner":"1","day":3}</textarea>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Complete</button>
					<button type="submit" class="btn btn-primary">Reject</button>
					<button type="submit" class="btn btn-primary">Assign</button>
					<button type="button" class="btn btn-default" onclick="back()">Back</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>