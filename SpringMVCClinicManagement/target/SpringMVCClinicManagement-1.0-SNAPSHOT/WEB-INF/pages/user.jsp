<%-- 
    Document   : user
    Created on : Aug 9, 2023, 1:12:52 AM
    Author     : minhp
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:choose>
    <c:when test="${user.id == null}">
        <h1 class="text-center text-info mt-1"><spring:message code="content.user.title.add"/></h1>
    </c:when>
    <c:otherwise>
        <h1 class="text-center text-info mt-1"><spring:message code="content.user.title.update"/></h1>
    </c:otherwise>
</c:choose>

<c:url value="/users" var="action"/>
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data" >
    <form:hidden path="id" />
    <form:hidden path="avatar" />
    <%--<form:errors path="*" element="div" cssClass="alert alert-danger mt-1" />--%>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="firstName" id="first_name" placeholder="..." />
        <label for="first_name"><spring:message code="content.index.first_name"/></label>
        <form:errors path="firstName" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="lastName" id="last_name" placeholder="..." />
        <label for="last_name"><spring:message code="content.index.last_name"/></label>
        <form:errors path="lastName" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="date" class="form-control" path="dateOfBirth" id="dob" placeholder="..." />
        <label for="dob"><spring:message code="content.index.dob"/></label>
        <form:errors path="dateOfBirth" element="div" cssClass="alert alert-danger mt-1" />
    </div>


    <div class="form-floating mb-3 mt-3">
        <form:select path="gender" class="form-select" id="gender">
            <form:option value="Male">Nam</form:option>
            <form:option value="Female">Nữ</form:option>
            <form:option value="Other">Khác</form:option>
        </form:select>
        <label for="gender"><spring:message code="content.index.gender"/></label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="email" id="email" placeholder="..." />
        <label for="email"><spring:message code="content.index.email"/></label>
        <form:errors path="email" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="phoneNumber" id="phone" placeholder="..." />
        <label for="phone"><spring:message code="content.index.phone"/></label>
        <form:errors path="phoneNumber" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="address" id="address" placeholder="..." />
        <label for="address"><spring:message code="content.index.address"/></label>
        <form:errors path="address" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="username" id="username" placeholder="..." />
        <label for="username"><spring:message code="content.index.username"/></label>
        <form:errors path="username" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="password" class="form-control" path="password" id="password" placeholder="..." />
        <label for="password"><spring:message code="content.index.password"/></label>
        <form:errors path="password" element="div" cssClass="alert alert-danger mt-1" />
    </div>
    <c:choose>
        <c:when test="${user.id == null}">
            <div class="form-floating mb-3 mt-3">
                <form:input type="password" class="form-control" path="confirmPassword" id="confirm_password" placeholder="..."/>
                <label for="confirm_password"><spring:message code="content.index.confirm_password"/></label>
                <form:errors path="confirmPassword" element="div" cssClass="alert alert-danger mt-1" />
            </div>
        </c:when>
        <c:otherwise>
            <div class="form-floating mb-3 mt-3">
                <form:input type="password" class="form-control" path="confirmPassword" id="confirm_password" value="${user.password}" placeholder="..."/>
                <label for="confirm_password"><spring:message code="content.index.confirm_password"/></label>
                <form:errors path="confirmPassword" element="div" cssClass="alert alert-danger mt-1" />
            </div>
        </c:otherwise>
    </c:choose>
    <div class="form-floating">
        <form:select class="form-select" id="role" name="role" path="roleId" >
            <c:forEach items="${role}" var="r" >
                <c:choose>
                    <c:when test="${r.id == user.roleId.id}">
                        <option value="${r.id}" selected>${r.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${r.id}">${r.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="role" class="form-label"><spring:message code="content.index.role"/></label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file" id="file" placeholder="..." />
        <label for="file"><spring:message code="content.index.avatar"/></label>
    </div>
    <div class="form-floating mt-1">
        <c:choose>
            <c:when test="${user.id == null}">
                <button class="btn btn-success" type="submit"><spring:message code="content.index.btn_add"/></button>
            </c:when>
            <c:otherwise>
                <button class="btn btn-success" type="submit"><spring:message code="content.index.btn_update"/></button>
            </c:otherwise>
        </c:choose>
    </div>
</form:form>

