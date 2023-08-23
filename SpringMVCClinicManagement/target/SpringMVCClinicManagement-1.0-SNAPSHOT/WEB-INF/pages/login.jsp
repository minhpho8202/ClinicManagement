<%-- 
    Document   : login
    Created on : Aug 14, 2023, 12:13:38 AM
    Author     : minhp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${param.error} != null" >
    <div class="alert alert-danger">
        <spring:message code="login.wrongErr" />
    </div>
</c:if>

<c:if test="${param.accessDenied} != null" >
    <div class="alert alert-danger">
        <spring:message code="login.wrongErr" />
    </div>
</c:if>
<c:url value="/login" var="action" />
<div class="container vh-100 d-flex justify-content-center align-items-center">
    <div class="col-md-6">
        <h2 class="text-center"><spring:message code="header.log_in"/></h2>
        <c:url value="/login" var="action" />
        <form method="post" action="${action}">
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="username" placeholder="..." name="username">
                <label for="username"><spring:message code="content.index.username"/></label>
            </div>
    
            <div class="form-floating mb-3">
                <input type="password" class="form-control" id="password" placeholder="..." name="password">
                <label for="password"><spring:message code="content.index.password"/></label>
            </div>
            
            <div class="form-floating">
                <input type="submit" value="<spring:message code='login.button'/>" class="btn btn-success w-100" />
            </div>
        </form>
    </div>
</div>