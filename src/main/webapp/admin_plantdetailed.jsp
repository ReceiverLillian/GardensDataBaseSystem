<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page
	import="java.util.Base64,java.sql.Blob,java.io.InputStream,java.io.OutputStream"%>
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
						<a class="navbar-brand" href="admin_main.jsp"><strong>欢迎使用园林植物综合管理平台</strong></a>
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
        								<div  class="text-muted bootstrap-admin-box-title">植物信息详情</div>
                            <div class="form-group">
                                <label>植物编号</label>
                                <input type="text" class="form-control" value="${speciesBean.species_id}" readonly>
                            </div>

                        <%
                                ArrayList<PictureBean> pictureBeans=(ArrayList<PictureBean>)session.getAttribute("pictureBeans");
                                for(PictureBean bean : pictureBeans){
                                Blob imageBlob = (Blob) bean.getPicture_content();
                                String base64Image = Base64.getEncoder().encodeToString(imageBlob.getBytes(1, (int) imageBlob.length()));
                        %>
                            <div>
                                <img style="width: 300px; align-items: center;" src="data:image/jpeg;base64, <%= base64Image %>" >
                            </div>
                            <div class="form-group">
                                <label>图片描述</label>
                                <input type="text" class="form-control" value="<%=bean.getPicture_discri() %>" readonly>
                            </div>
                            <div class="form-group">
                                <label>拍摄者/单位</label>
                                <input type="text" class="form-control" value="<%=bean.getPhototedby() %>" readonly>
                            </div>

                        <%
                            }
                        %>
        				<div class="row">
        					<div class="col-lg-12">
        						<table id="data_list" class="table table-hover table-bordered"
        							cellspacing="0" width="100%">
                            <div class="form-group">
                                <label>植物名称</label>
                                <input type="text" class="form-control" value="${speciesBean.species_name}" readonly>
                            </div>
                            <div class="form-group">
                                <label>创建人</label>
                                <input type="text" class="form-control" value="${speciesBean.createdby}" readonly>
                            </div>
                            <div class="form-group">
                                <label>植物别名</label>
                                <input type="text" class="form-control" value="${speciesBean.species_othername}" readonly>
                            </div>
                            <div class="form-group">
                                <label>创建时间</label>
                                <input type="text" class="form-control"  value="${speciesBean.createdtime}" readonly>
                            </div>

                            <div class="form-group">
                                <label>更新时间</label>
                                <input type="text" class="form-control"  value="${speciesBean.updatetime}" readonly>
                            </div>
                            <div class="form-group">
                                <label>植物形态</label>
                                <input type="text" class="form-control" value="${speciesBean.species_morph}" readonly>
                            </div>
                            <div class="form-group">
                                <label>生长环境</label>
                                <input type="text" class="form-control" value="${speciesBean.species_environment}" readonly>
                            </div>

                            <div class="form-group">
                                <label>栽培技术</label>
                                <input type="text" class="form-control"  value="${speciesBean.species_tech}" readonly>
                            </div>
                            <div class="form-group">
                                <label>应用价格</label>
                                <input type="text" class="form-control" value="${speciesBean.species_appl}" readonly>
                            </div>
                            <div class="form-group">
                                <label>所属科</label>
                                <input type="text" class="form-control" value="${speciesBean.family_name}" readonly>
                            </div>
                            <div class="form-group">
                                <label>所属属</label>
                                <input type="text" class="form-control" value="${speciesBean.genus_name}" readonly>
                            </div>
                            <div class="form-group">
                                <label>分布地区</label>
                                <input type="text" class="form-control" value="${totalprovince}" readonly>
                            </div>
                            <div class="form-group">
                                <a href="/gardens/basicMessageManagement"><button type="button" class="btn btn-info btn-xs">返回</button></a>
                            </div>
                            </div>
                               </div>
        					</div>
        				</div>
        			</div>
        		</div>
        	</div>


        </body>
        </html>