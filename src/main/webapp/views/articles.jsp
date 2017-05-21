<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Articles</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css'/>">
    <fmt:setLocale value="en_US" scope="request"/>
</head>
<body style="text-align: center">

<table style="margin-left: auto;margin-right: auto" cellspacing="30px">
    <c:forEach items="${articles}" var="article">
        <tr style="vertical-align: top">
            <td>${article.author}</td>
            <td>${article.publisher}</td>
            <td>${article.content}</td>
            <td>${article.date}</td>
        </tr>
    </c:forEach>


</table>
<a href="<c:url value='/articles/add'/>">Add</a>
</body>
</html>
