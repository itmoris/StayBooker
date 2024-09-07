<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set scope="session" var="isAuth" value="${sessionScope.get(\"user\") != null}"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">StayBooker</a>
    <div class="collapse navbar-collapse">
        <c:choose>
            <c:when test="${isAuth}">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                </ul>
            </c:when>

            <c:otherwise>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/registration">Register</a></li>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</nav>

