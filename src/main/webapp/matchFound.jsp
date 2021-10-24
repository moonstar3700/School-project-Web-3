<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Match Found</title>
    <link rel="stylesheet" type="text/css" href="css/style3.css">
</head>

<body>
<%@ include file="header.jsp" %>
<img class="item" src="images/registerfix.jpg" alt="foto dojo">
<main class="gridfield">
    <div class="grid">
    </div>
    <h2 class="maintitle">Match Found</h2>
    <div class="grid1">
    </div>
    <section>
    <p>U zocht volgende wedstrijd:</p>
        <table>
            <thead>
            <tr>
                <th>Group</th>
                <th>Date</th>
                <th>Time</th>
                <th>Home</th>
                <th>Away</th>
                <th>Winner</th>
                <th>Registered on</th>
                <th>Creator</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${match.group}</td>
                <td>${match.date}</td>
                <td>${match.time}</td>
                <td>${match.home}</td>
                <td>${match.away}</td>
                <c:if test="${not empty match.winner}">
                    <td>${match.winner}</td>
                    <td>${match.winnerregistration}</td>
                </c:if>
                <c:if test="${empty match.winner}">
                    <td>Deze match heeft nog geen geregistreerd resultaat</td>
                    <td>Deze match heeft nog geen geregistreerd resultaat</td>
                </c:if>
                <td>${match.creator.firstName} ${match.creator.lastName}</td>
                </td>
            </tr>
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