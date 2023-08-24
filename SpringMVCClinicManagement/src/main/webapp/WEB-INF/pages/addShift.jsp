<%-- 
    Document   : addShift
    Created on : Aug 15, 2023, 7:50:48 PM
    Author     : minhp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:choose>
    <c:when test="${shift.id == null}">
        <h1 class="text-center text-info mt-1"><spring:message code="content.shift.title.add"/></h1>
    </c:when>
    <c:otherwise>
        <h1 class="text-center text-info mt-1"><spring:message code="content.shift.title.update"/></h1>
    </c:otherwise>
</c:choose>
<c:url value="/shifts" var="action"/>
<form:form method="post" action="${action}" modelAttribute="shift" >
    <form:hidden path="id" />
    <%--<form:errors path="*" element="div" cssClass="alert alert-danger mt-1" />--%>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="..." />
        <label for="name"><spring:message code="content.shift.name"/></label>
        <form:errors path="name" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="datetime-local" class="form-control" path="startTime" id="startTime" placeholder="..." />
        <label for="startTime"><spring:message code="content.shift.start_time"/></label>
        <form:errors path="startTime" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="datetime-local" class="form-control" path="endTime" id="endTime" placeholder="..." />
        <label for="endTime"><spring:message code="content.shift.end_time"/></label>
        <form:errors path="endTime" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="description" id="description" placeholder="..." />
        <label for="description"><spring:message code="content.shift.description"/></label>
        <form:errors path="description" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating mt-1">
        <c:choose>
            <c:when test="${shift.id == null}">
                <button class="btn btn-success" type="submit"><spring:message code="content.index.btn_add"/></button>
            </c:when>
            <c:otherwise>
                <button class="btn btn-success" type="submit"><spring:message code="content.index.btn_update"/></button>
            </c:otherwise>
        </c:choose>
    </div>
</form:form>
