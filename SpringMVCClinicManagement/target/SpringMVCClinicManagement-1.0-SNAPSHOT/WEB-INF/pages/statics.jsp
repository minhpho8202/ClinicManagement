<%-- 
    Document   : statics
    Created on : Aug 24, 2023, 9:32:34 PM
    Author     : minhp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1"><spring:message code="content.statics.title"/></h1>
<section>
    <table class="table table-hover">
        <thead>
            <tr>
                <th> appointment quantity</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${quantity}</td>
            </tr>
        </tbody>
    </table>
</section>
<div>
    <canvas id="myChart"></canvas>
</div>

<script>
    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['appointment quantity'],
            datasets: [{
                    label: 'appointment quantity',
                    data: [${quantity}],
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
