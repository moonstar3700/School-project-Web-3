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
                <header>
                    <h1>Dojo Kamiyama</h1>
                    <nav>
                        <ul>
                            <li><a href="Controller">Home</a></li>
                            <li id="actual"><a href="Controller?command=Overview">Overview</a></li>
                            <li><a href="register.jsp">Register</a></li>
                        </ul>
                    </nav>


                </header>
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
                </main>
                <footer>
                    <p>
                        &copy; Webontwikkeling 3, UC Leuven-Limburg
                    </p>
                </footer>
            </div>
        </body>

        </html>