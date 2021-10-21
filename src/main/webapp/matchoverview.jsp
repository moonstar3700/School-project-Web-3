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
                Match Overview
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

        <c:if test="${not empty allMatches}">
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
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="match" items="${allMatches}">
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
                    <td><a id="edit" href="Controller?command=ToEdit&userid=${match.matchid}">Edit</a></td>
                    <td><a id="delete" href="Controller?command=ConfirmDelete&userid=${match.matchid}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:if>

        <c:if test="${empty allMatches}">
            <p>Er zijn momenteel geen wedstrijden geregistreerd. U kan <a href="registermatch.jsp">hier wedstrijden toevoegen</a>.</p>
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