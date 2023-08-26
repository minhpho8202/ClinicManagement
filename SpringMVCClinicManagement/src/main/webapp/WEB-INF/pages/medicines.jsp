<%-- 
    Document   : medicines
    Created on : Aug 14, 2023, 3:49:03 PM
    Author     : minhp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1"><spring:message code="content.medicine.title"/></h1>
<a href="<c:url value="/medicines/add" />" class="btn btn-primary"><spring:message code="content.medicine.title.add_medicine" /></a>
<c:if test="${counter > 1}">
    <ul class="pagination mt-1 justify-content-center">
        <li class="page-item"><a class="page-link" href="<c:url value="/medicines" />">Tất cả</a></li>
            <c:forEach begin="1" end="${counter}" var="i">
                <c:url value="/medicines" var="pageUrl">
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
                <th><spring:message code="content.medicine.name" /></th>
                <th><spring:message code="content.medicine.price" /></th>
                <th><spring:message code="content.medicine.quantity" /></th>
                <th><spring:message code="content.medicine.created" /></th>
                <th><spring:message code="content.medicine.updated" /></th>
                <th><spring:message code="content.medicine.unit" /></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${medicine}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td>${item.quantity}</td>
                    <td>${item.createdDate}</td>
                    <td>${item.updatedDate}</td>
                    <td>${item.unitId.name}</td>
                    <td>
                        <c:url value="/medicines/${item.id}" var="apiDelete"/>
                        <a href="<c:url value="/medicines/update/${item.id}" />" class="btn btn-success"><spring:message code="content.index.btn_update" /></a>
                        <button onclick="deleteMedicine('${apiDelete}', ${item.id})" class="btn btn-danger mt-1"><spring:message code="content.index.btn_delete" /></button>
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
