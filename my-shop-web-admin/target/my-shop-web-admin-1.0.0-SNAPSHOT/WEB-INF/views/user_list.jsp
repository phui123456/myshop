<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>WeMedia | 用户列表</title>
    <jsp:include page="${ctxInclude}/head.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="${ctxInclude}/header.jsp"/>
    <jsp:include page="${ctxInclude}/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户管理
                <small>用户列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">用户列表</li>
            </ol>
        </section>

        <section class="content">
            <c:if test="${message != null}">
                <div class="alert alert-success alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        ${message}
                </div>
            </c:if>

            <div class="row">
                <div class="col-xs-12">
                    <!-- Horizontal Form -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">高级搜索</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <div class="form-horizontal">
                            <div class="box-body">
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">用户名</label>
                                    <div class="col-sm-3">
                                        <input id="username" type="text" class="form-control" placeholder="用户名">
                                    </div>
                                    <label class="col-sm-1 control-label">邮箱</label>
                                    <div class="col-sm-3">
                                        <input id="email" type="text" class="form-control" placeholder="邮箱">
                                    </div>

                                    <label class="col-sm-1 control-label">手机</label>
                                    <div class="col-sm-3">
                                        <input id="phone" type="text" class="form-control" placeholder="手机">
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button id="btnDataTableReset" type="button" class="btn btn-default">重置</button>
                                <button id="btnDataTableSearch" type="button" class="btn btn-primary pull-right">搜索</button>
                            </div>
                            <!-- /.box-footer -->
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-primary">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>

                            <ul class="nav nav-pills pull-right">
                                <li><a href="/user/form"><i class="fa fa-fw fa-plus text-primary"></i> 新增</a></li>
                                <li><a href="javascript:void(0);" onclick="WeMedia.showEdit('/user/form?id=')"><i class="fa fa-fw fa-edit text-info"></i> 编辑</a></li>
                                <li><a href="javascript:void(0);" onclick="WeMedia.showDeleteMulti('/user/delete/multi?ids=');"><i class="fa fa-fw fa-trash text-danger"></i> 删除</a></li>
                            </ul>
                        </div>
                        <div class="box-body">
                            <div class="table-responsive">
                                <table id="dataTable" class="table table-bordered table-striped table-hover nowrap">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" class="minimal chk_master"></th>
                                        <th>用户名</th>
                                        <th>邮箱</th>
                                        <th>手机</th>
                                        <th>更新时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <jsp:include page="${ctxInclude}/footer.jsp"/>
</div>

<jsp:include page="${ctxInclude}/foot.jsp"/>
<script>
    $(function () {
        // 初始化 DataTables
        var dataTable = WeMedia.initDataTables("/user/data", [
            {
                data: function (row, type, set, meta) {
                    return "<input id='" + row.id + "' type='checkbox' class='minimal chk_slave' />";
                }
            },
            {data: 'username'},
            {data: 'email'},
            {data: 'phone'},
            {
                data: function (row, type, set, meta) {
                    return DateTimeUtils.dateFormat("yyyy-MM-dd hh:mm:ss", new Date(row.updated));
                }
            },
            {
                data: function (row, type, set, meta) {
                    return "<a href='javascript:void(0);' class='btn btn-sm btn-default' onclick='WeMedia.showDetail(\"/user/detail?id=\" + " + row.id + ")'><i class='fa fa-fw fa-search' title='查看'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<a href='/user/form?id=" + row.id + "' class='btn btn-sm btn-info'><i class='fa fa-fw fa-edit' title='编辑'></i></a>&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<a href='javascript:void(0);' class='btn btn-sm btn-danger' onclick='WeMedia.showDelete(\"/user/delete?id=\" + " + row.id + ")'><i class='fa fa-fw fa-trash-o' title='删除'></i></a>";
                }
            }
        ]);

        // 绑定搜索事件
        $("#btnDataTableSearch").on("click", function () {
            // 动态传参数
            dataTable.settings()[0].ajax.data = {
                "username": $("#username").val(),
                "email": $("#email").val(),
                "phone": $("#phone").val()
            };
            dataTable.ajax.reload();
        });
    });
</script>
</body>
</html>