<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

    <title>我的商城 | 用户详情</title>
    <jsp:include page="../includes/foots.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">

    <div class="box-body">
        <table id="dataTable" class="table table-bordered">
            <tbody>
            <tr>
                <td>邮箱</td>
                <td>${tbUsers.email}</td>
            </tr>
            </tbody>
        </table>

    </div>

<jsp:include page="../includes/tail.jsp"/>
</body>

</html>
