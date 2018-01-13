<%--
  Created by Vova Korobko.  
  Date: 07.01.18  
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fdt" uri="format_date_time" %>
<html><head><title>DRIVERS</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <c:import url="/WEB-INF/jsp/fragments/admin_menu.jspf" charEncoding="utf-8"/>
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
        <p>Administrative console: drivers</p>
        <p>Here you can see all drivers and their appointments. You can choose any to appoint, change or remove a bus</p>
    </div>

    <table border="1" width="100%" class="table-hover table table-striped">
        <tr>
            <th>First name</th>
            <th>Patronymic</th>
            <th>Last name</th>
            <th>Bus VIN</th>
            <th>Appointed date</th>
            <th>Approved date</th>
            <th></th>
        </tr>
        <c:forEach var="app" items="${app_list}" >
            <tr>
                <td><c:out value="${ app.employee.names.firstName }" /></td>
                <td><c:out value="${ app.employee.names.patronymic }" /></td>
                <td><c:out value="${ app.employee.names.lastName }" /></td>
                <td><c:out value="${ app.bus.VIN }" /></td>
                <td><c:out value="${ fdt:formatLocalDateTime(app.created) }" /></td>
                <td><c:out value="${ fdt:formatLocalDateTime(app.approved) }" /></td>
                <td>
                    <form method="POST" action="" class="inline">
                        <input type="hidden" name="command" value="appoint_bus">
                        <input type="hidden" name="employee_id" value="<c:out value="${app.employee.employeeId}" />">
                        <input type="hidden" name="id" value="<c:out value="${app.appointmentId}" />">
                        <button type="submit" class="link-button">
                            Appoint bus
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body></html>