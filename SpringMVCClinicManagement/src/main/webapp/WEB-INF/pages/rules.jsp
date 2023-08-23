<%-- 
    Document   : rules
    Created on : Aug 14, 2023, 10:35:24 PM
    Author     : minhp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1"><spring:message code="content.rule.title"/></h1>
<section>
    <table class="table table-hover">
        <thead>
            <tr>
                <th><spring:message code="content.rule.lmt" /></th>
                <th><spring:message code="content.rule.fee" /></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
                <tr>
                    <td>${rule.appointmentLimit}</td>
                    <td>${rule.fee}</td>
                    <td>
                        <a href="<c:url value="/rules/update/${rule.id}" />" class="btn btn-success"><spring:message code="content.index.btn_update" /></a>
                    </td>
                </tr>
        </tbody>
    </table>
</section>