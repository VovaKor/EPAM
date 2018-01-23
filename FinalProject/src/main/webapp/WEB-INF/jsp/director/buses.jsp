<%--
  Created by Vova Korobko.  
  Date: 12.01.18  
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title>BUSES</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <c:import url="/WEB-INF/jsp/fragments/director_menu.jspf" charEncoding="utf-8"/>
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
        <p>Director console: buses</p>
        <p>Here you can see all buses. You can choose any to update.</p>
        <p>By setting VIN to blank you will delete this bus.</p>
    </div>

    <table border="1" width="100%" class="table-hover table table-striped">
        <tr>
            <th>VIN</th>
            <th>Registration number</th>
            <th>Model name</th>
            <th></th>
        </tr>
        <tr>
            <td><input form="create" type="text" name="vin" required/></td>
            <td><input form="create" type="text" name="registr_number"/></td>
            <td>
                <select form="create" name="model_id">
                    <c:forEach items="${model_list}" var="model">
                        <option value = "${model.modelId}">${model.modelName}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <form id="create" method="POST" action="do" class="inline">
                    <input type="hidden" name="command" value="create_bus">
                    <button type="submit" class="link-button">
                        Create bus
                    </button>
                </form>
            </td>
        </tr>
        <c:forEach var="bus" items="${bus_list}" >
            <tr>
                <td><input form="<c:out value="${bus.VIN}" />" type="text" name="vin" value="<c:out value="${ bus.VIN }" />" /></td>
                <td><input form="<c:out value="${bus.VIN}" />" type="text" name="registr_number" value="<c:out value="${ bus.registrationNumber }" />" /></td>
                <td>
                    <select form="<c:out value="${bus.VIN}" />" name="model_id">
                        <c:forEach items="${model_list}" var="model">
                            <c:if test="${model.modelId == bus.busModel.modelId}">
                                <option selected value = "${model.modelId}">${model.modelName}</option>
                            </c:if>
                            <option value = "${model.modelId}">${model.modelName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <form id="<c:out value="${bus.VIN}" />" method="POST" action="do" class="inline">
                        <input type="hidden" name="command" value="update_bus">
                        <input type="hidden" name="old_vin" value="<c:out value="${bus.VIN}" />">
                        <button type="submit" class="link-button">
                            Update bus
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <c:import url="/WEB-INF/jsp/fragments/pagination.jsp" charEncoding="utf-8"/>
</div>
</body></html>