<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Search</title>
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
            Search match
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

            <p>Zoek trainingen van een bepaalde datum</p>

            <form method="POST" action="Controller?command=SearchTrainingByDate" novalidate="novalidate">
                <p><label for="date">Date</label><input type="date" id="date" name="date" required
                                                        value="<c:out value='${datePreviousValue}'/>"></p>
                <p><input type="submit" id="searchTraining" value="Search"></p></form>


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