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
    <title>Doctor Page</title>
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
<c:set var="doctor" value="${sessionScope.currentDoctor}"/>
<main>
    <div class="container">
        <ul class="collection with-header">
            <li class="collection-header">
                <h4> ${doctor.getUser().getName()}
                ${doctor.getUser().getSurname()}</h4>
                <c:choose>
                    <c:when test="${requestScope.bad_getaway == 'emptyExecutor'}">
                        <p class="red-text" style="text-align: center"><fmt:message key="emptyExecutor" /></p>
                    </c:when>
                    <c:when test="${requestScope.bad_getaway == 'emptyAssignmentType'}">
                        <p class="red-text" style="text-align: center"><fmt:message key="emptyAssignmentType" /></p>
                    </c:when>
                    <c:when test="${requestScope.bad_getaway == 'nurseCannotDoOperations'}">
                        <p class="red-text" style="text-align: center"><fmt:message key="nurseCannotDoOperations" /></p>
                    </c:when>
                    <c:when test="${requestScope.bad_getaway == 'emptyDiagnosis'}">
                        <p class="red-text" style="text-align: center"><fmt:message key="emptyDiagnosis" /></p>
                    </c:when>
                </c:choose>
            </li>

            <c:forEach items="${sessionScope.patients}" var="patient" varStatus="status">
            <li class="collection-item ">
                <c:set var="currDoctorId" value="${sessionScope.currentDoctor.getId()}"/>
                <c:set var="executorId" value="${patient.getTreatment().getAssignment().getExecutor().getId()}"/>
                <c:set var="patientDoctorId" value="${patient.getDoctor().getId()}"/>
                <c:if test="${executorId == currDoctorId}">
                    <form method="post" action=${pageContext.request.contextPath}/doctor/completeAssign>
                        <input type="hidden" id="patientId2" name="patientId2" value='${patient.getId()}'>
                        <button class="btn right cyan lighten-1"><fmt:message key="completeAssignment" /></button>
                    </form>
                </c:if>
                <span class="title"><fmt:message key="nameList"/> ${patient.getUser().getName()}</span>

                <p><fmt:message key="surnameList"/> ${patient.getUser().getSurname()}
                    <c:if test="${currDoctorId == patientDoctorId}">
                <a class="btn right cyan lighten-1 modal-trigger" href="#modal_${status.index}"><fmt:message key="createAssignment" /></a>

                <div id="modal_${status.index}" class="modal" style="min-height: 50%">
                    <form method="post" action=${pageContext.request.contextPath}/doctor/createAssign class="center-block" style="text-align: center; min-width: 100%">
                        <div class="container">
                            <div class="input-field" >
                                <input type="hidden" id="patientId" name="patientId" value='${patient.getId()}'>
                            </div>

                            <div class="input-field" style="margin-top: 40px">
                                <select name="doctorId2">
                                    <option value="0" disabled selected><fmt:message key="executor"/></option>
                                    <c:forEach begin="1" end="${sessionScope.allDoctorsNurses.size() - 1}" varStatus="loop">
                                        <option value= "${sessionScope.allDoctorsNurses.get(loop.index - 1).getId()}">${sessionScope.allDoctorsNurses.get(loop.index - 1).getUser().getName()} ${sessionScope.allDoctorsNurses.get(loop.index - 1).getUser().getSurname()} (${sessionScope.allDoctorsNurses.get(loop.index - 1).getDoctorType().getType()})</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-field ">
                                <input type="text" id="description" name="description">
                                <label class="active" for="description"><fmt:message key="description"/></label>
                            </div>
                            <select name="assignmentType">
                                <option value="0" disabled selected><fmt:message key="assignmentType"/></option>
                                <c:forEach begin="1" end="${sessionScope.assignmentTypes.size() - 1}" varStatus="loop">
                                    <option value= "${sessionScope.assignmentTypes.get(loop.index - 1).getId()}">${sessionScope.assignmentTypes.get(loop.index - 1).getType()}</option>
                                </c:forEach>
                            </select>
                            <div class="input-field center" style="margin-bottom: 40px">
                                <button class="btn right cyan lighten-1" type="submit"><fmt:message key="submit"/></button>
                            </div>
                        </div>
                    </form>
                </div>
                </c:if>
                <p><fmt:message key="birthdayList"/> ${patient.getUser().getBirthday()}
                    <c:if test="${currDoctorId == patientDoctorId}">
                <a class="btn right cyan lighten-1 modal-trigger" href="#mod_${status.index}"><fmt:message key="defineDiagnosis" /></a>

                <div id="mod_${status.index}" class="modal" style="min-height: 50%">
                    <form method="post" action=${pageContext.request.contextPath}/doctor/defineDiagnosis class="center-block" style="text-align: center; min-width: 80%">
                        <div class="container">
                        <div class="input-field">
                            <input type="hidden" id="patientId1" name="patientId1" value='${patient.getId()}'>
                        </div>

                        <div class="input-field" style="margin-top: 40px">
                            <select name="diagnosis">
                                <option value="0" disabled selected><fmt:message key="diagnosis"/></option>
                                <c:forEach begin="1" end="${sessionScope.diagnoses.size() - 1}" varStatus="loop">
                                    <option value= "${sessionScope.diagnoses.get(loop.index).getId()}">${sessionScope.diagnoses.get(loop.index).getType()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-field center" style="margin-bottom: 40px">
                            <button class="btn right cyan lighten-1" type="submit"><fmt:message key="submit"/></button>
                        </div>
                        </div>
                    </form>
                </div>
                </c:if>
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