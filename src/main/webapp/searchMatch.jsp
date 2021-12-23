<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="css/style3.css">
</head>

<body>
<div id="container">
    <%@ include file="header.jsp" %>
    <img class="item" src="images/registerfix.jpg" alt="foto dojo">
    <main class="gridfield">
        <div class="grid">
        </div>
        <h2 class="maintitle">
            Search match
        </h2>
        <div class="grid1">
        </div>
        <section>
            <c:if test="${not empty errors}">
                <div class="alert alert-danger">
                    <ul>
                        <c:forEach items="${errors}" var="error">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <p>Zoek een specifieke wedstrijd</p>

            <form method="POST" action="Controller?command=SearchMatch" novalidate="novalidate"><p><label for="home">Home
                Team</label><input type="text" id="home" name="home"
                                   value="${homePreviousValue}"></p>
                <p><label for="away">Away Team</label><input type="text" id="away" name="away"
                                                             value="${awayPreviousValue}"></p>
                <p><label for="group">Group</label>
                    <select id="group" name="group">
                        <c:choose>
                            <c:when test="${groupPreviousValue eq 'Recreation'}">
                                <option selected="selected">Recreation</option>
                            </c:when>
                            <c:otherwise>
                                <option>Recreation</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${groupPreviousValue eq 'Youth'}">
                                <option selected="selected">Youth</option>
                            </c:when>
                            <c:otherwise>
                                <option>Youth</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${groupPreviousValue eq 'Elite'}">
                                <option selected="selected">Elite</option>
                            </c:when>
                            <c:otherwise>
                                <option>Elite</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </p>
                <p><input type="submit" id="searchMatch" value="Search"></p></form>

            <p>Zoek wedstrijden van een bepaalde datum</p>

            <form method="POST" action="Controller?command=SearchMatchByDate" novalidate="novalidate">
                <p><label for="date">Date</label><input type="date" id="date" name="date" required
                                                        value="<c:out value='${datePreviousValue}'/>"></p>
                <p><input type="submit" id="searchMatch2" value="Search"></p></form>


        </section>

    </main>
    <footer>
        <p>
            &copy; Webontwikkeling 3, UC Leuven-Limburg
        </p>
    </footer>
</div>
</body>

</html>