<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Edit</title>
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
            Edit ${param.group} match: ${param.home} vs ${param.away}
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

            <c:if test="${date < now}">

                <form method="POST"
                      action="Controller?command=EditMatch&matchid=${matchid}&date=${date}&time=${time}"
                      novalidate="novalidate">
                    <!-- novalidate in order to be able to run tests correctly -->
                    <p><label for="home2">Home Team</label><input type="text" id="home2" name="home" required
                                                                  value="${home}"></p>
                    <p><label for="away2">Away Team</label><input type="text" id="away2" name="away" required
                                                                  value="${away}"></p>
                    <p>Date: ${date}</p>
                    <p>Time: ${time}</p>
                    <p>Winner:</p>
                    <input type="radio" id="winner1"
                           name="winner" value="away">
                    <label for="winner1">Home</label>

                    <input type="radio" id="winner2"
                           name="winner" value="away">
                    <label for="winner2">Away</label>

                    <p><input type="submit" id="editmatch2" value="Edit"></p>

                </form>

            </c:if>


            <c:if test="${date > now}">

                <p> ID: ${matchid}</p>
                <form method="POST" action="Controller?command=EditMatch&matchid=${matchid}" novalidate="novalidate">
                    <p><label for="home1">Home Team</label><input type="text" id="home1" name="home" required
                                                                  value="${home}"></p>
                    <p><label for="away1">Away Team</label><input type="text" id="away1" name="away" required
                                                                  value="${away}"></p>
                    <p><label for="date">Date</label><input type="date" id="date" name="date" required
                                                            value="${date}"></p>
                    <p><label for="time">Time</label><input type="time" id="time" name="time" required
                                                            value="${time}"></p>
                    <p><input type="submit" id="editmatch1" value="Edit"></p>

                </form>

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