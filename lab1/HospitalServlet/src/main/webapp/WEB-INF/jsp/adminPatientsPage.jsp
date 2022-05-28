<%@ page import="model.entity.Doctor" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.Patient" %>
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
    <title>Patients List</title>
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

        .formSelect{
            width: 25%;
            margin: 2vh 0.625% 2vh 2vh;
        }
    </style>
</head>
<body>
<jsp:include page="elements/header.jsp"/>

<main>
    <div class="container" style="background: white">
        <div class="col s12">
            <div class="input-field col s4">
                <a style="float: right; margin: 2vh" href="${pageContext.request.contextPath}/admin"><fmt:message key="home"/> </a>
                <form class="formSelect" action="${pageContext.request.contextPath}/admin/adminPatientsPage/sorting" method="post">
                    <select name="type">
                        <option value="7" disabled selected><fmt:message key="sortingType"/></option>
                        <option value="4"><fmt:message key="alphabet"/></option>
                        <option value="5"><fmt:message key="date"/></option>
                    </select>
                    <button type="submit" class="btn middle cyan lighten-1">
                        <strong><fmt:message key="sort"/></strong>
                    </button>
                </form>
            </div>
            <ul class="collection col s12">
                <c:forEach items="${requestScope.activePatients}" var="patient" varStatus="status">
                <li class="collection-item ">
                    <c:set var="patientRoleId" value="${patient.getUser().getRole().getId()}"/>
                    <c:set var="patientId" value="${patient.getId()}"/>
                    <span class="title"><fmt:message key="nameList"/> <c:out value="${patient.getUser().getName()}"/>
                        <c:if test="${patRoleId != 5}">
                        <a class="btn right cyan lighten-1 modal-trigger" href="#modal_${status.index}"><fmt:message key="assignDoctor"/></a>
                        <div id="modal_${status.index}" class="modal" style="min-height: 50%">
                            <form method="post" id="form_${status.index}" action=${pageContext.request.contextPath}/admin/assignDoctor
                                  class="center-block" style="text-align: center; min-width: 80%">
                                <div class="container">
                                    <div class="input-field">
                                        <input type="hidden" id="patientId" name="patientId" value="${patient.getId()}"/>
                                    </div>
                                    <div class="input-field" style="margin-top: 40px">
                                        <select name="doctorId">
                                        <option value="0" disabled selected><fmt:message key="doctors"/></option>
                                        <c:forEach begin="1" end="${sessionScope.allDoctors.size()}" varStatus="loop">
                                            <option value= "${sessionScope.allDoctors.get(loop.index - 1).getId()}">${sessionScope.allDoctors.get(loop.index - 1).getUser().getName()} ${sessionScope.allDoctors.get(loop.index - 1).getUser().getSurname()} (${sessionScope.allDoctors.get(loop.index - 1).getDoctorType().getType()})</option>
                                        </c:forEach>
                                        </select>
                                    </div>
                                    <div class="input-field center">
                                        <button class="btn right cyan lighten-1" type="submit"><fmt:message
                                                key="submit"/></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        </c:if>
                        </span>
                    <p><fmt:message key="surnameList"/> <c:out value="${patient.getUser().getSurname()}"/></p>
                    <p><fmt:message key="birthdayList"/> <c:out value="${patient.getUser().getBirthday()}"/></p>
                    <p><fmt:message key="patientIdList"/> <c:out value="${patient.getId()}"/></p>
                    <p><fmt:message key="statusList"/> <c:out value="${patient.getUser().getRole().getName()}"/></p>
                    <p><fmt:message key="currentDoctorList"/> <c:out value="${patient.getDoctor().getUser().getName()}"/> ${patient.getDoctor().getUser().getSurname()} (${patient.getDoctor().getDoctorType().getType()})</p>
                </li>
                </c:forEach>
            </ul>
            <ul class="pagination col s12" style="margin-left: 50%; margin-bottom: 2%">
                <c:forEach var="i" begin="1" end="${numberOfPatientPages}">
                    <c:choose>
                        <c:when test="${currentPatientPage == i}">
                            <li class="active cyan lighten-1"><a style="pointer-events: none; cursor: default;">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="waves-effect"><a href="${pageContext.request.contextPath}/admin/adminPatientsPage?currentPatientsPage=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
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