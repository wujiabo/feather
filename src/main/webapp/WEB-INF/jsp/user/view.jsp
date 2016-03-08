<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>用户管理</title>
<script type="text/javascript">
	function pagination(pageValue) {
		var currentPage = $("input[name='currentPage']").val();
		if (pageValue == 'F') {
			$("input[name='currentPage']").val('1');
		} else if (pageValue == 'P') {
			$("input[name='currentPage']").val(currentPage - 1);
		} else if (pageValue == 'N') {
			$("input[name='currentPage']").val(currentPage + 1);
		} else if (pageValue == 'L') {
			$("input[name='currentPage']").val('${pageBean.totalPage}');
		}
	}
</script>
</head>
<body>

	<form action="" method="post">
		<input type="hidden" name="currentPage" value="${currentPage}" />
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="collapse navbar-collapse">
					<div class="navbar-form navbar-left">
						<div class="input-group">
							<span class="input-group-addon">User Name</span> <input
								name="userName" value="${userName}" type="text"
								class="form-control" placeholder="User Name">
						</div>
						<div class="input-group">
							<span class="input-group-addon">screen Name</span> <input
								name="screenName" value="${screenName}" type="text"
								class="form-control" placeholder="screen Name">
						</div>
					</div>
					<div class="navbar-form navbar-right">
						<button type="submit" class="btn btn-primary">Search</button>
					</div>
				</div>
			</div>
		</nav>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>User Name</th>
					<th>Screen Name</th>
					<th>State</th>
					<th>Operation</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageBean.pageList}" var="bean">
					<tr>
						<th scope="row">1</th>
						<td>${bean.user_name}</td>
						<td>${bean.screen_name}</td>
						<td>${bean.state}</td>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="collapse navbar-collapse">
			<div class="navbar-right">
				<ul class="pagination">
					<li><a href="#" ${pageBean.isFirst?'':'pagination("F")'}
						aria-label="Previous"><span aria-hidden="true">««</span></a></li>
					<li><a href="#" ${pageBean.isFirst?'':'pagination("P")'}>«</a></li>
					<li><a href="#" ${pageBean.isLast?'':'pagination("N")'}>»</a></li>
					<li><a href="#" ${pageBean.isLast?'':'pagination("L")'}
						aria-label="Next"><span aria-hidden="true">»»</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</body>
</html>