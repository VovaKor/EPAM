<%--
  Created by Vova Korobko.  
  Date: 08.01.18  
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fdt" uri="format_date_time" %>
<html><head><title>APPOINTMENT</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <c:import url="/WEB-INF/jsp/fragments/driver_menu.jspf" charEncoding="utf-8"/>
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
        <p>Driver console: current appointment</p>
        <p>Here you can see your appointment. If you still do not have any appointment all fields must be blank.</p>
        <p><b>You have to approve your appointment.</b></p>
    </div>

    <div class="bs-form">
        <form name="appointForm" action="do" method="POST" >
            <input type="hidden" name="command" value="approve_appointment" />
            <input type="hidden" name="id" value="<c:out value="${app.appointmentId}"/>" />
            <div class="form-group">
                <label>Bus VIN:</label>
                <label><c:out value="${app.bus.VIN}" /></label>
            </div>
            <div class="form-group">
                <label>Bus registration number:</label>
                <label><c:out value="${app.bus.registrationNumber}" /></label>
            </div>
            <div class="form-group">
                <label>Bus model name:</label>
                <label><c:out value="${app.bus.busModel.modelName}" /></label>
            </div>
            <div class="form-group">
                <label>Route number:</label>
                <label><c:out value="${app.bus.route.routeNumber}" /></label>
            </div>
            <div class="form-group">
                <label>Begin station:</label>
                <label><c:out value="${app.bus.route.beginPoint}" /></label>
            </div>
            <div class="form-group">
                <label>End station:</label>
                <label><c:out value="${app.bus.route.endPoint}" /></label>
            </div>
            <div class="form-group">
                <label>Date appointed:</label>
                <label><c:out value="${fdt:formatLocalDateTime(app.created)}" /></label>
            </div>
            <div class="form-group">
                <label>Date approved:</label>
                <label><c:out value="${fdt:formatLocalDateTime(app.approved)}" /></label>
            </div>
            <button type="submit" class="btn btn-primary">Approve appointment</button>
        </form>
    </div>
</div>
</body>
</html>