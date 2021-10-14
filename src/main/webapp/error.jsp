<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="css/style3.css">
</head>

<body>
<header>
    <h1>
        <span>Dojo Kamiyama</span>
    </h1>
    <nav>
        <ul>

            <li><a id="click" href="Controller">Home</a></li>
            <li><a href="Controller?command=Overview">Overview</a></li>
            <li><a href="register.jsp">Register</a></li>
        </ul>
    </nav>
</header>
<img class="item" src="images/registerfix.jpg" alt="foto dojo">
<main class="gridfield">
    <div class="grid">
    </div>
    <h2 class="maintitle">Error</h2>
    <div class="grid1">
    </div>
    <section>
    <p>Oeps, er liep iets fout.</p>
        <p>Fout: ${pageContext.exception}</p>

    </section>


</main>
<footer>
    <p>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </p>
</footer>
</body>

</html>