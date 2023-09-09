<%-- 
    Document   : header
    Created on : Aug 8, 2023, 1:11:43 AM
    Author     : minhp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:url value="/" var="action" />
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid container">
        <a class="navbar-brand" href="<c:url value="/"/>"><spring:message code="header.name" /></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/"/>"><spring:message code="header.user" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/medicines"/>"><spring:message code="header.medicine" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/shifts"/>"><spring:message code="header.shift" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/rules"/>"><spring:message code="header.rule" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/statics"/>"><spring:message code="header.statics" /></a>
                </li>


            </ul>
            <div class="nav-item">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                        <a class="dropdown-toggle btn btn-outline-primary me-3" href="#" id="languageDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <spring:message code="header.language" />
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="languageDropdown">
                            <li><a class="dropdown-item" href="?lang=en">English</a></li>
                            <li><a class="dropdown-item" href="?lang=vi">Vietnamese</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <div class="d-flex align-items-center">
                        <a class="me-3 btn btn-outline-primary" href="<c:url value="/" />">${pageContext.request.userPrincipal.name}</a>
                        <a class="btn btn-outline-primary" href="<c:url value="/logout" />"><spring:message code="header.log_out" /></a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="d-flex align-items-center">
                        <a class="btn btn-outline-primary" href="<c:url value="/login" />"><spring:message code="header.log_in" /></a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>
