<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

    <title>我的商城 | 用户列表</title>
    <jsp:include page="../includes/foots.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/header.jsp"/>
    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="../includes/aside.jsp"/>
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                新增用户
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">新增用户</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="col-md-12">
                <c:if test="${baseResult != null}">
                    <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                            ${baseResult.massage}
                    </div>
                </c:if>
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">用户</h3>
                    </div>
                    <!-- /.box-header -->
                    <!-- form start -->
                    <from:form id="inputForm" cssClass="form-horizontal" action="/user/save" method="post" modelAttribute="tbUser">
                        <div class="box-body">
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">用户名</label>

                                <div class="col-sm-10">
                                    <from:input path="username" cssClass="form-control required" placeholder="请输入用户名"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-2 control-label">密码</label>

                                <div class="col-sm-10">
                                    <from:input path="password" cssClass="form-control required" placeholder="请输入密码"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="phone" class="col-sm-2 control-label">手机</label>

                                <div class="col-sm-10">
                                    <from:input path="phone" cssClass="form-control required" placeholder="请输入手机号"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-sm-2 control-label">邮箱</label>

                                <div class="col-sm-10">
                                    <from:input path="email" cssClass="form-control required" placeholder="请输入邮箱"/>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                <button type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                        </div>
                    </from:form>

                </div>
            </div>
        </section>
    </div>
</div>
<jsp:include page="../includes/tail.jsp"/>
</body>

</html>
