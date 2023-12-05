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
		class="navbar navbar-light bg-primary navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small"
		role="navigation">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="collapse navbar-collapse main-navbar-collapse">
						<a class="text-white navbar-brand " href="/gardens/login.jsp"><strong>园林植物监测管理</strong></a>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown  text-white"><a href="#" role="button"
								class="dropdown-toggle" data-hover="dropdown"> <i
									class="glyphicon glyphicon-user "></i> 欢迎您，${user.user_name}
								</a>
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
					<li><a href="/gardens/conserve_plant.jsp"><i
							class="glyphicon glyphicon-chevron-right"></i> 植物监测</a></li>
					<li><a href="/gardens/conserve_quota.jsp"><i
							class="glyphicon glyphicon-chevron-right"></i> 指标管理</a></li>
					<li><a href="/gardens/conserve_quota.jsp"><i
							class="glyphicon glyphicon-chevron-right"></i> 异常指标</a></li>
				</ul><br><br>

			</div>

			<!-- content -->
<%--			<div class="col-md-10">--%>
<%--				<div class="row">--%>
<%--					<div class="col-md-6">--%>
<%--						<div class="panel panel-default">--%>
<%--							<div class="panel-heading">--%>
<%--								<div class="text-muted bootstrap-admin-box-title">养护要求</div>--%>
<%--							</div>--%>
<%--							<div class="bootstrap-admin-panel-content">--%>
<%--								<ul>--%>
<%--									<li>养护人员可以在登录系统后，查询养护要求；</li>--%>

<%--								</ul>--%>
<%--							</div>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--					<div class="col-md-6">--%>
<%--						<div class="panel panel-default">--%>
<%--							<div class="panel-heading">--%>
<%--								<div class="text-muted bootstrap-admin-box-title">进行养护</div>--%>
<%--							</div>--%>
<%--							<div class="bootstrap-admin-panel-content">--%>
<%--								<ul>--%>
<%--									<li>养护人员根据养护要求对园林植物进行定期养护</li>--%>

<%--								</ul>--%>
<%--							</div>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</div>--%>

<%--			</div>--%>
		</div>
	</div>


</body>
</html>