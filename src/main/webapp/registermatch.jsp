<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <meta charset="UTF-8">
            <title>Add Match</title>
            <link rel="stylesheet" type="text/css" href="css/style3.css">
        </head>

        <body>
            <div id="container">
                <jsp:include page="header.jsp">
                    <jsp:param name="actual" value="Add Match"/>
                </jsp:include>
                <img class="item" src="images/registerfix.jpg" alt="foto dojo">
                <main class="gridfield">
                    <div class="grid">
                    </div>
                    <h2 class="maintitle">
                        Register
                    </h2>
                    <div class="grid1">
                    </div>
                    <section>
                        <c:if test="${not empty user}">
                        <c:if test="${not empty errors}">
                            <div class="alert alert-danger">
                                <ul>
                                    <c:forEach items="${errors}" var="error">
                                        <li>${error}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>

                        <form method="POST" action="Controller?command=AddMatch" novalidate="novalidate">
                            <!-- novalidate in order to be able to run tests correctly -->
                            <p><label for="home">Home Team</label><input type="text" id="home" name="home" required value="${homePreviousValue}"> </p>
                            <p><label for="away">Away Team</label><input type="text" id="away" name="away" required value="${awayPreviousValue}"> </p>
                            <p><label for="date">Date</label><input type="date" id="date" name="date" required value="${datePreviousValue}"></p>
                            <p><label for="time">Time</label><input type="time" id="time" name="time" required value="${timePreviousValue}"> </p>
                            <p><input type="submit" id="addMatch" value="Add Match"></p>

                        </form>
                        </c:if>
                        <c:if test="${empty user}">
                        <p>U moet ingelogd zijn om een wedstrijd aan te maken.</p>
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