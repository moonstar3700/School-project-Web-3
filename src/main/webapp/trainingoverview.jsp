<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Training overview</title>
    <link rel="stylesheet" type="text/css" href="css/style3.css">
    <script src="JS/TrainingFilter.js"></script>
</head>

<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="Trainings"/>
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

        <c:if test="${not empty notAuthorized}">
            <section>
                <p>
                    ${notAuthorized}
                        <c:redirect url="index.jsp"/>
                </p>
            </section>

        </c:if>
        <c:if test="${empty user}">
            <p>U moet ingelogd zijn om trainingen te bekijken.</p>
            <c:redirect url="index.jsp"/>
        </c:if>
        <c:if test="${empty notAuthorized}">

            <p id="zoekp"><a href="searchTraining.jsp" id="zoek">Zoek een training</a></p>

        <c:if test="${not empty user}">

            <c:if test="${param.confirmation eq 'succes'}">
                <section>
                    <p>Training werd succesvol toegevoegd.</p>
                </section>

            </c:if>

            <c:if test="${param.confirmation eq 'succesEdit'}">
                <section>
                    <p>Training werd succesvol aangepast.</p>
                </section>

            </c:if>

            <c:if test="${param.confirmation eq 'succesDelete'}">
                <section>
                    <p>Training werd succesvol verwijderd.</p>
                </section>
            </c:if>

            <section>
                <h1 id="toh">sorteer Opties</h1>
                <p>Om Alle tabellen te sorteren, gelieve te kiezen waar u op wilt sorteren en op sort te klikken</p>
                <p>Om een specifieke tabel te filteren (met javascript), gelieve te klikken op de headings TrainingID, Date, Start, End, of op durations
                van de gewenste tabel. (Deze zullen ondelijnd worden indien erover gehoverd word)</p>
            </section>

        <c:if test="${not empty errors}">
            <div class="alert alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <c:if test="${emptytable == false}">
            <form method="POST" action="Controller?command=TrainingOverview" novalidate="novalidate">
                <p><label for="filter">Sort by</label><select name="filter" id="filter">
                    <option value="training_id">id</option>
                    <option value="training_date">date</option>
                    <option value="training_start">start time</option>
                    <option value="training_end">end time</option>
                </select></p>
                <p><input type="submit" id="filterButton" value="sort"></p>
            </form>
                    <c:forEach var="trainingen" items="${allTrainings}">
                        <h2 class="TOtitle">${trainingen.key.lastName}</h2>
                        <table id="${trainingen.key.userid}"}>
                            <thead>
                            <tr>
                                <th class="TOtableHeading" onclick="sortTableNumber(${trainingen.key.userid}, 0)">TrainingID</th>
                                <th class="TOtableHeading" onclick="sortTableDate(${trainingen.key.userid}, 1)">Date</th>
                                <th class="TOtableHeading" onclick="sortTableTime(${trainingen.key.userid}, 2)">Start</th>
                                <th class="TOtableHeading" onclick="sortTableTime(${trainingen.key.userid}, 3)">End</th>
                                <th class="TOtableHeading" onclick="sortTableNumber(${trainingen.key.userid}, 4)">Duration (in min)</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="training" items="${trainingen.value}" varStatus="status">
                                <tr>
                                    <td>${training.trainingId}</td>
                                    <td class="datecheck">${training.date}</td>
                                    <td>${training.start}</td>
                                    <td>${training.end}</td>
                                    <td>${training.calculateTime()}</td>
                                    <c:if test="${sessionScope.user.userid eq training.userID || sessionScope.user.role eq 'ADMIN'}">
                                        <td><a id="edit" href="Controller?command=ToEditTraining&trainingid=${training.trainingId}">Edit</a></td>
                                        <td><a id="delete" href="Controller?command=DeleteTraining&trainingid=${training.trainingId}">Delete</a></td>
                                    </c:if>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:forEach>
        </c:if>
        </c:if>

        <c:if test="${emptytable eq true}">
            <section>
                <p>Er zijn momenteel geen Trainingen geregistreerd. U kan <a href="registertraining.jsp">hier Trainingen toevoegen</a>.</p>
            </section>

        </c:if>
        </c:if>
        <%--<c:if test="${empty user}">
            <section class="notloggedpanel" ><p>U moet ingelogd zijn om Trainingen te kunnen zien.</p></section>
        </c:if>--%>
    </main>
    <footer>
        <p>
            &copy; Webontwikkeling 3, UC Leuven-Limburg
        </p>
    </footer>
</div>
</body>

</html>