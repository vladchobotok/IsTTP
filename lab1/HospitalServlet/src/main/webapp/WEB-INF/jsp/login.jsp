<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="${language}" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">
    <meta charset="utf-8">
    <title>Hospital | Sign in</title>
</head>

<body class="cyan">
<div class="row">
    <div class="col m6 offset-m3 l4 offset-l4 s12">
        <div class="card-panel z-depth-5" style="min-height: 90vh; margin-top: 4vh; position: relative">
            <div class="valign-wrapper" style="margin-top: 20%">
                <form method="post" action=${pageContext.request.contextPath}/login class="center-block" style="text-align: center; min-width: 80%">
                    <h1><fmt:message key="login"/> </h1>
                    <h4><fmt:message key="welcome"/></h4>
                    <c:choose>
                        <c:when test="${requestScope.bad_getaway == 'emptyEmail'}">
                            <p class="red-text"><fmt:message key="emptyEmail" /></p>
                        </c:when>
                        <c:when test="${requestScope.bad_getaway == 'emptyPassword'}">
                            <p class="red-text"><fmt:message key="emptyPassword" /></p>
                        </c:when>
                        <c:when test="${requestScope.bad_getaway == 'incorrectInput'}">
                            <p class="red-text"><fmt:message key="incorrectInput" /></p>
                        </c:when>
                    </c:choose>
                    <div class="input-field" style="margin-top: 40px">
                        <i class="material-icons prefix">email</i>
                        <input type="text" id="email" name="email">
                        <label for="email"><fmt:message key="email"/></label>
                    </div>
                    <div class="input-field">
                        <i class="material-icons prefix">lock</i>
                        <input type="password" id="password" name="password">
                        <label for="password"><fmt:message key="pass"/></label>
                    </div>
                    <p><fmt:message key="noAccount"/> <a href="${pageContext.request.contextPath}/register"> <fmt:message key="register"/></a></p>
                    <div class="input-field center">
                        <button class="btn-large indigo darken-4" type="submit"><fmt:message key="login"/></button>
                    </div>
                </form>
                <a style="position: absolute; bottom: 2%" href="${pageContext.request.contextPath}/"><fmt:message key="home"/></a>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>
    $(document).ready(function(){
    });
</script>
</body>
</html>


