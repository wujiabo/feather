<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<title>菜单管理</title>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$("#form")
								.validate(
										{
											rules : {
												menuUrl : {
													required : true,
													minlength : 2
												},
												menuName : {
													required : true,
													minlength : 2
												},
												state : "required"
											},
											messages : {
												menuUrl : {
													required : "Please provide a menu url",
													minlength : "Your screen name must be at least 2 characters long"
												},
												menuName : {
													required : "Please enter a menu name",
													minlength : "Your menu name must consist of at least 2 characters"
												},
												state : "Please select at least 1 state"
											}
										});
					});
	function back() {
		window.location.href = "${CONTEXT_PATH}/menuMgmt/view";
	}
</script>
<style type="text/css">
ul.ztree {
	margin-top: 10px;
	border: 1px solid #617775;
	background: #f0f6e4;
	width: 220px;
	height: 360px;
	overflow-y: scroll;
	overflow-x: auto;
}
</style>
</head>
<body>
	<form action="${CONTEXT_PATH}/menuMgmt/update" method="post" id="form">
		<input type="hidden" name="menuId" value="${menu.menuId}" /> <input
			type="hidden" name="updateType" value="${updateType}" />
		<div class="form-horizontal">
			<c:if test="${updateType=='add'}">
				<div class="form-group">
					<label for="menuPid" class="col-sm-2 control-label">Parent
						Menu</label>
					<div class="col-sm-10">
						<select class="form-control" name="menuPid">
							<option value="">请选择</option>
							<c:forEach items="${rootMenuList}" var="bean">
								<option value="${bean.menuId}" >${bean.menuName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<label for="menuUrl" class="col-sm-2 control-label">Menu Url</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="menuUrl"
						value="${menu.menuUrl}" placeholder="Menu Url" />
				</div>
			</div>
			<div class="form-group">
				<label for="menuName" class="col-sm-2 control-label">Menu
					Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="menuName"
						value="${menu.menuName}" placeholder="Menu Name" />
				</div>
			</div>
			<div class="form-group">
				<label for="seq" class="col-sm-2 control-label">Seq</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="seq"
						value="${menu.seq}" placeholder="Seq" />
				</div>
			</div>
			<div class="form-group">
				<label for="state" class="col-sm-2 control-label">State</label>
				<div class="col-sm-10">
					<select class="form-control" name="state">
						<option value="">请选择</option>
						<option value="0" ${menu.state=='0'?'selected':''}>有效</option>
						<option value="1" ${menu.state=='1'?'selected':''}>无效</option>
					</select>
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Save</button>
					<button type="button" class="btn btn-default" onclick="back()">Back</button>
				</div>
			</div>
		</div>
		<div id="menuContent" class="menuContent"
			style="display: none; position: absolute;">
			<ul id="menuTree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
		</div>
	</form>
</body>
</html>