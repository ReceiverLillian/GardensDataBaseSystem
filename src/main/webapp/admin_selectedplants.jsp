<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page
	import="bean.*,dao.*"%>

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
	.custom-table {
		background-color: #ffffff; /* 设置背景色为白色 */
	}
  </style>

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
						<a class="navbar-brand" href="superior_main.jsp"><strong>欢迎使用园林植物综合管理平台</strong></a>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" role="button"
								class="dropdown-toggle" data-hover="dropdown"> <i
									class="glyphicon glyphicon-user"></i> 欢迎您，${user.user_name}
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
        					<div class="col-lg-12">
        						<div class="panel panel-default bootstrap-admin-no-table-panel">
        							<div class="panel-heading">
        								<div class="text-muted bootstrap-admin-box-title">植物信息</div>
        							</div>
                                <div>
                                        <form class="form-horizontal" action="/gardens/AdminSelectServlet"
                                            method="post">
                                             <div class="col-lg-7 form-group">
                                                <label class="col-lg-4 control-label" for="query_name">植物信息</label>
                                                <div class="col-lg-8">
                                                    <input class="form-control" id="name" name="name"
                                                        type="text" value=""> <label class="control-label"
                                                        for="query_bname" style="display: none;"></label>
                                                </div>
                                              </div>
                                            <div class="col-lg-5 form-group">
                                                <button type="submit" class="btn btn-primary" id="btn_query">查询</button>
                                            </div>
 <!--------------------植物信息模糊查询↑   根据科属及其组合查询↓   ---------------------->
                                        </form>

                                        <form class="form-horizontal" action="/gardens/AdminSelectByTypeServlet"
                                            method="post">
                                            <div class="col-lg-7 form-group">
                                               <label class="col-lg-4 control-label" for="query_name">科属信息</label>

                                                <div class="col-lg-4">
                                                    <select class="form-control" id="selectbyfamily" name="name1">
                                                        <option value="-1">请选择</option>
                                                        <%

                                                            FamilyDao famdao = new FamilyDao();
                                                            ArrayList<FamilyBean> data = (ArrayList<FamilyBean>) famdao.getAllFamily();
                                                            for (FamilyBean bean : data) {
                                                        %>
                                                        <option value="<%=bean.getFamily_name()%>">
                                                            <%=bean.getFamily_name()%>
                                                        </option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>
                                                </div>

                                                <div class="col-lg-4 form-group">
                                                    <select class="form-control" id="selectbygenus" name="name2">
                                                        <option value="-1">请选择</option>
                                                        <%
                                                            String selectedValue = request.getParameter("name");
                                                            GenusDao gendao = new GenusDao();
                                                            ArrayList<GenusBean> data1 = (ArrayList<GenusBean>) gendao.getAllGenus();
                                                            for (GenusBean bean : data1) {
                                                        %>
                                                        <option value="<%=bean.getGenus_name()%>">
                                                            <%=bean.getGenus_name()%>
                                                        </option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>
                                                </div>
                                              </div>
                                            <div class="col-lg-3 form-group">
                                                <button type="submit" class="btn btn-primary" id="btn_query">查询</button>
                                            </div>
                                        </form>

                                    </div>
        						</div>
        					</div>

        				<div class="row">
        					<div class="col-lg-12">
        						<table id="data_list" class="table table-hover table-bordered custom-table"
        							cellspacing="0" width="100%">
        							<thead>
        								<tr>
        									<th>植物编号</th>
        									<th>植物名称</th>
        									<th>植物别名</th>
        									<th>所属科</th>
                                            <th>所属属</th>
                                            <th>分布地区</th>
                                             <th>操作</th>
        								</tr>
        							</thead>

							<%
								ArrayList<SpeciesBean> allspecies=(ArrayList<SpeciesBean>)request.getSession().getAttribute("allspecies");
                             	int i=0;
								for (SpeciesBean bean : allspecies) {

							%>
							<tbody>
								<td><%=bean.getSpecies_id()%></td>
                                <td><%=bean.getSpecies_name()%></td>
								<td><%=bean.getSpecies_othername()%></td>
								<td><%=bean.getFamily_name()%></td>
								<td><%=bean.getGenus_name()%></td>
								<td><%=bean.getTotalprovience()%></td>

								<td>
								<button type="button" class="btn btn-warning btn-xs"
                                   onclick="javascrtpt:window.location.href='Admin_PlantsDetailedServlet?spe_id=<%=bean.getSpecies_id()%>'">详情</button></td>
							</tbody>
							<%
							i++;
								}
							%>

        						</table>
        					</div>
        				</div>
        			</div>
        		</div>
    </div>

</body>
</html>