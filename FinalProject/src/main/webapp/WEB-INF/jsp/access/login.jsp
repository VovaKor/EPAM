<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title>LOGIN</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
    </div>
    <div class="bs-form">
        <form name="loginForm" method="POST" action="do">
            <input type="hidden" name="command" value="login" />
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="login" class="form-control"
                       placeholder="Enter Email" required="required"/>
            </div>
            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" class="form-control"
                       placeholder="Enter Password" required="required"/>
            </div>
            <div class="form-group">
                <br/>
                ${errorLoginPassMessage}
                <br/>
            </div>
            <button type="submit" class="btn btn-primary">Log in</button>

        </form>
        <form method="POST" action="do" class="inline">
            <input type="hidden" name="command" value="show_signup_view">
            <button type="submit" class="link-button">
                Register
            </button>
        </form>
    </div>

</div>
</body>
</html>
