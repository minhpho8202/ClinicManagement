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
<section class="container mt-5">
    <form action="${action}">
        <div class="row">
            <div class="col-md-4">
                <div class="form-group">
                    <label for="fromDate"><spring:message code="content.statics.fromDate"/></label>
                    <input type="date" id="fromDate" name="fromDate" class="form-control">
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="toDate"><spring:message code="content.statics.toDate"/></label>
                    <input type="date" id="toDate" name="toDate" class="form-control">
                </div>
            </div>
            <div class="col-md-4">
                <button type="submit" class="btn btn-primary mt-4"><spring:message code="content.statics.filter"/></button>
            </div>
        </div>
    </form>
    <table class="table table-hover mt-4">
        <thead>
            <tr>
                <th><spring:message code="content.statics.AppCount"/></th>
                <th><spring:message code="content.statics.feeRev"/></th>
                <th><spring:message code="content.statics.medRev"/></th>
                <th><spring:message code="content.statics.totRev"/></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${quantity}</td>
                <td>$${totalFee}</td>
                <td>$${totalMedicine}</td>
                <td>$${totalFee + totalMedicine}</td>
            </tr>
        </tbody>
    </table>
    <div class="chart-container" style="display: flex;">
        <div style="max-width: 400px; flex: 1;">
            <canvas id="myAppointmentChart" width="400" height="400"></canvas>
        </div>
        <div style="max-width: 400px; flex: 1;">
            <canvas id="myFeeAndMedicineChart" width="400" height="400"></canvas>
        </div>
    </div>
</section>

<script>
    const ctx1 = document.getElementById('myAppointmentChart');

    new Chart(ctx1, {
        type: 'doughnut',
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

    const ctx2 = document.getElementById('myFeeAndMedicineChart');

    new Chart(ctx2, {
        type: 'bar',
        data: {
            labels: ['fee and medicine revenue compare'],
            datasets: [
                {
                    label: 'fee',
                    data: [${totalFee}],
                    borderWidth: 1
                },
                {
                    label: 'medicine',
                    data: [${totalMedicine}],
                    borderWidth: 1
                }
            ]

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
