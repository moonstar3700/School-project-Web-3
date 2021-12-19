<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Delete user</title>
    <link rel="stylesheet" type="text/css" href="css/style3.css">
</head>

<body>
<%@ include file="header.jsp" %>
<img class="item" src="images/registerfix.jpg" alt="foto dojo">
<main class="gridfield">
    <div class="grid">
    </div>
    <h2 class="maintitle">Delete user</h2>
    <div class="grid1">
    </div>
    <section>
        <c:if test="${empty user}">
            <p>U moet ingelogd om wedstrijden te verwijderen.</p>
            <c:redirect url="index.jsp"/>
        </c:if>


        <c:if test="${not empty user}">
            <c:if test="${empty matchid}">
                <p>Geen wedstrijd geselecteerd om te verwijderen.</p>
            </c:if>
        <c:if test="${not empty matchid}">
        <p>Are you sure you want to delete ${group} match ${home} versus ${away}?</p>
        <form class="left" action="Controller?command=MatchOverview" method="POST">
            <input id="cancel" type="submit" value="No"/>
        </form>
        <form class="right" action="Controller?command=DeleteMatch&matchid=${matchid}" method="POST">
            <input id="delete" type="submit" value="Yes"/>
        </form>
        </c:if>
        </c:if>

    </section>


</main>
<footer>
    <p>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </p>
</footer>
</body>

</html>