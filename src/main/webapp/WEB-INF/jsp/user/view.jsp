<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>用户管理</title>
</head>
<body>

	<form action="">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="collapse navbar-collapse">
					<div class="navbar-form navbar-left">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="User Name">
							<input type="text" class="form-control" placeholder="screen Name">
						</div>
					</div>
					<div class="navbar-form navbar-right">
						<button type="submit" class="btn btn-primary">Submit</button>
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
					<li><a href="#" aria-label="Previous"><span
							aria-hidden="true">««</span></a></li>
					<li><a href="#">«</a></li>
					<li><a href="#">»</a></li>
					<li><a href="#" aria-label="Next"><span aria-hidden="true">»»</span></a></li>
				</ul>
			</div>
		</div>


	</form>
</body>
</html>