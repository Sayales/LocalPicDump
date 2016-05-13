<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 11.05.2016
  Time: 16:07
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<div style="display: inline; width: 800px; margin: 0 auto">
    <img style="border: hidden; max-height: 800px; max-width: 800px" src="${src}">
    <g:form controller="picture" action="picEdition">
        <g:hiddenField name="picId" value="${picId}"/>
        <label>Folder: </label>
        <g:textField name="folderName" value="${folder}"/>
        <label>Tags: </label>
        <g:textField name="tagString" value="${tagString}" placeholder="Enter tags with ';' delimiter"/>
        <g:actionSubmit value="Confirm" action="picEdition"/>
    </g:form>
</div>
<a href="/LocalPicDumb/picture/">Home</a>
</body>
</html>