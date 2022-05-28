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
    <title>Doctors List</title>
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
                <form class="formSelect" action="${pageContext.request.contextPath}/admin/adminDoctorsPage/sorting" method="post">
                    <select name="type">
                        <option value="6" disabled selected><fmt:message key="sortingType"/></option>
                        <option value="1"><fmt:message key="alphabet"/></option>
                        <option value="2"><fmt:message key="category"/></option>
                        <option value="3"><fmt:message key="countPatients"/></option>
                    </select>
                    <button type="submit" class="btn middle cyan lighten-1">
                        <strong><fmt:message key="sort"/></strong>
                    </button>
                </form>
            </div>

            <ul class="collection col s12">
                <c:forEach items="${requestScope.activeDoctors}" var="doctor" varStatus="status">
                <li class="collection-item ">
                    <span class="title"><fmt:message key="nameList"/> ${doctor.getUser().getName()}</span>
                    <p><fmt:message key="surnameList"/> ${doctor.getUser().getSurname()}</p>
                    <p><fmt:message key="doctorTypeList"/> ${doctor.getDoctorType().getType()}</p>
                    <p><fmt:message key="doctorIdList"/> ${doctor.getId()}</p>
                </li>
                </c:forEach>
            </ul>
            <ul class="pagination col s12" style="margin-left: 50%; margin-bottom: 2%">
                <c:forEach var="i" begin="1" end="${numberOfDoctorPages}">
                    <c:choose>
                        <c:when test="${currentDoctorsPage == i}">
                            <li class="active cyan lighten-1"><a style="pointer-events: none; cursor: default;">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="waves-effect"><a href="${pageContext.request.contextPath}/admin/adminDoctorsPage?currentDoctorsPage=${i}">${i}</a></li>
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