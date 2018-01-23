<%--
  Created by Vova Korobko.  
  Date: 30.12.17  
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html><head><title>Error - page not found</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
        <h1>Oops, we couldn't find the page you were looking for...</h1>
        <br/>
        <h1>... but we can take you to</h1>
        <form method="POST" action="do" class="inline">
            <input type="hidden" name="command" value="login">
            <button type="submit" class="link-button">
                Login page
            </button>
        </form>
    </div>

</div>

</body></html>
