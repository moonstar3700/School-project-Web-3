<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style3.css">
</head>

<body>
<header>
    <h1>
        <span>Dojo Kamiyama</span>
    </h1>
    <nav>
        <ul>

            <li id="actual"><a href="Controller">Home</a></li>
            <li><a href="Controller?command=Overview">Overview</a></li>
            <li><a href="register.jsp">Register</a></li>
        </ul>
    </nav>
</header>
<img class="item" src="images/indexfix.jpg" alt="foto dojo">
<main class="gridfield">
    <div class="grid">
    </div>
    <h2 class="maintitle">Home</h2>
    <div class="grid1">
    </div>
        <section>

            <c:if test="${not empty user}">
            <div>
                <p class="userwelcome">Welcome ${user}!</p>
            </div>
            </c:if>

            <c:if  test="${not empty user}">
                <form method="POST" action="Controller?command=Logout" novalidate>
                    <p><input type="submit" id="Logout" value="Log out"></p>
                </form>
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

            <c:if test="${empty user}">
                <form method="POST" action="Controller?command=Login" novalidate>
                    <p><label for="email">Email</label><input type="email" id="email" name="email" required></p>
                    <p><label for="password">Password</label><input type="password" id="password" name="password" required>
                    <p><input type="submit" id="login" value="Login"></p>
                </form>
            </c:if>

            <div>
                <p class="introtext">Welcome bij Dojo Kamiyama. Hier kunt u trainingen plannen.</p>
            </div>

        </section>


</main>
<footer>
    <p>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </p>
</footer>
</body>

</html>