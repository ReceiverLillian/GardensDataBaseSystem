<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.Species" %>
<%@ page import="java.util.List" %>
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
	 /* 表格样式 */
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
	<div class="bootstrap-admin-panel-content table-container">
		<table>
			<thead>
			<tr>
				<th>编号</th>
				<th>种名</th>
				<th>别名</th>
				<th>科名</th>
				<th>属名</th>
				<th>形态特征</th>
				<th>栽培技术</th>
				<th>应用价值</th>
				<th>配图描述</th>
				<th>配图拍摄地点</th>
				<th>配图拍摄人</th>
			</tr>
			</thead>
			<tbody>
			<% List<Species> speciesList = (List<Species>) request.getAttribute("speciesList");
				if (speciesList != null) {
					System.out.println("species表不为空。");
					/*for (Species species:speciesList)
						System.out.println("testaaaa");*/
					for (Species species : speciesList) { %>
			<tr>
				<td><%= species.getSpeciesId() %></td>

				<td><%= species.getSpeciesName() %></td>
				<td><%= species.getSpeciesOtherName() %></td>
				<td><%= species.getFamilyName() %></td>
				<td><%= species.getGenusName() %></td>
				<td><%= species.getSpeciesMorph() %></td>
				<td><%= species.getSpeciesTech() %></td>
				<td><%= species.getSpeciesAppl() %></td>
				<td><%= species.getPictureDescri() %></td>
				<td><%= species.getPicturePlace() %></td>
				<td><%= species.getPhotoedBy() %></td>
			</tr>
			<%     }
			} else { %>
			<tr>
				<td colspan="11">暂无物种数据。</td>
			</tr>
			<% } %>
			</tbody>
		</table>
	</div>



</body>
</html>