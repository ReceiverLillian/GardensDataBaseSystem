<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page
	import="com.rain.bean.AdminBean,com.rain.bean.HistoryBean,com.rain.dao.AdminDao"%>
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
	<%
		AdminBean admin = new AdminBean();
		String aid = (String) session.getAttribute("aid");
		AdminDao admindao = new AdminDao();
		admin = admindao.get_AidInfo2(aid);
	%>
	<nav
		class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small"
		role="navigation">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="collapse navbar-collapse main-navbar-collapse">
						<a class="navbar-brand" href="staff.jsp"><strong>欢迎使用园林植物综合管理平台</strong></a>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" role="button"
								class="dropdown-toggle" data-hover="dropdown"> <i
									class="glyphicon glyphicon-user"></i> 欢迎您，<%out.write(admin.getName());%>
	 									(<%=session.getAttribute("aid")%>)	</a>
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
					<li><a href="/books/all_book.jsp"><i
							class="glyphicon glyphicon-chevron-right"></i> 全部图书</a></li>
					<li><a href="/books/staff_book.jsp"><i
							class="glyphicon glyphicon-chevron-right"></i> 图书管理</a></li>
					<li><a href="/books/staff_tran.jsp"><i
							class="glyphicon glyphicon-chevron-right"></i> 图书流入</a></li>
					<li><a href="/books/staff_retran.jsp"><i
                           class="glyphicon glyphicon-chevron-right"></i> 图书流出</a></li>
					<li><a href="/books/staff_borrow.jsp"><i
							class="glyphicon glyphicon-chevron-right"></i> 图书借阅</a></li>

				</ul><br><br>

			</div>

			<!-- content -->
			<div class="col-md-10">
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">图书管理</div>
							</div>
							<div class="bootstrap-admin-panel-content">
								<ul>
									<li>根据图书名称、作者、分类查询图书基本信息</li>
									<li>添加、修改或删除图书信息</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">图书借阅信息</div>
							</div>
							<div class="bootstrap-admin-panel-content">
								<ul>
									<li>展示所有正在借阅的图书信息与读者信息</li>
									<li>可以实现还书与延期功能</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">图书归还信息</div>
							</div>
							<div class="bootstrap-admin-panel-content">
								<ul>
									<li>展示所有已经归还的图书信息</li>
									<li>可以查看归还读者信息</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">统计分析</div>
							</div>
							<div class="bootstrap-admin-panel-content">
								<ul>
									<li>对本单位管理的图书进行统计分析</li>
									<li>近年借阅情况统计</li>
								</ul>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	
	<!-------------------------------------------------------------->
	
	<form class="form-horizontal" method="post"
		action="/books/AdminServlet">
		<!--保证样式水平不混乱-->
		<!-- 模态框（Modal） -->
		<!--更新密码 -->
		<div class="modal fade" id="updatepwd" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">修改密码</h4>
					</div>
					<div class="modal-body">

						<!--正文-->
						<input type="hidden" name="tip" value="1"> <input
							type="hidden" name="url" value="staff">
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">原密码</label>
							<div class="col-sm-7">
								<input type="password" class="form-control" name="password"
									id="oldPwd" placeholder="请输入原密码"> <label
									class="control-label" for="oldPwd" style="display: none"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">新密码</label>
							<div class="col-sm-7">
								<input type="password" class="form-control" name="password2"
									id="newPwd" placeholder="请输入新密码"> <label
									class="control-label" for="newPwd" style="display: none"></label>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">修改</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</form>
	<!-------------------------------------------------------------->

	<!-----------------------个人资料模糊框------------------------->

	<form class="form-horizontal" method="post"
		action="/books/AdminServlet">
		<!--保证样式水平不混乱-->
		<!-- 模态框（Modal） -->
		<!-- index界面修改用户信息 -->
		<div class="modal fade" id="updateinfo" tabindex="-1" role="dialog"
			aria-labelledby="ModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="ModalLabel">个人资料</h4>
					</div>
					<div class="modal-body">

						<!--正文-->
						<input type="hidden" name="tip" value="2"> <input
							type="hidden" name="url" value="staff">
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">真实姓名</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="请输入您的真实姓名"
									value='<%out.write(admin.getName());%>'> <label
									class="control-label" for="name" style="display: none"></label>
							</div>
						</div>
                        <div class="form-group">
                        	<label for="firstname" class="col-sm-3 control-label">性别</label>
                        	<div class="col-sm-7">
                        		<input type="text" class="form-control" id="sex" name="sex"
                        			placeholder="请输入您的性别" value='<%out.write(admin.getSex());%>'>
                        		<label class="control-label" for="sex" style="display: none"></label>
                            </div>
                        </div>
						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">手机号</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="phone" name="phone"
									placeholder="请输入您的手机号"
									value='<%out.write(admin.getPhone());%>'> <label
									class="control-label" for="phone" style="display: none"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label">邮箱</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="email" name="email"
									placeholder="请输入您的邮箱" value='<%out.write(admin.getEmail());%>'>
								<label class="control-label" for="email" style="display: none"></label>
							</div>
						</div>


                        <div class="form-group">
                            <label for="firstname" class="col-sm-3 control-label">工作编号</label>
                                 <div class="col-sm-7">
                                 <input type="text" class="form-control" id="workid" name="workid"
                                     placeholder="请输入您的性别" value='<%out.write(admin.getWorkid());%>'>
                                 <label class="control-label" for="workid" style="display: none"></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="firstname" class="col-sm-3 control-label">地址</label>
                               <div class="col-sm-7">
                                  <input type="text" class="form-control" id="address" name="address"
                                     placeholder="请输入您的地址" value='<%out.write(admin.getAddress());%>'>
                                  <label class="control-label" for="address" style="display: none"></label>
                               </div>
                        </div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">修改</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</form>
</body>
</html>