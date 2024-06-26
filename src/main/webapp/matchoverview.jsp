<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Match Overview</title>
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

        <c:if test="${param.confirmation eq 'succes'}">
            <section>
                <p>Match werd succesvol toegevoegd.</p>
            </section>

        </c:if>

        <c:if test="${param.confirmation eq 'succesEdit'}">
            <section>
                <p>Match werd succesvol aangepast.</p>
            </section>

        </c:if>

        <c:if test="${param.confirmation eq 'succesDelete'}">
            <section>
                <p>Match werd succesvol verwijderd.</p>
            </section>

        </c:if>

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
            
            <p id="zoekp"><a href="searchMatch.jsp" id="zoek">Zoek een wedstrijd</a></p>

            <h3 class="matchtitle">Elite</h3>
            <c:forEach var="match" items="${allMatches}">
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
                            <c:if test="${empty notAuthorized}">
                                <th>Edit</th>
                                <th>Delete</th>
                            </c:if>

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
                            <c:if test="${user.userid eq match.creator.userid || (user.group eq 'ELITE' && user.role eq 'COORDINATOR') || user.role eq 'ADMIN'}">
                            <td><a id="edit1" href="Controller?command=ToEditMatch&matchid=${match.matchid}">Edit</a>
                            </td>
                            <td><a id="delete1" href="Controller?command=ConfirmMatchDelete&matchid=${match.matchid}">Delete</a></td>
                            </c:if>
                        </tr>
                        </tbody>
                    </table>

                </c:if>
            </c:forEach>

            <h3 class="matchtitle">Youth</h3>

            <c:forEach var="match" items="${allMatches}">
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
                            <c:if test="${empty notAuthorized}">
                                <th>Edit</th>
                                <th>Delete</th>
                            </c:if>

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
                            <c:if test="${user.userid eq match.creator.userid || (user.group eq 'YOUTH' && user.role eq 'COORDINATOR') || user.role eq 'ADMIN'}">
                            <td><a id="edit2" href="Controller?command=ToEditMatch&matchid=${match.matchid}">Edit</a>
                            <td><a id="delete2" href="Controller?command=ConfirmMatchDelete&matchid=${match.matchid}">Delete</a></td>
                            </c:if>
                        </tr>
                        </tbody>
                    </table>
                </c:if>
            </c:forEach>

            <h3 class="matchtitle">Recreation</h3>

            <c:forEach var="match" items="${allMatches}">
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
                            <c:if test="${empty notAuthorized}">
                            <th>Edit</th>
                            <th>Delete</th>
                            </c:if>
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
                            <c:if test="${user.userid eq match.creator.userid || (user.group eq 'RECREATION' && user.role eq 'COORDINATOR') || user.role eq 'ADMIN'}">
                            <td><a id="edit3" href="Controller?command=ToEditMatch&matchid=${match.matchid}">Edit</a>
                            <td><a id="delete3" href="Controller?command=ConfirmMatchDelete&matchid=${match.matchid}">Delete</a></td>
                            </c:if>
                        </tr>

                        </tbody>
                    </table>
                </c:if>
            </c:forEach>


        </c:if>

        <c:if test="${empty allMatches}">
            <section><p>Er zijn momenteel geen wedstrijden geregistreerd. <c:if test="${not empty user}"> U kan <a
                    href="registermatch.jsp">hier wedstrijden
                toevoegen</a> .</c:if></p></section>
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