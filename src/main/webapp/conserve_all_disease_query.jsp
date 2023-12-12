<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <a class="navbar-brand" href="/gardens/login.jsp"><strong>园林植物养护管理</strong></a>
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
                <li><a href="/gardens/conserveAllPlants"><i
                        class="glyphicon glyphicon-chevron-right"></i> 养护管理</a></li>
                <li><a href="/gardens/conserveAddPlant"><i
                        class="glyphicon glyphicon-chevron-right"></i> 增加养护</a></li>
                <li><a href="/gardens/conserveAllDisease"><i
                        class="glyphicon glyphicon-chevron-right"></i> 病虫害管理</a></li>
                <li><a href="/gardens/conserveAddDisease"><i
                        class="glyphicon glyphicon-chevron-right"></i> 增加病虫害</a></li>
            </ul><br><br>
        </div>
        <div class="col-md-10">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">病虫害管理-搜索结果</div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <form method="post" action="/gardens/conserveAllDiseaseQuery" class="form-inline"  id="searchform">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="searchWord" placeholder="搜索植物学名/病名/药名...">
                                </div>
                                <button type="submit" class="btn btn-default">搜索</button>
                            </form>
                            <div class="form-group">
                            </div>
                            <table class="table table-hover" >
                                <thead>
                                <tr>
                                    <th>病名</th>
                                    <th>药名</th>
                                    <th>药剂用量</th>
                                    <th>防治方法</th>
                                    <th>作用期限</th>
                                    <th>创建时间</th>
                                    <th>更新时间</th>
                                    <th>养护对象</th>

                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${diseaseMedBeans}" var="diseaseMedBeans" varStatus="status">
                                    <tr>
                                        <td><c:out value="${diseaseMedBeans.dis_name}"></c:out></td>
                                        <td><c:out value="${diseaseMedBeans.med_name}"></c:out></td>
                                        <td><c:out value="${diseaseMedBeans.dis_mednum}"></c:out></td>
                                        <td><c:out value="${diseaseBeans[status.index].dis_tech}"></c:out></td>
                                        <td>
                                            <fmt:formatDate value="${diseaseMedBeans.dis_ddl}" pattern="yyyy-MM-dd" />
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${diseaseMedBeans.dis_ctime}" pattern="yyyy-MM-dd" />
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${diseaseMedBeans.dis_utime}" pattern="yyyy-MM-dd" />
                                        </td>
                                        <td>
                                            <c:out value="${speciesBeans[status.index].species_name}"></c:out>
                                        </td>



                                        <td>
                                            <a href="/gardens/conserveAllDiseaseUpdate?dis_id=${diseaseMedBeans.dis_id}&med_id=${diseaseMedBeans.med_id}&species_id=${speciesBeans[status.index].species_id}">
                                                <button type="button" class="btn btn-info btn-xs">修改</button>
                                            </a>
                                            <a href="/gardens/conserveAllDiseaseDelete?dis_id=${diseaseMedBeans.dis_id}&med_id=${diseaseMedBeans.med_id}&species_id=${speciesBeans[status.index].species_id}">
                                                <button type="button" class="btn btn-danger btn-xs">删除</button>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="form-group">
                                <a href="/gardens/conserveAllDisease"><button type="button" class="btn btn-info btn-xs">返回</button></a>
                            </div>
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