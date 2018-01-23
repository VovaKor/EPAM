<%--
  Created by Vova Korobko.  
  Date: 10.01.18  
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title>ROUTES</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <c:import url="/WEB-INF/jsp/fragments/director_menu.jspf" charEncoding="utf-8"/>
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
        <p>Director console: routes</p>
        <p>Here you can see all routes. You can add new route, choose any existed to change or remove.</p>
        <p>By setting route number to <b>0</b> and clicking <b>Update</b> you will remove this route. Don't forget to unsubscribe buses.</p>
    </div>

    <table border="1" width="100%" class="table-hover table table-striped">
        <tr>
            <th>Route number</th>
            <th>Begin station</th>
            <th>End station</th>
            <th></th>
        </tr>
        <tr>
            <td><input form="create" type="number" name="route_number" required/></td>
            <td><input form="create" type="text" name="begin_point" required/></td>
            <td><input form="create" type="text" name="end_point" required/></td>
            <td>
                <form id="create" method="POST" action="do" class="inline">
                    <input type="hidden" name="command" value="create_route">
                    <button type="submit" class="link-button">
                        Create route
                    </button>
                </form>
            </td>
        </tr>
        <c:forEach var="route" items="${route_list}" >
            <tr>
                <td><input form="<c:out value="${route.routeNumber}" />" type="number" name="route_number" value="<c:out value="${ route.routeNumber }" />" required/></td>
                <td><input form="<c:out value="${route.routeNumber}" />" type="text" name="begin_point" value="<c:out value="${ route.beginPoint }" />" required/></td>
                <td><input form="<c:out value="${route.routeNumber}" />" type="text" name="end_point" value="<c:out value="${ route.endPoint }" />" required/></td>
                <td>
                    <form id="<c:out value="${route.routeNumber}" />" method="POST" action="do" class="inline">
                        <input type="hidden" name="command" value="update_route">
                        <input type="hidden" name="old_route_number" value="<c:out value="${route.routeNumber}" />">
                        <button type="submit" class="link-button">
                            Update route
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body></html>