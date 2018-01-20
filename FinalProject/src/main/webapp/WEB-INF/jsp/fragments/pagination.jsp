<%--
  Created by Vova Korobko.  
  Date: 19.01.18  
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pagination</title>
</head>
<body>
<div class="row">
    <div class="col-sm-12">
        <div class="btn-group btn-group-justified">
            <%--For displaying Previous link except for the 1st page --%>
            <c:if test="${currentPage != 1}">
                <form method="POST" action="do" class="inline">
                    <input type="hidden" name="command" value="${command}">
                    <input type="hidden" name="page" value="${currentPage - 1}">
                    <button type="submit" class="btn btn-primary">
                        <- Previous
                    </button>
                </form>
            </c:if>

            <%--For displaying Page numbers. The when condition does not display a link for the current page--%>
            <c:forEach begin="1" end="${pageAmount}" var="i">
                <c:choose>
                    <c:when test="${currentPage == i}">
                        <button class="btn btn-primary" disabled>
                                ${i}
                        </button>
                    </c:when>
                    <c:otherwise>
                        <form method="POST" action="do" class="inline">
                            <input type="hidden" name="command" value="${command}">
                            <input type="hidden" name="page" value="${i}">
                            <button type="submit" class="btn btn-primary">
                                    ${i}
                            </button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <%--For displaying Next link --%>
            <c:if test="${currentPage < pageAmount}">
                <form method="POST" action="do" class="inline">
                    <input type="hidden" name="command" value="${command}">
                    <input type="hidden" name="page" value="${currentPage + 1}">
                    <button type="submit" class="btn btn-primary">
                        Next ->
                    </button>
                </form>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>