<%-- 
    Document   : index
    Created on : Aug 8, 2023, 12:04:14 AM
    Author     : minhp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<section>
    <h1 class="text-center text-info mt-1"><spring:message code="content.index.title" /></h1>
    <c:url value="/" var="action"/>
    <form class="input-group mb-3" action="${action}" method="GET">
        <input type="text" class="form-control" id="name" name="name" placeholder="Tìm kiếm theo tên...">
        <button class="btn btn-success" type="submit"><spring:message code="content.index.search" /></button>
    </form>
    <a href="<c:url value="/?role=ROLE_DOCTOR" />" class="btn btn-success"><spring:message code="content.index.doctor" /></a>
    <a href="<c:url value="/?role=ROLE_NURSE" />" class="btn btn-success"><spring:message code="content.index.nurse" /></a>
    <a href="<c:url value="/?role=ROLE_PATIENT" />" class="btn btn-success"><spring:message code="content.index.patient" /></a>
    <br/>
    <a href="<c:url value="/users" />" class="btn btn-primary mt-2"><spring:message code="content.index.btn_add_user" /></a>
    <c:if test="${counter > 1}">
        <ul class="pagination mt-1 justify-content-center">
            <li class="page-item"><a class="page-link" href="<c:url value="/" />"><spring:message code="content.index.all" /></a></li>
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/" var="pageUrl">
                        <c:param name="page" value="${i}"></c:param>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>
    <table class="table table-hover">
        <thead>
            <tr>
                <th><spring:message code="content.index.avatar" /></th>
                <th><spring:message code="content.index.first_name" /></th>
                <th><spring:message code="content.index.last_name" /></th>
                <th><spring:message code="content.index.dob" /></th>
                <th><spring:message code="content.index.gender" /></th>
                <th><spring:message code="content.index.email" /></th>
                <th><spring:message code="content.index.phone" /></th>
                <th><spring:message code="content.index.address" /></th>
                <th><spring:message code="content.index.role" /></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${user}" var="item">
                <tr>
                    <td><img src="${item.avatar}" alt="avatar" width="120"/></td>
                    <td>${item.firstName}</td>
                    <td>${item.lastName}</td>
                    <td>${item.dateOfBirth}</td>
                    <td>${item.gender}</td>
                    <td>${item.email}</td>
                    <td>${item.phoneNumber}</td>
                    <td>${item.address}</td>
                    <td>${item.roleId.name}</td>
                    <td>
                        <c:url value="/users/${item.id}" var="apiDelete"/>
                        <a href="<c:url value="/users/${item.id}" />" class="btn btn-success"><spring:message code="content.index.btn_update" /></a>
                        <button onclick="deleteUser('${apiDelete}', ${item.id})" class="btn btn-danger mt-1"><spring:message code="content.index.btn_delete" /></button>
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
