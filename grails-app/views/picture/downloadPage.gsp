<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 09.05.2016
  Time: 23:40
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

    <g:form controller="picture" action="downloadFromUrl">
        <g:textField name="picUrl"/>
        <g:actionSubmit value="add Pic" action="downloadFromUrl"/>
    </g:form>
</body>
</html>