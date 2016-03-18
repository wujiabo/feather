<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>流程管理</title>
<script type="text/javascript">
	function back() {
		window.location.href = "${CONTEXT_PATH}/workflowMgmt/process";
	}
	$().ready(function() {
		$("#form").validate({
			rules : {
				processFile : "required"
			},
			messages : {
				processFile : "Please select at least 1 file"
			}
		});
	});
</script>
</head>
<body>
	<form action="${CONTEXT_PATH}/workflowMgmt/deploy" method="post" enctype="multipart/form-data"
		id="form">
		<div class="form-horizontal">
			<div class="form-group">
				<label for="screenName" class="col-sm-2 control-label">Process
					File</label>
				<div class="col-sm-10">
					<input type="file" class="form-control" name="processFile"
						placeholder="Process File" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Deploy</button>
					<button type="button" class="btn btn-default" onclick="back()">Back</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>