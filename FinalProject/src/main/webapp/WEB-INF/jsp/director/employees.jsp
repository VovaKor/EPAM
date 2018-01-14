<%--
  Created by Vova Korobko.  
  Date: 09.01.18  
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title>EMPLOYEES</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <c:import url="/WEB-INF/jsp/fragments/director_menu.jspf" charEncoding="utf-8"/>
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
        <p>Director console: employees</p>
        <p>Here you can see all employees. You can choose any to appoint a position, change or remove a position</p>
        <p>By setting position to none you are blocking this employee access to the site.</p>
    </div>

    <table border="1" width="100%" class="table-hover table table-striped">
        <tr>
            <th>First name</th>
            <th>Patronymic</th>
            <th>Last name</th>
            <th>Position</th>
            <th></th>
        </tr>
        <c:forEach var="emp" items="${emp_list}" >
            <tr>
                <td><c:out value="${ emp.names.firstName }" /></td>
                <td><c:out value="${ emp.names.patronymic }" /></td>
                <td><c:out value="${ emp.names.lastName }" /></td>
                <td>
                    <select form="<c:out value="${emp.employeeId}" />" name="role_id">
                        <option value = "0">none</option>
                        <c:forEach items="${pos_list}" var="position">
                            <c:if test="${emp.position.role_id == position.role_id}">
                                <option selected value = "${position.role_id}">${position}</option>
                            </c:if>
                            <option value = "${position.role_id}">${position}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <form id="<c:out value="${emp.employeeId}" />" method="POST" action="do" class="inline">
                        <input type="hidden" name="command" value="update_position">
                        <input type="hidden" name="employee_id" value="<c:out value="${emp.employeeId}" />">
                        <button type="submit" class="link-button">
                            Update position
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body></html>
