<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020-12-15
  Time: 09:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>getALL</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
    <div>
        <table class="table table-striped table-bordered" style="text-align: center">
            <thead>
                <tr>
                    <td>ID</td>
                    <td>UserName</td>
                    <td>PassWord</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.userName}</td>
                        <td>${item.passWord}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
