<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/foots.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css" >
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/header.jsp"/>
    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="../includes/aside.jsp"/>
    <div class="content-wrapper">
        <c:if test="${baseResult != null}">
            <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    ${baseResult}
            </div>
        </c:if>
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
            </h1>


            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">内容管理</li>
            </ol>

        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">内容管理</h3>
                            <div class="box-tools">

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="row" style="padding-left: 20px;padding-top: 10px">
                                    <a href="/user/from" type="button" class="btn btn-default"><i class="fa fa-plus-circle"></i>新增</a>
                                    <a href="#" type="button" class="btn btn-danger"><i class="fa fa-trash"></i>删除</a>
                                    <a href="#" type="button" class="btn btn-primary"><i class="fa fa-mail-forward"></i>导入</a>
                                    <a href="#" type="button" class="btn btn-primary"><i class="fa  fa-mail-reply"></i>导出</a>
                                </div>
                            </div>
                        </div>


                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="treeTable" class="table table-bordered">
                                <thead>
                                    <tr>
                                       <th>ID</th>
                                       <th>名称</th>
                                       <th>排序</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${contendCategories}" var="contendCategor">
                                        <tr id="${contendCategor.id}" pId="${contendCategor.parentId}">
                                            <td>${contendCategor.id}</td>
                                            <td>${contendCategor.name}</td>
                                            <td>${contendCategor.sortOrder}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
<jsp:include page="../includes/tail.jsp"/>
<script src="/static/assets/plugins/treeTable/jquery.treeTable.min.js"></script>
    <script>
        $(function () {
            $('#treeTable').treeTable({
                column:1,
                expandLevel:2

            })
        })
    </script>
</body>

</html>
