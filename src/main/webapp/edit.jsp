<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Edit</title>
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
            Edit ${param.firstName} ${param.lastName}
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

            <p> ID: ${userid}</p>

            <form method="POST" action="Controller?command=Edit&userid=${userid}" novalidate="novalidate">
                <!-- novalidate in order to be able to run tests correctly -->
                <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName" required
                                                                   value="<c:out value='${firstName}'/>"></p>
                <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" required
                                                                 value="<c:out value='${lastName}'/>"></p>
                <p><label for="email">Email</label><input type="email" id="email" name="email" required
                                                         value="<c:out value='${email}'/>" value='${email}'></p>


                    <p><label for="role">Role</label>
                        <select id="role" name="role" <c:if test="${user.userid == userid || (user.role eq 'COORDINATOR' && role eq 'admin')}">disabled</c:if>>
                            <option value="admin" <c:if test="${role eq 'admin'}">selected</c:if><c:if test="${sessionScope.user.role != 'ADMIN'}">disabled</c:if>>Admin</option>
                            <option value="coordinator" <c:if test="${role eq 'coordinator'}">selected</c:if>>Coordinator</option>
                            <option value="trainer" <c:if test="${role eq 'trainer'}">selected</c:if>>Trainer</option>
                            <c:if test="${user.userid == userid || (user.role eq 'COORDINATOR' && role eq 'admin')}">
                                <input type="hidden" name="role" value="${role}"/>
                            </c:if>
                        </select></p>




                <p><label for="group">Group</label>
                    <select id="group" name="group">
                        <c:choose>
                            <c:when test="${group eq 'recreation'}">
                                <option selected="selected">Recreation</option>
                            </c:when>
                            <c:otherwise>
                                <option>Recreation</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${group eq 'youth'}">
                                <option selected="selected">Youth</option>
                            </c:when>
                            <c:otherwise>
                                <option>Youth</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${group eq 'elite'}">
                                <option selected="selected">Elite</option>
                            </c:when>
                            <c:otherwise>
                                <option>Elite</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </p>
                <p><input type="submit" id="edit" value="Edit"></p>

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