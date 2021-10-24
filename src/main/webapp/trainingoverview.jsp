<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style3.css">
</head>

<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="Matches"/>
    </jsp:include>
    <img class="item" src="images/overviewfix.jpg" alt="foto dojo">
    <main>
        <div class="overviewgrid">
            <div class="grid">
            </div>
            <h2 class="maintitle">
                Training Overview
            </h2>
            <div class="grid1">
            </div>
        </div>

        <c:if test="${not empty errors}">
            <div class="alert alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <c:if test="${not empty allTrainings}">
        <table>
            <thead>
            <tr>
                <th>TrainingID</th>
                <th>Date</th>
                <th>Start</th>
                <th>End</th>
                <th>Duration (in min)</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="training" items="${allTrainings}" varStatus="status">
                <tr>
                    <td>${training.trainingId}</td>
                    <td>${training.date}</td>
                    <td>${training.start}</td>
                    <td>${training.end}</td>
                    <td>${allTrainingdurations[status.index]}</td>
                    <td><a id="edit" href="Controller?command=ToEditTraining&trainingid=${training.trainingId}">Edit</a></td>
                    <td><a id="delete" href="Controller?command=ConfirmTrainingDelete&trainingid=${training.trainingId}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:if>

        <c:if test="${empty allTrainings}">
            <p>Er zijn momenteel geen Trainingen geregistreerd. U kan <a href="registermatch.jsp">hier Trainingen toevoegen</a>.</p>
        </c:if>
    </main>
    <footer>
        <p>
            &copy; Webontwikkeling 3, UC Leuven-Limburg
        </p>
    </footer>
</div>
</body>

</html>