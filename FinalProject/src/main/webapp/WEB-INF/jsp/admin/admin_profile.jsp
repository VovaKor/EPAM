<%--
  Created by Vova Korobko.  
  Date: 12.01.18  
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title>PROFILE</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <c:import url="/WEB-INF/jsp/fragments/admin_menu.jspf" charEncoding="utf-8"/>
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
        <p>Administrative console: profile</p>
        <p><b>You have to enter your current password to commit changes.</b></p>
    </div>
    <c:import url="/WEB-INF/jsp/fragments/profile.jspf" charEncoding="utf-8"/>
</div>
</body>
</html>