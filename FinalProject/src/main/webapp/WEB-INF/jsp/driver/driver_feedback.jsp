<%--
  Created by Vova Korobko.  
  Date: 09.01.18  
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title>FEEDBACK</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <c:import url="/WEB-INF/jsp/fragments/driver_menu.jspf" charEncoding="utf-8"/>
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
        <p>Driver console: action result</p>
        <p>
            <c:import url="/WEB-INF/jsp/fragments/feedback.jspf" charEncoding="utf-8"/>
        </p>
    </div>

</div>
</body>
</html>