<%@ page import="model.entity.Doctor" %>
<%@ page import="model.entity.Patient" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
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
    <title>Nurse Page</title>
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
    </style>
</head>
<body>

<jsp:include page="elements/header.jsp" />
<c:set var="doctor" value="${sessionScope.currentNurse}"/>
<main>
    <div class="container">
        <ul class="collection with-header">
            <li class="collection-header">
                <h4> ${doctor.getUser().getName()}
                    ${ doctor.getUser().getSurname()}</h4>
            </li>
            <c:forEach items="${sessionScope.patients}" var="patient">
            <li class="collection-item ">
                <form method="post" action=${pageContext.request.contextPath}/doctor/completeAssign>
                    <input type="hidden" id="patientId2" name="patientId2" value='${ patient.getId()}'>
                    <button class="btn right cyan lighten-1"><fmt:message key="completeAssignment" /></button>
                </form>
                <span class="title"><fmt:message key="nameList"/> ${ patient.getUser().getName()}</span>

                <p><fmt:message key="surnameList"/> ${ patient.getUser().getSurname()}
                <p><fmt:message key="birthdayList"/> ${ patient.getUser().getBirthday()}
                <p><fmt:message key="currentDoctorList"/> ${patient.getDoctor().getUser().getName()} ${patient.getDoctor().getUser().getSurname()} (${patient.getDoctor().getDoctorType().getType()})
                <p><fmt:message key="assignmentTypeList"/> ${patient.getTreatment().getAssignment().getType().getType()}
                <p><fmt:message key="assignmentDescriptionList"/> ${patient.getTreatment().getAssignment().getDescription()}
                <p><fmt:message key="diagnosisList"/> ${patient.getTreatment().getDiagnosis().getType()}
            </li>
            </c:forEach>
        </ul>
    </div>
</main>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
<script>
    $(document).ready(function(){
        $('select').formSelect();
        $('.sidenav').sidenav();
        $('.modal').modal();
    });
</script>
</body>
</html>