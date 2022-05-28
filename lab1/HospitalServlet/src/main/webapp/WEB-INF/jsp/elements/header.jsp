<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="locale"/>

<header >
    <nav class="cyan">
        <div class="container">
            <div class="nav-wrapper">
                <a href="#" class="brand-logo"><i class="material-icons">local_hospital</i><fmt:message key="hospital"/></a>
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
                    <li><a href="${pageContext.request.contextPath}/logout" class="btn cyan darken-2"><fmt:message key="logout"/></a></li>
                </ul>
            </div>
                <div class="input-field col s12">
            </div>
        </div>
    </nav>
    <ul class="sidenav" id="mobile-demo">
        <li><a href="${pageContext.request.contextPath}/logout" class="btn cyan lighten-1"><fmt:message key="logout"/></a></li>
    </ul>
</header>
