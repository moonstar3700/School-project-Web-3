<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
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
                User Overview
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

        <table>
            <thead>
            <tr>
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
            <c:forEach var="match" items="${allMatches}">
                <tr>
                    <td>${match.date}</td>
                    <td>${match.time}</td>
                    <td>${match.home}</td>
                    <td>${match.away}</td>
                    <td>${match.winner}</td>
                    <td>${match.winnerregistration}</td>
                    <td>${match.creator}</td>
                    <td><a id="edit" href="Controller?command=ToEdit&userid=${match.matchid}">Edit</a></td>
                    <td><a id="delete" href="Controller?command=ConfirmDelete&userid=${match.matchid}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </main>
    <footer>
        <p>
            &copy; Webontwikkeling 3, UC Leuven-Limburg
        </p>
    </footer>
</div>
</body>

</html>