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
                    <jsp:param name="actual" value="Users"/>
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

                    <c:if test="${not empty notAuthorized}">
                        <section>
                            <p>${notAuthorized}</p>
                        </section>
                    </c:if>
                    <c:if test="${empty notAuthorized}">

                    <c:if test="${param.confirmation eq 'succes'}">
                        <p>User werd succesvol toegevoegd.</p>
                    </c:if>

                    <c:if test="${param.confirmation eq 'succesEdit'}">
                        <p>User werd succesvol aangepast.</p>
                    </c:if>

                    <c:if test="${param.confirmation eq 'succesDelete'}">
                        <p>User werd succesvol verwijderd.</p>
                    </c:if>

                    <c:if test="${not empty errors}">
                        <p id="zoekp"><a href="searchTraining.jsp" id="zoek">Zoek een training</a></p>
                        <div class="alert alert-danger">
                            <ul>
                                <c:forEach items="${errors}" var="error">
                                    <li>${error}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>

                    <c:if test="${not empty allUsers}">

                    <table>
                        <thead>
                            <tr>
                                <th>Userid</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>E-mail</th>
                                <th>Group</th>
                                <th>Role</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${allUsers}">
                                <tr>
                                    <td>${user.userid}</td>
                                    <td>${user.firstName}</td>
                                    <td>${user.lastName}</td>
                                    <td>${user.email}</td>
                                    <td>${user.group}</td>
                                    <td>${user.role}</td>
                                    <td><a id="edit" href="Controller?command=ToEdit&userid=${user.userid}">Edit</a></td>
                                    <td><a id="delete" href="Controller?command=ConfirmDelete&userid=${user.userid}">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </c:if>

                    <c:if test="${empty allUsers}">
                    <p>Er zijn momenteel geen gebruikers geregistreerd. U kan zich <a href="register.jsp">hier registeren</a>.</p>
                    </c:if>
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