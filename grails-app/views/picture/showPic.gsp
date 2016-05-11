<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 10.05.2016
  Time: 0:34
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
 <g:each in="${srcList}" var="source">
    <img src="${source}"> <br>
 </g:each>
</body>
</html>