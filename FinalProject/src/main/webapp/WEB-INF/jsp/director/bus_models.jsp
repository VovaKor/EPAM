<%--
  Created by Vova Korobko.  
  Date: 10.01.18  
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title>BUS MODELS</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/main.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container">
    <c:import url="/WEB-INF/jsp/fragments/director_menu.jspf" charEncoding="utf-8"/>
    <div class="jumbotron">
        <h1>CAR FLEET</h1>
        <p>Director console: bus models</p>
        <p>Here you can see all bus models. You can add a new bus model, choose any existed to change or remove.</p>
        <p>By setting bus model name to blank and clicking <b>Update</b> you will remove this bus model.</p>
        <p>If you want to delete some bus model don't forget to delete buses this model first.</p>
    </div>

    <table border="1" width="100%" class="table-hover table table-striped">
        <tr>
            <th>Bus model name</th>
            <th></th>
        </tr>
        <tr>
            <td><input form="create" type="text" name="model_name" required/></td>
            <td>
                <form id="create" method="POST" action="" class="inline">
                    <input type="hidden" name="command" value="create_bus_model">
                    <button type="submit" class="link-button">
                        Create bus model
                    </button>
                </form>
            </td>
        </tr>
        <c:forEach var="model" items="${model_list}" >
            <tr>
                <td><input form="<c:out value="${model.modelId}" />" type="text" name="model_name" value="<c:out value="${ model.modelName }" />" /></td>
                <td>
                    <form id="<c:out value="${model.modelId}" />" method="POST" action="" class="inline">
                        <input type="hidden" name="command" value="update_bus_model">
                        <input type="hidden" name="model_id" value="<c:out value="${model.modelId}" />">
                        <button type="submit" class="link-button">
                            Update bus model
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body></html>