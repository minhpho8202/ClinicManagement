<%-- 
    Document   : addEmployee
    Created on : Aug 16, 2023, 12:08:47 AM
    Author     : minhp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1"><spring:message code="content.shift.employee.title.add"/></h1>
<section>
    <table class="table table-hover">
        <thead>
            <tr>
                <th><spring:message code="content.shift.name" /></th>
                <th><spring:message code="content.shift.start_time" /></th>
                <th><spring:message code="content.shift.end_time" /></th>
                <th><spring:message code="content.shift.description" /></th>
                <th><spring:message code="content.shift.employee" /></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${shift.name}</td>
                <td>${shift.startTime}</td>
                <td>${shift.endTime}</td>
                <td>${shift.description}</td>
                <td>
                    <c:forEach items="${userShiftList}" var="us">
                        <div class="mt-1">
                            <span>${us.userId.roleId.name} ${us.userId.firstName} ${us.userId.lastName}</span>
                                <c:url value="/shifts/${shift.id}/employee/${us.userId.id}" var="delete"/>
                                <button class="btn btn-danger btn-sm" onclick="deleteUserShift('${delete}', ${us.userId.id})">
                                    &#x2718;
                                </button>
                        </div>

                        <br>
                    </c:forEach>
                </td>
            </tr>
        </tbody>
    </table>
    <c:url value="/user-shift" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="userShift">
        <form:hidden path="shiftId" value="${shift.id}" />
        <form:errors path="*" element="div" cssClass="alert alert-danger mt-1" />
        <div class="form-floating">
            <form:select class="form-select" id="userId" name="userId" path="userId" >
                <c:forEach items="${user}" var="u" >
                    <option value="${u.id}">${u.roleId.name} ${u.firstName} ${u.lastName}</option>
                </c:forEach>
            </form:select>
            <label for="userId" class="form-label"><spring:message code="content.shift.employee"/></label>
        </div>
        <div class="form-floating mt-1">
            <button class="btn btn-success" type="submit"><spring:message code="content.index.btn_add"/></button>
        </div>
    </form:form>
</section>

<script src="<c:url value="/js/main.js" />" ></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>