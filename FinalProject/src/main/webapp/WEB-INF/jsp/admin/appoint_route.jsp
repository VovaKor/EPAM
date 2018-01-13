<%--
  Created by Vova Korobko.  
  Date: 06.01.18  
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title>APPOINT ROUTE</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <c:import url="/WEB-INF/jsp/fragments/admin_menu.jspf" charEncoding="utf-8"/>
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
        <p>Administrative console: appoint route</p>
        <p>Here you can set or remove route for bus. You can choose any route or set empty value.</p>
    </div>

    <div class="bs-form">
        <form name="appointForm" action="" method="POST" >
            <input type="hidden" name="command" value="update_bus_route" />
            <input type="hidden" name="vin" value="<c:out value="${bus.VIN}"/>" />
            <div class="form-group">
                <label>Bus VIN:</label>
                <label><c:out value="${bus.VIN}" /></label>
            </div>

            <div class="form-group">
                <label>Bus registration number:</label>
                <label><c:out value="${bus.registrationNumber}" /></label>
            </div>

            <div class="form-group">
                <label>Bus model name:</label>
                <label><c:out value="${bus.busModel.modelName}" /></label>
            </div>

            <div class="form-group">
                <label>Select bus route:</label>
                <select name="route_number">
                    <option value = "0">no route</option>
                    <c:forEach items="${routes}" var="route">
                        <c:if test="${bus.route.routeNumber == route.routeNumber}">
                            <option selected value = "${route.routeNumber}">${route.routeNumber}</option>
                        </c:if>
                        <option value = "${route.routeNumber}">${route.routeNumber}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Update bus</button>
        </form>
    </div>
</div>
</body>
</html>