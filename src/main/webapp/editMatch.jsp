<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Edit Match</title>
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
            Edit match: ${home} vs ${away}
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

            <c:if test="${empty user}">
                <p>U moet ingelogd om gebruikers aan te passen.</p>
                <c:redirect url="index.jsp"/>
            </c:if>

            <c:if test="${not empty user}">
            <c:if test="${empty matchid}">
                <p>Geen wedstrijd geselecteerd om aan te passen.</p>
            </c:if>


            <c:if test="${not empty matchid}">

            <fmt:parseDate value="${date}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
            <fmt:formatDate value="${parsedDate}" type="date" pattern="dd-MM-yyyy" var="stdDatum" />
            <jsp:useBean id="now" class="java.util.Date" />

            <c:if test="${parsedDate < now}">

                <form method="POST"
                      action="Controller?command=EditMatch&matchid=${matchid}&date=${date}&time=${time}"
                      novalidate="novalidate">
                    <!-- novalidate in order to be able to run tests correctly -->
                    <p><label for="home2">Home Team</label><input type="text" id="home2" name="home" required
                                                                 value="<c:out value='${home}'/>"></p>
                    <p><label for="away2">Away Team</label><input type="text" id="away2" name="away" required
                                                                 value="<c:out value='${away}'/>"></p>
                    <p>Date: ${date}</p>
                    <p>Time: ${time}</p>
                    <p>Winner:</p>
                    <c:choose><c:when test="${winner eq 'away'}"><input type="radio" id="winner1"
                              name="winner" value="home"></c:when><c:otherwise><input type="radio" id="winner1"
                                                                                      name="winner" value="home" checked="checked"></c:otherwise></c:choose>
                    <label for="winner1">Home</label>

                    <c:choose><c:when test="${winner eq 'away'}"><input type="radio" id="winner2"
                                                                   name="winner" value="away" checked="checked"></c:when><c:otherwise><input type="radio" id="winner2"
                                                                                                                           name="winner" value="away"></c:otherwise></c:choose>
                    <label for="winner2">Away</label>

                    <p><input type="submit" id="editmatch2" value="Edit"></p>

                </form>

            </c:if>


            <c:if test="${parsedDate > now}">

                <p> ID: ${matchid}</p>
                <form method="POST" action="Controller?command=EditMatch&matchid=${matchid}" novalidate="novalidate">
                    <p><label for="home1">Home Team</label><input type="text" id="home1" name="home" required
                                                                 value="<c:out value='${home}'/>"></p>
                    <p><label for="away1">Away Team</label><input type="text" id="away1" name="away" required
                                                                 value="<c:out value='${away}'/>"></p>
                    <p><label for="date">Date</label><input type="date" id="date" name="date" required
                                                           value="<c:out value='${date}'/>"></p>
                    <p><label for="time">Time</label><input type="time" id="time" name="time" required
                                                           value="<c:out value='${time}'/>" ></p>
                    <p><input type="submit" id="editmatch1" value="Edit"></p>

                </form>

            </c:if>
            </c:if>
            </c:if>


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