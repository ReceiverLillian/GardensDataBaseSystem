<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.Species" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.SpeciesDao" %>
<%--<%@ page--%>
<%--	import="com.bean.AdminBean,com.dao.AdminDao"%>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN" class="ax-vertical-centered">
<head>
	<meta charset="UTF-8">
	<title>园林植物综合管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
	<link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.11.6/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/jQuery/jquery-3.1.1.min.js"></script>
	<script src="static/js/bootstrap-dropdown.min.js"></script>
	<script src="static/js/adminUpdateInfo.js"></script>
	<script src="static/js/adminUpdatePwd.js"></script>

	<script>
		function deleteSpecies(speciesId) {
			if (confirm("确定要删除该物种吗？")) {
				$.post("DeleteSpecies", { speciesId: speciesId })
						.done(function(response) {
							if (response === "success") {
								location.reload(); // 刷新页面
							} else {
								alert("删除失败，请重试。");
							}
						})
						.fail(function() {
							alert("删除失败，请重试。");
						});
			}
		}
	</script>

	<style>
		body {
			background-image: url("05.jpg");
		}
		/* 表格样式 */
		table {
			background-color: white;
		}
		.table-container {
			margin-top: 20px;
			width: 100%;
		}

		.table-container table {
			width: 100%;
			border-collapse: collapse;
		}

		.table-container th, .table-container td {
			border: 1px solid #ddd;
			padding: 8px;
			text-align: left;
		}

		.table-container th {
			background-color: #f5f5f5;
		}

		.table-container tr:nth-child(even) {
			background-color: #f9f9f9;
		}

		.table-container tr:hover {
			background-color: #f1f1f1;
		}
	</style>
	<%--<jsp:useBean id="m_species" class="bean.Species" scope="page"/>
	<jsp:setProperty name="m_species" property="speciesId" value=""--%>
</head>

<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>

<body class="bootstrap-admin-with-small-navbar">
<nav
		class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small"
		role="navigation">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="collapse navbar-collapse main-navbar-collapse">
					<a class="navbar-brand" href="admin_main.jsp"><strong>欢迎使用园林植物综合管理平台</strong></a>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" role="button"
												class="dropdown-toggle" data-hover="dropdown"> <i
								class="glyphicon glyphicon-user"></i> 欢迎您，${user.user_name}
							<i class="caret"></i>
							<ul class="dropdown-menu">
								<li><a href="/gardens/staff_jiance.jsp">退出</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</nav>


	<%--<%= Integer.parseInt(request.getParameter("speciesId"))%>--%>
<form action="AddSpecies" method="post" class="container">
	<div class="row">
		<div class="col-md-4">
			<div class="form-group">
				<label for="speciesName" style="color: white;">种名:</label>
				<input type="text" class="form-control" id="speciesName" name="speciesName"  />
			</div>
			<div class="form-group">
				<label for="species_othername" style="color: white;">别名:</label>
				<input type="text" class="form-control" id="species_othername" name="alias"  />
			</div>
		</div>
		<div class="col-md-8">
			<div class="form-group">
				<label for="family" style="color: white;">科名:</label>
				<input type="text" class="form-control" id="family" name="family"  />
			</div>
			<div class="form-group">
				<label for="genus" style="color: white;">属名:</label>
				<input type="text" class="form-control" id="genus" name="genus"  />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label for="morphology" style="color: white;">形态特征:</label>
				<textarea class="form-control" id="morphology" name="morphology" >请输入该植物的基本形态特征：</textarea>
			</div>
			<div class="form-group">
				<label for="cultivation" style="color: white;">栽培技术:</label>
				<textarea class="form-control" id="cultivation" name="cultivation" >请输入该植物的一些栽培技术：</textarea>
			</div>
			<div class="form-group">
				<label for="application" style="color: white;">应用价值:</label>
				<textarea class="form-control" id="application" name="application" >请输入该植物的部分应用价值：</textarea>
			</div>
		</div>
	</div>

	<!-- 其他表单字段 -->
	<%--<input type="hidden" name="speciesId" value="<%= request.getParameter("speciesId") %>" />--%>

	<div class="text-center">
		<button type="submit" class="btn btn-primary">确认添加</button>
	</div>
</form>


</body>
</html>
