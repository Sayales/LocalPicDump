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
<g:form controller="picture" action="downloadFromUrl">
    <g:textField name="picUrl"/>
    <g:actionSubmit value="add Pic" action="downloadFromUrl "/>
</g:form>
<g:form contoller="picture" action="showTagged">
    <g:textField name="tagName"/>
    <g:actionSubmit value="Show with tag" action="showTagged"/>
</g:form>
 <g:each in="${srcList}" var="source">
    <a href="editPic/${source.id}"><img style="border: hidden; max-height: 200px; width: auto"  src="${source.src}"></a>
 </g:each>
<br>
<ul>
<g:each in="${folders}" var="folder">
    <li><a href="showFolder/${folder.name}">${folder.name}</a> </li>
</g:each>
</ul>
<a href="/LocalPicDumb/picture/">Home</a>
</body>
</html>