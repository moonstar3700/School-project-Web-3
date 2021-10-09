<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <meta charset="UTF-8">
            <title>Sign Up</title>
            <link rel="stylesheet" type="text/css" href="css/style3.css">
        </head>

        <body>
            <div id="container">
                <header>
                    <h1><span>Dojo Kamiyama</span></h1>
                    <nav>
                        <ul>
                            <li><a href="Controller">Home</a></li>
                            <li><a href="Controller?command=Overview">Overview</a></li>
                            <li id="actual"><a href=register.jsp>Register</a></li>
                        </ul>
                    </nav>


                </header>
                <img class="item" src="images/register.jpg" alt="foto dojo">
                <main class="gridfield">
                    <div class="grid">
                    </div>
                    <h2 class="maintitle">
                        Register
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

                        <form method="POST" action="Controller?command=Register" novalidate="novalidate">
                            <!-- novalidate in order to be able to run tests correctly -->
                            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName" required value="${firstNamePreviousValue}"> </p>
                            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" required value="${lastNamePreviousValue}"> </p>
                            <p><label for="email">Email</label><input type="email" id="email" name="email" required value="${emailPreviousValue}"></p>
                            <p><label for="password">Password</label><input type="password" id="password" name="password" required value="${passwordPreviousValue}"> </p>
                            <p><label for="group">Group</label>
                                <!-- previous value bij select? -->
                                <select id="group" name="group">
                                    <c:choose>
                                        <c:when test="${groupPreviousValue eq 'Recreation'}">
                                            <option selected="selected">Recreation</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option>Recreation</option>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${groupPreviousValue eq 'Youth'}">
                                            <option selected="selected">Youth</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option>Youth</option>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${groupPreviousValue eq 'Elite'}">
                                            <option selected="selected">Elite</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option>Elite</option>
                                        </c:otherwise>
                                    </c:choose>
            </select>
                            </p>
                            <p><input type="submit" id="signUp" value="Sign Up"></p>

                        </form>
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