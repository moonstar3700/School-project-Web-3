<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>XXX</span></h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li id="actual"><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="register.jsp">Register</a></li>
            </ul>
        </nav>
        <h2>
            User Overview
        </h2>

    </header>
    <main>
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
                    <th>${user.userid}</th>
                    <th>${user.firstName}</th>
                    <th>${user.lastName}</th>
                    <th>${user.email}</th>
                    <th>${user.group}</th>
                    <th>${user.role}</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </c:forEach>
            <caption>Users Overview</caption>
            </tbody>
        </table>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>