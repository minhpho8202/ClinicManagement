<%-- 
    Document   : AddMedicine
    Created on : Aug 14, 2023, 4:29:39 PM
    Author     : minhp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:choose>
    <c:when test="${medicine.id == null}">
        <h1 class="text-center text-info mt-1"><spring:message code="content.medicine.title.add"/></h1>
    </c:when>
    <c:otherwise>
        <h1 class="text-center text-info mt-1"><spring:message code="content.medicine.title.update"/></h1>
    </c:otherwise>
</c:choose>

<c:url value="/medicines" var="action"/>
<form:form method="post" action="${action}" modelAttribute="medicine">
    <form:hidden path="id" />
    <%--<form:errors path="*" element="div" cssClass="alert alert-danger mt-1" />--%>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="..." />
        <label for="name"><spring:message code="content.medicine.name"/></label>
        <form:errors path="name" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="price" id="price" placeholder="..." />
        <label for="price"><spring:message code="content.medicine.price"/></label>
        <form:errors path="price" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="quantity" id="quantity" placeholder="..." />
        <label for="quantity"><spring:message code="content.medicine.quantity"/></label>
        <form:errors path="quantity" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="unit" name="unit" path="unitId" >
            <c:forEach items="${unit}" var="u" >
                <c:choose>
                    <c:when test="${u.id == medicine.unitId.id}">
                        <option value="${u.id}" selected>${u.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${u.id}">${u.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="unit" class="form-label"><spring:message code="content.medicine.unit"/></label>
    </div>
    <div class="form-floating mt-1">
        <c:choose>
            <c:when test="${medicine.id == null}">
                <button class="btn btn-success" type="submit"><spring:message code="content.index.btn_add"/></button>
            </c:when>
            <c:otherwise>
                <button class="btn btn-success" type="submit"><spring:message code="content.index.btn_update"/></button>
            </c:otherwise>
        </c:choose>
    </div>
</form:form>

