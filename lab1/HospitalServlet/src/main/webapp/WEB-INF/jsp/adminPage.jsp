<%@ page import="model.entity.Doctor" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.Patient" %>
<%@ page import="model.service.PatientService" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale"/>

<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">
    <title>Admin Page</title>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

        google.charts.load('current', {'packages':['corechart']});

        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {

            var data = google.visualization.arrayToDataTable([
                ['Doctor', 'Count of patients'],
                <c:forEach items="${allDoctors}" var="entry">
                [ '${entry.getUser().getName()} ${entry.getUser().getSurname()}', ${sessionScope.patientService.findAllDoctorPatients(entry.getId()).size()} ],
                </c:forEach>
            ]);

            var options = {
                'title' : 'Doctor\'s patient count',
                is3D : true,
                pieSliceText: 'label',
                tooltip :  {showColorCode: true},
                'width' : 1000,
                'height' : 500
            };

            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));

            chart.draw(data, options);
        }
    </script>
    <script type="text/javascript">

        google.charts.load('current', {'packages':['corechart']});

        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {

            var data = google.visualization.arrayToDataTable([
                ['Doctor', 'Count of patients'],
                <c:forEach items="${allDoctors}" var="entry">
                [ '${entry.getUser().getName()} ${entry.getUser().getSurname()}', ${sessionScope.patientService.findAllDoctorPatients(entry.getId()).size()} ],
                </c:forEach>
            ]);

            var options = {
                'title' : 'Doctor\'s patient count',
                is3D : true,
                pieSliceText: 'label',
                tooltip :  {showColorCode: true},
                'width' : 1000,
                'height' : 500
            };

            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));

            chart.draw(data, options);
        }
    </script>
    <style>
        body {
            display: flex;
            min-height: 100vh;
            flex-direction: column;
            background: url("https://www.itij.com/sites/default/files/styles/og_image/public/2021-08/hospital-modern.jpg?h=f8702894&itok=r_2edjAc") no-repeat center fixed;
            background-size: cover;
        }

        main {
            flex: 1 0 auto;
        }

        .col form{
            width:50%;
            margin:0 auto;
        }
        .buttonHolder{
            text-align: center;
            margin: 2vh 2vh 2vh 2vh;
        }

    </style>
</head>
<body>
<jsp:include page="elements/header.jsp"/>

<main>
    <div class="container">
        <ul class="collection with-header">
            <li class="collection-header"><h4>Admin
            </h4>
                <c:choose>
                    <c:when test="${requestScope.bad_getaway == 'emptyDoctor'}">
                        <p class="red-text" style="text-align: center"><fmt:message key="emptyDoctor" /></p>
                    </c:when>
                </c:choose>
            </li>
        </ul>
    </div>

    <div class="container" style="background: white; min-height: auto;">
        <div class="col" style=" display: flex; flex-direction: column; text-align: center">
            <form action="${pageContext.request.contextPath}/admin/registerDoctor">
                <p class="buttonHolder">
                    <button class="btn cyan lighten-1"><fmt:message key="registerDoctor"/></button>
                </p>
            </form>
            <form action="${pageContext.request.contextPath}/admin/registerPatient">
                <p class="buttonHolder">
                    <button class="btn cyan lighten-1"><fmt:message key="registerPatient"/></button>
                </p>
            </form>
            <form action="${pageContext.request.contextPath}/admin/adminDoctorsPage">
                <p class="buttonHolder">
                    <input type="hidden" name="currentDoctorsPage" value="1">
                    <button class="btn cyan lighten-1"><fmt:message key="doctorsList"/></button>
                </p>
            </form>
            <form action="${pageContext.request.contextPath}/admin/adminPatientsPage">
                <p class="buttonHolder">
                    <input type="hidden" name="currentPatientsPage" value="1">
                    <button class="btn cyan lighten-1"><fmt:message key="patientsList"/></button>
                </p>
            </form>
            <div id="chart_div"></div>
        </div>
    </div>
</main>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
<script>
    $(document).ready(function () {
        $('select').formSelect();
        $('.tabs').tabs();
        $('.sidenav').sidenav();
        $('.modal').modal();
    });
</script>
</body>
</html>
