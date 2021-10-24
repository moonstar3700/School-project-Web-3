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
            Edit Training
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

            <p> ID: ${trainingid}</p>
            <form method="POST" action="Controller?command=EditTraining&trainingid=${trainingid}" novalidate="novalidate">
                <!-- novalidate in order to be able to run tests correctly -->
                <p><label for="date">Date</label><input type="date" id="date" name="date" required value="${date}"> </p>
                <p><label for="start">Start time</label><input type="time" id="start" name="start" required value="${start}"> </p>
                <p><label for="end">End time</label><input type="time" id="end" name="end" required value="${end}"></p>
                <p><input type="submit" id="editTraining" value="Edit Training"></p>

            </form>
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