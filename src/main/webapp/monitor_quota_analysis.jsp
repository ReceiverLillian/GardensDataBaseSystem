<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        .navbar-custom .navbar-brand,
        .navbar-custom .navbar-nav .nav-link,
        .navbar-custom .navbar-nav .nav-link .glyphicon,
        .navbar-custom .navbar-nav .nav-link:hover,
        .navbar-custom .navbar-nav .nav-link:focus {
            color: white !important; /* 使用 !important 来确保覆盖其他样式 */
        }
        /* 额外添加针对欢迎语的样式 */
        .navbar-custom .navbar-nav .dropdown a {
            color: white !important;
        }
    </style>
</head>

<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>

<body class="bootstrap-admin-with-small-navbar">
<nav
        class="navbar navbar-light bg-primary navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small  navbar-custom"
        role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="collapse navbar-collapse main-navbar-collapse">
                    <a class="navbar-brand" href="/gardens/login.jsp"><strong>园林植物监测管理</strong></a>
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
<c:if test="${not empty feedbackmessage}">
    <script>alert('${feedbackmessage}');</script>
</c:if>
<div class="container">
    <!-- left, vertical navbar & content -->
    <div class="row">
        <!-- left, vertical navbar -->
        <div class="col-md-2 bootstrap-admin-col-left">
            <ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
                <li><a href="/gardens/monitorAllPlants"><i
                        class="glyphicon glyphicon-chevron-right"></i> 监测管理</a></li>
                <li><a href="/gardens/monitorAddPlant"><i
                        class="glyphicon glyphicon-chevron-right"></i> 增加监测</a></li>
                <li><a href="/gardens/monitor_add_batchplant.jsp"><i
                        class="glyphicon glyphicon-chevron-right"></i> 批量增加监测</a></li>
                <li><a href="/gardens/monitorQuotaAnalysis"><i
                        class="glyphicon glyphicon-chevron-right"></i> 指标分析</a></li>
                <li><a href="/gardens/monitorQuotaError"><i
                        class="glyphicon glyphicon-chevron-right"></i> 异常指标</a></li>
            </ul><br><br>

        </div>

        <!-- content -->
        <div class="col-md-10">
            <div class="row">
                <div class="col-lg-10">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">指标分析</div>
                        </div>

                            <div class="bootstrap-admin-panel-content">

                                <h4>监测指标平均值</h4>
                                <ul class="list-group">
                                    <c:forEach var="entry" items="${aveValue}">
                                        <li class="list-group-item">${entry.key}: <span class="badge">${entry.value}</span></li>
                                    </c:forEach>
                                </ul>

                                <h4>监测指标最大值</h4>
                                <ul class="list-group">
                                    <c:forEach var="entry" items="${maxValue}">
                                        <li class="list-group-item">${entry.key}: <span class="badge">${entry.value}</span></li>
                                    </c:forEach>
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