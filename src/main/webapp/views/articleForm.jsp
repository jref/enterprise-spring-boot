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
<h1>User list</h1>

<table style="margin-left: auto;margin-right: auto" cellspacing="30px">
    <form method="post" action="<c:url value='/articles'/> ">
       <%-- <tr>
            <td><input name="id" type="text" placeholder="id"/></td>
        </tr>--%>
        <tr>
            <td><input name="author" type="text" placeholder="Author"/></td>
        </tr>
           <tr>
               <td><input name="publisher" type="text" placeholder="Publisher"/></td>
           </tr>
        <tr>
            <td>
                <textarea name="content" placeholder="Enter your text here..."></textarea>
            </td>
        </tr>

        <tr>
            <td><input value="Save" type="submit"/></td>
        </tr>
    </form>
</table>

</body>
</html>
