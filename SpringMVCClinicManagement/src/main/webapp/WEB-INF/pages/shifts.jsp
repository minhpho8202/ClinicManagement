<%-- 
    Document   : Shifts
    Created on : Aug 14, 2023, 10:07:50 PM
    Author     : minhp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1"><spring:message code="content.shift.title"/></h1>
<a href="<c:url value="/shifts/add" />" class="btn btn-primary"><spring:message code="content.shift.btn_add_user" /></a>
<c:if test="${counter > 1}">
    <ul class="pagination mt-1 justify-content-center">
        <li class="page-item"><a class="page-link" href="<c:url value="/" />">Tất cả</a></li>
            <c:forEach begin="1" end="${counter}" var="i">
                <c:url value="/" var="pageUrl">
                    <c:param name="page" value="${i}"></c:param>
                </c:url>
            <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
            </c:forEach>
    </ul>
</c:if>
<section>
    <table class="table table-hover">
        <thead>
            <tr>
                <th><spring:message code="content.shift.name" /></th>
                <th><spring:message code="content.shift.start_time" /></th>
                <th><spring:message code="content.shift.end_time" /></th>
                <th><spring:message code="content.shift.description" /></th>
                <th><spring:message code="content.shift.employee" /></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${shift}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.startTime}</td>
                    <td>${item.endTime}</td>
                    <td>${item.description}</td>
                    <td>
                        <c:forEach items="${userShift}" var="us">
                            <c:if test="${us.shiftId.id == item.id}" >
                                ${us.userId.roleId.name} ${us.userId.firstName} ${us.userId.lastName}<br>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:url value="/shifts/${item.id}" var="apiDelete"/>
                        <a href="<c:url value="/shifts/${item.id}/employee" />" class="btn btn-primary"><spring:message code="content.index.btn_add_emp" /></a>
                        <a href="<c:url value="/shifts/${item.id}" />" class="btn btn-success"><spring:message code="content.index.btn_update" /></a>
                        <button onclick="deleteShift('${apiDelete}', ${item.id})" class="btn btn-danger mt-1"><spring:message code="content.index.btn_delete" /></button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${counter > 1}">
        <ul class="pagination mt-1 justify-content-center">
            <li class="page-item"><a class="page-link" href="<c:url value="/" />">Tất cả</a></li>
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/" var="pageUrl">
                        <c:param name="page" value="${i}"></c:param>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>
</section>

<script src="<c:url value="/js/main.js" />" ></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>