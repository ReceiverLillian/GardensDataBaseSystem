<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script src="static/js/bootstrap.min.js"></script>
<script src="static/jQuery/jquery-3.1.1.min.js"></script>
<script src="static/js/bootstrap-dropdown.min.js"></script>
<script src="static/js/adminUpdateInfo.js"></script>
<script src="static/js/adminUpdatePwd.js"></script>
<style>
body {
	background-image: url("05.jpg");
}
</style>
</head>

<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>

<body class="bootstrap-admin-with-small-navbar">
<%--	<%--%>
<%--		AdminBean admin = new AdminBean();--%>
<%--		String aid = (String) session.getAttribute("aid");--%>
<%--		AdminDao admindao = new AdminDao();--%>
<%--		admin = admindao.get_AidInfo2(aid);--%>
<%--	%>--%>
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

	<div class="container">
		<!-- left, vertical navbar & content -->
		<div class="row">
			<!-- left, vertical navbar -->
			<div class="col-md-2 bootstrap-admin-col-left">
				<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
					<li><a href="/gardens/basicMessageManagement"><i
							class="glyphicon glyphicon-chevron-right"></i> 植物基本信息管理</a></li>
					<li><a href="/gardens/sortManagement"><i
							class="glyphicon glyphicon-chevron-right"></i> 植物分类管理</a></li>

					<%--<li><a href="/gardens/AdminSelectPlants"><i
							class="glyphicon glyphicon-chevron-right"></i> 查询植物</a></li>--%>
				</ul><br><br>

			</div>

			<!-- content -->
			<div class="col-md-10">
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">植物基本信息管理</div>
							</div>
							<div class="bootstrap-admin-panel-content">
								<ul>
									<li>您是管理员，可以对植物信息进行增删改查等基本操作</li>

								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">植物分类信息</div>
							</div>
							<div class="bootstrap-admin-panel-content">
								<ul>
									<li>您是管理员，可以依据分类信息进行增删改查</li>

								</ul>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>


</body>
</html>