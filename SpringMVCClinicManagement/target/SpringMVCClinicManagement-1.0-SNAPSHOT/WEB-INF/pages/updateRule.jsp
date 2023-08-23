<%-- 
    Document   : updateRule
    Created on : Aug 14, 2023, 11:01:42 PM
    Author     : minhp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1"><spring:message code="content.rule.title.update"/></h1>
<c:url value="/rules" var="action"/>
<form:form method="post" action="${action}" modelAttribute="rule">
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="appointmentLimit" id="appointmentLimit" placeholder="..." />
        <label for="name"><spring:message code="content.rule.lmt"/></label>
        <form:errors path="appointmentLimit" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="fee" id="fee" placeholder="..." />
        <label for="price"><spring:message code="content.rule.fee"/></label>
        <form:errors path="fee" element="div" cssClass="alert alert-danger mt-1" />
    </div>

    <div class="form-floating mt-1">
        <button class="btn btn-success" type="submit"><spring:message code="content.index.btn_update"/></button>
    </div>
</form:form>