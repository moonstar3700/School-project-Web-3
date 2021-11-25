<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Matches Found</title>
    <link rel="stylesheet" type="text/css" href="css/style3.css">
</head>

<body>
<%@ include file="header.jsp" %>
<img class="item" src="images/registerfix.jpg" alt="foto dojo">
<main class="gridfield">
    <div class="grid">
    </div>
    <h2 class="maintitle">Matches Found</h2>
    <div class="grid1">
    </div>
    <section>
    <p>Volgende wedstrijden worden gespeeld op ${date}:</p>
        <h3 class="matchtitle">Elite</h3>
        <c:forEach var="match" items="${foundMatches}">
            <c:if test="${match.group eq 'ELITE'}">
                <table>
                    <thead>
                    <tr>
                        <th>Group</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Home</th>
                        <th>Away</th>
                        <c:if test="${not empty match.winner}">
                            <th>Winner</th>
                            <th>Registered on</th>
                        </c:if>
                        <c:if test="${empty match.winner}">
                            <th>Winner</th>
                        </c:if>
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
                            <td>Nog result yet</td>
                        </c:if>
                        <td>${match.creator.firstName} ${match.creator.lastName}</td>
                    </tr>
                    </tbody>
                </table>

            </c:if>
        </c:forEach>

        <h3 class="matchtitle">Youth</h3>

        <c:forEach var="match" items="${foundMatches}">
            <c:if test="${match.group eq 'YOUTH'}">
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
                    </tr>
                    </tbody>
                </table>
            </c:if>
        </c:forEach>

        <h3 class="matchtitle">Recreation</h3>

        <c:forEach var="match" items="${foundMatches}">
            <c:if test="${match.group eq 'RECREATION'}">
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
                    </tr>

                    </tbody>
                </table>
            </c:if>
        </c:forEach>


    </section>


</main>
<footer>
    <p>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </p>
</footer>
</body>

</html>