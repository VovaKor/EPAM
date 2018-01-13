<%--
  Created by Vova Korobko.  
  Date: 08.01.18  
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fdt" uri="format_date_time" %>
<html><head><title>APPOINT BUS</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <c:import url="/WEB-INF/jsp/fragments/admin_menu.jspf" charEncoding="utf-8"/>
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
        <p>Administrative console: appoint bus</p>
        <p>Here you can set or remove bus for driver. You can choose any free bus or set empty value.</p>
    </div>

    <div class="bs-form">
        <form name="appointForm" action="" method="POST" >
            <input type="hidden" name="command" value="update_driver_bus" />
            <input type="hidden" name="employee_id" value="<c:out value="${app.employee.employeeId}"/>" />
            <div class="form-group">
                <label>First name:</label>
                <label><c:out value="${app.employee.names.firstName}" /></label>
            </div>

            <div class="form-group">
                <label>Patronymic:</label>
                <label><c:out value="${app.employee.names.patronymic}" /></label>
            </div>

            <div class="form-group">
                <label>Last name:</label>
                <label><c:out value="${app.employee.names.lastName}" /></label>
            </div>

            <div class="form-group">
                <label>Date appointed:</label>
                <label><c:out value="${fdt:formatLocalDateTime(app.created)}" /></label>
            </div>

            <div class="form-group">
                <label>Date approved:</label>
                <label><c:out value="${fdt:formatLocalDateTime(app.approved)}" /></label>
            </div>

            <div class="form-group">
                <label>Current bus vin:</label>
                <label><c:out value="${app.bus.VIN}" /></label>
            </div>

            <div class="form-group">
                <label>Select bus for driver:</label>
                <select name="bus_id">
                    <option value = "">no bus</option>
                    <c:forEach items="${id_list}" var="bus_id">
                        <option value = "${bus_id}">${bus_id}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Update driver</button>
        </form>
    </div>
</div>
</body>
</html>