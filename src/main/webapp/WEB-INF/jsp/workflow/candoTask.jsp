<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>认领任务</title>
<script type="text/javascript">
	function pagination(pageValue) {
		var currentPage = $("input[name='currentPage']").val();
		if (pageValue == 'F') {
			$("input[name='currentPage']").val('1');
		} else if (pageValue == 'P') {
			$("input[name='currentPage']").val(parseInt(currentPage) - 1);
		} else if (pageValue == 'N') {
			$("input[name='currentPage']").val(parseInt(currentPage) + 1);
		} else if (pageValue == 'L') {
			$("input[name='currentPage']").val('${totalPage}');
		}
		$("#form").submit();
	}
	function claim(taskId) {
		window.location.href = "${CONTEXT_PATH}/workflowMgmt/claim/"
				+ taskId;
	}
</script>
</head>
<body>

	<form action="${CONTEXT_PATH}/workflowMgmt/process" method="post"
		id="form">
		<input type="hidden" name="currentPage" value="${currentPage}" />

		<div class="row">
			<div class="col-lg-4">
				<div class="input-group">
					<span class="input-group-addon">Order Id</span> <input
						name="orderId" value="${orderId}"
						type="text" class="form-control" placeholder="Order Id">
				</div>
			</div>
			<div class="col-lg-4">
				<div class="input-group"></div>
			</div>
		</div>
		<br>

		<div class="collapse navbar-collapse">
			<div class="navbar-right">
				<button type="submit" class="btn btn-primary">Search</button>
			</div>
		</div>
		<br>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>Id</th>
					<th>Name</th>
					<th>Process Instance</th>
					<th>Operation</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${taskList}" var="bean" varStatus="status">
					<tr>
						<th scope="row">${status.index + 1}</th>
						<td>${bean.id}</td>
						<td>${bean.name}</td>
						<td>${bean.processInstanceId}</td>
						<td>
							<button type="button" class="btn btn-default btn-xs"
								onclick="claim('${bean.id}')">Claim</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="collapse navbar-collapse">
			<div class="navbar-right">
				<ul class="pagination">
					<li><a href="javascript:void(0)"
						${isFirst?'':'onclick=pagination("F")'} aria-label="Previous"><span
							aria-hidden="true">««</span></a></li>
					<li><a href="javascript:void(0)"
						${isFirst?'':'onclick=pagination("P")'}>«</a></li>
					<li><a href="javascript:void(0)">共${totalCount}条
							${currentPage} / ${totalPage}</a></li>
					<li><a href="javascript:void(0)"
						${isLast?'':'onclick=pagination("N")'}>»</a></li>
					<li><a href="javascript:void(0)"
						${isLast?'':'onclick=pagination("L")'} aria-label="Next"><span
							aria-hidden="true">»»</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</body>
</html>