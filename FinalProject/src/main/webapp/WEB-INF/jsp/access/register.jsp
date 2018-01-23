<%--
  Created by Vova Korobko.  
  Date: 12.01.18  
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title>REGISTER</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
    </div>
    <div class="bs-form">
        <form method="POST" action="do">
            <input type="hidden" name="command" value="register" />
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" class="form-control"
                       placeholder="Enter Email" required="required"/>
            </div>
            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" class="form-control"
                       placeholder="Enter Password" required="required"/>
            </div>
            <div class="form-group">
                <label>First name:</label>
                <input type="text" name="first_name" class="form-control"
                       placeholder="Enter First Name" required="required"/>
            </div>
            <div class="form-group">
                <label>Patronymic:</label>
                <input type="text" name="patronymic" class="form-control"
                       placeholder="Enter Patronymic" />
            </div>
            <div class="form-group">
                <label>Last name:</label>
                <input type="text" name="last_name" class="form-control"
                       placeholder="Enter Last Name" required="required"/>
            </div>
            <div class="form-group">
                <br/>
                ${errorLoginPassMessage}
                <br/>
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>

</div>
</body>
</html>