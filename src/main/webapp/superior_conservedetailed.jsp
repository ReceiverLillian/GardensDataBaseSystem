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
  </style>
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
					<li><a href="/gardens/SuperiorShowConserveServlet"><i
							class="glyphicon glyphicon-chevron-right"></i> 养护信息</a></li>
					<li><a href="/gardens/SuperiorShowMonitorServlet"><i
							class="glyphicon glyphicon-chevron-right"></i> 监测信息</a></li>
					<li><a href="/gardens/SuperiorShowDiseaseServlet"><i
							class="glyphicon glyphicon-chevron-right"></i> 病虫害信息</a></li>
					<li><a href="/gardens/SuperiorConPeopleServlet"><i
							class="glyphicon glyphicon-chevron-right"></i> 养护人员</a></li>
					<li><a href="/gardens/SuperiorMonPeopleServlet"><i
							class="glyphicon glyphicon-chevron-right"></i> 监测人员</a></li>
					<li><a href="/gardens/SuperiorShowPlantsServlet"><i
							class="glyphicon glyphicon-chevron-right"></i> 植物信息</a></li>
					<li><a href="/gardens/SuperiorShowTypesServlet"><i
							class="glyphicon glyphicon-chevron-right"></i> 类别信息</a></li>

				</ul><br><br>

			</div>

		<!-- content -->
        			<div class="col-md-10">
        				<div class="row">
        					<div class="col-lg-12">
        						<div class="panel panel-default bootstrap-admin-no-table-panel">
        							<div class="panel-heading">
        								<div  class="text-muted bootstrap-admin-box-title">养护信息详情</div>

        				<div class="row">
        					<div class="col-lg-12">
        						<table id="data_list" class="table table-hover table-bordered"
        							cellspacing="0" width="100%">
                            <div class="form-group">
                                <label>养护编号</label>
                                <input type="text" class="form-control" value="${targetconserve.con_id}" readonly>
                            </div>
                            <div class="form-group">
                                <label>养护名称</label>
                                <input type="text" class="form-control" value="${targetconserve.con_name}" readonly>
                            </div>
                            <div class="form-group">
                                <label>养护对象</label>
                                <input type="text" class="form-control" value="${targetspecies.species_name}" readonly>
                            </div>

                            <div class="form-group">
                                <label>养护地点</label>
                                <input type="text" class="form-control" value="${targetconserve.con_place}" readonly>
                            </div>

                            <div class="form-group">
                                <label>养护时间</label>
                                <input type="text" class="form-control" value="${targetconserve.con_ctime}" readonly>
                            </div>

                            <div class="form-group">
                                <label>更新时间</label>
                                <input type="text" class="form-control"  value="${targetconserve.con_utime}" readonly>
                            </div>

                            <div class="form-group">
                                <label>创建人</label>
                                <input type="text" class="form-control" value="${targetconserve.createdby}" readonly>
                            </div>

                            <div class="form-group">
                                <label>养护人</label>
                                <input type="text" class="form-control"  value="${targetconserve.conby}" readonly>
                            </div>
                            <div class="form-group">
                                <a href="superior_conserve.jsp"><button type="button" class="btn btn-info btn-xs">返回</button></a>
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