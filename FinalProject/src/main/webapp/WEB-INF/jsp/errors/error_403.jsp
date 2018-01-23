<%--
  Created by Vova Korobko.  
  Date: 01.01.18  
--%>

<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error Status - 403</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />"/>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
        <h2>Access is denied!!!</h2>
        <hr/>
        <br/>
        <p>
            It seems that the page you've requested has not been given privileged permissions for you to use.
            This activity may be reported in the server logs for future use
            Kindly please provide permitted URL
            <br/> We are extremely sorry about the inconvenience for not finding the requested web page. Please do
            co-operate. We wish you best.
        </p>
    </div>
</div>
</body>
</html>