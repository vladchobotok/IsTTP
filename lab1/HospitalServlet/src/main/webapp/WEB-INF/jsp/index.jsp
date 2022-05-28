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
    <title>Hospital Project</title>
    <style>
        body{
            background: url("https://www.itij.com/sites/default/files/styles/og_image/public/2021-08/hospital-modern.jpg?h=f8702894&itok=r_2edjAc") no-repeat center fixed;
            background-size: cover;
        }
    </style>
</head>

<body>
<header>
    <nav class="cyan">
        <div class="container">
            <div class="nav-wrapper">
                <a href="${pageContext.request.contextPath}/login" class="brand-logo"><i class="material-icons">local_hospital</i><fmt:message key="hospital" /></a>
                <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
                <ul class="right hide-on-med-and-down">
                    <li>
                        <div>
                            <form>
                                <select id="language" name="language" onchange="submit()">
                                    <option  value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                                    <option  value="ua" ${language == 'ua' ? 'selected' : ''}>Українська</option>
                                </select>
                            </form>
                        </div>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/register" class="btn cyan darken-2"><fmt:message key="register" /></a></li>
                    <li><a href="${pageContext.request.contextPath}/login" class="btn cyan darken-2"><fmt:message key="login" /></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <ul class="sidenav" id="mobile-demo">
        <li><a href="${pageContext.request.contextPath}/"><fmt:message key="home" /></a></li>
    </ul>
</header>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
<script>
    $(document).ready(function () {
        $('.sidenav').sidenav();
        $('.materialboxed').materialbox();
        $('select').formSelect();
    });
</script>
</body>
</html>