<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <meta charset="UTF-8">
            <title>Add training</title>
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
                        Register Training
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

                            <p>Hier kan u nieuwe Trainingen toevoegen.</p>

                        <form method="POST" action="Controller?command=AddTraining" novalidate="novalidate">
                            <!-- novalidate in order to be able to run tests correctly -->
                            <p><label for="date">Date</label><input type="date" id="date" name="date" required value="<c:out value='${datePreviousValue}'/>"></p>
                            <p><label for="start">Start time</label><input type="time" id="start" name="start" required value="<c:out value='${startPreviousValue}'/>"></p>
                            <p><label for="end">End time</label><input type="time" id="end" name="end" required value="<c:out value='${endPreviousValue}'/>"></p>
                            <p><input type="submit" id="addTraining" value="Add Training"></p>

                        </form>
                        </c:if>
                        <c:if test="${empty user}">
                        <p>U moet ingelogd zijn om een Training aan te maken.</p>
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