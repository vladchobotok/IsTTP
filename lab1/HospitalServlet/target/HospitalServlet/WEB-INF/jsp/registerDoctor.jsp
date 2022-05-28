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
    <title>Register doctors</title>
    <style>
        body {
            background: url("https://www.itij.com/sites/default/files/styles/og_image/public/2021-08/hospital-modern.jpg?h=f8702894&itok=r_2edjAc") no-repeat center fixed;
            background-size: cover;
        }
    </style>
</head>
<body>
<jsp:include page="elements/header.jsp" />
<div class="row">
    <div class="col m6 offset-m3 l4 offset-l4 s12">
        <div class="card-panel z-depth-5" style="min-height: 95vh; margin-top: 4vh; position: relative">
            <form method="post" action=${pageContext.request.contextPath}/admin/registerDoctor class="center-block" style="text-align: center; min-width: 80%">
                <h4><fmt:message key="registerDoctor"/></h4>
                <c:choose>
                    <c:when test="${requestScope.bad_getaway == 'emptyName'}">
                        <p class="red-text"><fmt:message key="emptyName" /></p>
                    </c:when>
                    <c:when test="${requestScope.bad_getaway == 'emptySurname'}">
                        <p class="red-text"><fmt:message key="emptySurname" /></p>
                    </c:when>
                    <c:when test="${requestScope.bad_getaway == 'emptyEmail'}">
                        <p class="red-text"><fmt:message key="emptyEmail" /></p>
                    </c:when>
                    <c:when test="${requestScope.bad_getaway == 'incorrectBirthday'}">
                        <p class="red-text"><fmt:message key="incorrectBirthday" /></p>
                    </c:when>
                    <c:when test="${requestScope.bad_getaway == 'emptyPassword'}">
                        <p class="red-text"><fmt:message key="emptyPassword" /></p>
                    </c:when>
                    <c:when test="${requestScope.bad_getaway == 'accountAlreadyExists'}">
                        <p class="red-text"><fmt:message key="accountAlreadyExists" /></p>
                    </c:when>
                    <c:when test="${requestScope.bad_getaway == 'wrongConfirmedPassword'}">
                        <p class="red-text"><fmt:message key="wrongConfirmedPassword" /></p>
                    </c:when>
                    <c:when test="${requestScope.bad_getaway == 'incorrectEmailFormat'}">
                        <p class="red-text"><fmt:message key="incorrectEmailFormat" /></p>
                    </c:when>
                    <c:when test="${requestScope.bad_getaway == 'incorrectPasswordFormat'}">
                        <p class="red-text"><fmt:message key="incorrectPasswordFormat" /></p>
                    </c:when>
                </c:choose>
                <div class="input-field" style="margin-top: 40px">
                    <input type="text" id="name" name="name" placeholder="Ivanov">
                    <label class="active" for="name"><fmt:message key="name"/></label>
                </div>
                <div class="input-field" >
                    <input type="text" id="surname" name="surname" placeholder="Ivan">
                    <label class="active" for="surname"><fmt:message key="surname"/></label>
                </div>
                <div class="input-field">
                    <input type="email" id="email" name="email" placeholder="ivanovivan@gmail.com">
                    <label class="active" for="email"><fmt:message key="email"/></label>
                </div>
                <div class="input-field">
                    <input type="date" id="birthday" name="birthday">
                    <label class="active" for="birthday"><fmt:message key="birthday"/></label>
                </div>
                <div class="input-field">
                    <input type="password" id="password" name="password">
                    <label class="active" for="password"><fmt:message key="pass"/></label>
                </div>
                <div class="input-field">
                    <input type="password" id="confirm-password" name="confirm-password">
                    <label class="active" for="confirm-password"><fmt:message key="repeatPass"/></label>
                </div>
                <div class="input-field col s12">
                    <select name="doctorsType">
                        <option value="0" disabled selected><fmt:message key="doctorsType"/></option>
                        <option value="1"><fmt:message key="nurses"/></option>
                        <option value="2"><fmt:message key="ENT"/></option>
                        <option value="3"><fmt:message key="surgeon"/></option>
                        <option value="4"><fmt:message key="traumatologist"/></option>
                    </select>
                </div>
                 <div class="input-field center">
                   <button class="btn right cyan lighten-1" type="submit"><fmt:message key="register"/></button>
                 </div>
            </form>
            <a style="position: absolute; bottom: 2%; left: 4%" href="${pageContext.request.contextPath}/admin"><fmt:message key="home"/> </a>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
<script>
    $(document).ready(function () {
        $('select').formSelect();
    });
</script>
</body>
</html>
