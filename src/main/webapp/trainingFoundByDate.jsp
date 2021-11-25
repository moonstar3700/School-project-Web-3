<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Trainings Found</title>
    <link rel="stylesheet" type="text/css" href="css/style3.css">
</head>

<body>
<%@ include file="header.jsp" %>
<img class="item" src="images/registerfix.jpg" alt="foto dojo">
<main class="gridfield">
    <div class="grid">
    </div>
    <h2 class="maintitle">Trainings Found</h2>
    <div class="grid1">
    </div>
    <section>
        <p>Volgende trainingen vonden plaats op ${date}:</p>
        <table>
            <thead>
            <tr>
                <th>TrainingID</th>
                <th>Date</th>
                <th>Start</th>
                <th>End</th>
                <th>Duration (in min)</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="training" items="${foundTrainings}" varStatus="status">
                <tr>
                    <td>${training.trainingId}</td>
                    <td class="datecheck">${training.date}</td>
                    <td>${training.start}</td>
                    <td>${training.end}</td>
                    <td>${training.calculateTime()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </section>


</main>
<footer>
    <p>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </p>
</footer>
</body>

</html>