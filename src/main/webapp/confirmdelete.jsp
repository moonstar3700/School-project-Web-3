<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Delete user</title>
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
<img class="item" src="images/registerfix.jpg" alt="foto dojo">
<main class="gridfield">
    <div class="grid">
    </div>
    <h2 class="maintitle">Delete user</h2>
    <div class="grid1">
    </div>
    <section>
        <p>Are you sure you want to delete user ${firstName} ${lastName} (userid ${userid}, group ${group})?</p>
        <form class="left" action="Controller?command=Overview" method="POST">
            <input id="cancel" type="submit" value="No"/>
        </form>
        <form class="right" action="Controller?command=Delete&userid=${userid}" method="POST">
            <input id="delete" type="submit" value="Yes"/>
        </form>

    </section>


</main>
<footer>
    <p>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </p>
</footer>
</body>

</html>