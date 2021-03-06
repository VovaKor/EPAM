<%--
  Created by Vova Korobko.  
  Date: 12.01.18  
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Profile</title></head>
<body>
<div class="bs-form">
    <form method="POST" action="do">
        <input type="hidden" name="command" value="update_profile" />
        <div class="form-group">
            <label>Your current Email:</label>
            <input type="email" name="email" class="form-control"
                   value="<c:out value="${employee.email}"/>" required="required"/>
        </div>
        <div class="form-group">
            <label>Enter your current Password:</label>
            <input type="password" name="old_password" class="form-control"
                   placeholder="Enter your new Password" required/>
        </div>
        <div class="form-group">
            <label>Enter your new Password or leave blank:</label>
            <input type="password" name="new_password" class="form-control"
                   placeholder="Enter your new Password" />
        </div>
        <div class="form-group">
            <label>First name:</label>
            <input type="text" name="first_name" class="form-control"
                   value="<c:out value="${employee.names.firstName}"/>" required="required"/>
        </div>
        <div class="form-group">
            <label>Patronymic:</label>
            <input type="text" name="patronymic" class="form-control"
                   value="<c:out value="${employee.names.patronymic}"/>" />
        </div>
        <div class="form-group">
            <label>Last name:</label>
            <input type="text" name="last_name" class="form-control"
                   value="<c:out value="${employee.names.lastName}"/>" required="required"/>
        </div>
        <div class="form-group">
            <br/>
            ${errorLoginPassMessage}
            <br/>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>
</body></html>