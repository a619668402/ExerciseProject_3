<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020-12-15
  Time: 09:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <button id="btn">click</button>
  <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
  <script>
    $(function () {
        $("#btn").click(function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/getone/123',
                success: function (data) {
                    console.log(data)
                }
            })
        })
    })
  </script>
  </body>
</html>
