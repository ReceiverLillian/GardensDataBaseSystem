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

<%
	SpeciesDao sd = new SpeciesDao();
	Species species = sd.selectPlantById(Integer.parseInt(request.getParameter("speciesId")));

%>
	<%--<%= Integer.parseInt(request.getParameter("speciesId"))%>--%>
<form action="UpdateSort" method="post" class="container">
	<div class="row">
		<div class="col-md-4">
			<div class="form-group">
				<label for="speciesName" style="color: white;">种名:</label>
				<input type="text" class="form-control" id="speciesName" name="speciesName" value="<%= species.getSpeciesName() %>" />
			</div>
			<div class="form-group">
				<label for="species_othername" style="color: white;">别名:</label>
				<input type="text" class="form-control" id="species_othername" name="alias" value="<%= species.getSpeciesOtherName() %>" />
			</div>
		</div>
		<div class="col-md-8">
			<div class="form-group">
				<label for="family" style="color: white;">科名:</label>
				<input type="text" class="form-control" id="family" name="family" value="<%= species.getFamilyName() %>" />
			</div>
			<div class="form-group">
				<label for="genus" style="color: white;">属名:</label>
				<input type="text" class="form-control" id="genus" name="genus" value="<%= species.getGenusName() %>" />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<%--<div class="form-group">
				&lt;%&ndash;@declare id="province"&ndash;%&gt;<label for="province" style="color: white;">分布区域:</label>
				<textarea class="form-control" id="province" name="province" ><%= species.getProvince() %></textarea>
			</div>--%>
			<div class="form-group">
				<%--@declare id="province"--%>
				<label for="province" style="color: white;">分布区域:</label>
				<select class="form-control" id="province" name="province">
					<%
						String selectedProvince = species.getProvince();
						String[] provinces = {"北京市", "天津市", "河北省", "山西省", "内蒙古自治区", "辽宁省", "吉林省", "黑龙江省", "上海市", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "广西壮族自治区", "海南省", "重庆市", "四川省", "贵州省", "云南省", "西藏自治区", "陕西省", "甘肃省", "青海省", "宁夏回族自治区", "新疆维吾尔自治区", "台湾省", "香港特别行政区", "澳门特别行政区"};

						for (String province : provinces) {
							boolean isSelected = province.equals(selectedProvince);
					%>
					<option value="<%= province %>" <%= isSelected ? "selected" : "" %>><%= province %></option>
					<%
						}
					%>
				</select>
			</div>
			<div class="form-group">
				<%--@declare id="environment"--%><label for="environment" style="color: white;">生长环境:</label>
				<textarea class="form-control" id="environment" name="environment" ><%= species.getEnvironment() %></textarea>
			</div>

		</div>
	</div>

	<!-- 其他表单字段 -->
	<input type="hidden" name="speciesId" value="<%= request.getParameter("speciesId") %>" />

	<div class="text-center">
		<button type="submit" class="btn btn-primary">保存</button>
	</div>
</form>


</body>
</html>
