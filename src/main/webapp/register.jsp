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
                <jsp:include page="header.jsp">
                    <jsp:param name="actual" value="Register"/>
                </jsp:include>
                <img class="item" src="images/registerfix.jpg" alt="foto dojo">
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

                        <p>Hier kan u zich registreren als trainer. Als u een andere rol wenst, kan dit later worden aangepast.</p>

                        <form method="POST" action="Controller?command=Register" novalidate="novalidate">
                            <!-- novalidate in order to be able to run tests correctly -->
                            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName" required value="${firstNamePreviousValue}"> </p>
                            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" required value="${lastNamePreviousValue}"> </p>
                            <p><label for="email">Email</label><input type="email" id="email" name="email" required value="${emailPreviousValue}"></p>
                            <p><label for="password">Password</label><input type="password" id="password" name="password" required value="${passwordPreviousValue}"> </p>
                            <meter id="password-meter" min="0" max="5" low="3" high="5" optimum="5" value="0"></meter>
                            <p id="password-text"></p>
                            <p id="letter" class="invalid2">Een <b>lowercase</b> letter</p>
                            <p id="capital" class="invalid2">Een <b>uppercase</b> letter</p>
                            <p id="number" class="invalid2">Een <b>nummer</b></p>
                            <p id="len" class="invalid2">Minimum <b>8 characters</b></p>
                            <p id="char" class="invalid2">Andere characters vb: (, :, ...</p>
                            <p id="register-group"><label for="group">Group</label>
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
                        <script src="JS/Register.js"></script>
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