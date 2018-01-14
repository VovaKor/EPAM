<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title>BUSES</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <c:import url="/WEB-INF/jsp/fragments/admin_menu.jspf" charEncoding="utf-8"/>
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
        <p>Administrative console: buses</p>
        <p>Here you can see all buses. You can choose any to appoint, change or remove a route</p>
    </div>

    <table border="1" width="100%" class="table-hover table table-striped">
        <tr>
            <th>VIN</th>
            <th>Registration number</th>
            <th>Model name</th>
            <th>Route number</th>
            <th></th>
        </tr>
        <c:forEach var="bus" items="${bus_list}" >
            <tr>
                <td><c:out value="${ bus.VIN }" /></td>
                <td><c:out value="${ bus.registrationNumber }" /></td>
                <td><c:out value="${ bus.busModel.modelName }" /></td>
                <td><c:out value="${ bus.route.routeNumber }" /></td>
                <td>
                    <form method="POST" action="do" class="inline">
                        <input type="hidden" name="command" value="appoint_route">
                        <input type="hidden" name="vin" value="<c:out value="${bus.VIN}" />">
                        <button type="submit" class="link-button">
                            Appoint route
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body></html>
