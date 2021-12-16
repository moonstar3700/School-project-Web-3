<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1> Dojo Kamiyama </h1>
    <nav>
        <ul>
            <li><a ${param.actual eq 'Home'?"class = actual":""} href="Controller">Home</a></li>
            <c:if test="${sessionScope.user eq null}">
                <li><a ${param.actual eq 'Register'?"class = actual":""} href="register.jsp">Register</a></li>
            </c:if>
            <li><a ${param.actual eq 'Matches'?"class = actual":""} href="Controller?command=MatchOverview">Matches</a></li>
            <c:if test="${not empty sessionScope.user}">
                <li><a ${param.actual eq 'Add Match'?"class = actual":""} href="registermatch.jsp">Add Match</a></li>
                <li><a ${param.actual eq 'Users'?"class = actual":""} href="Controller?command=UserOverview">Users</a></li>
                <li><a ${param.actual eq 'Trainings'?"class = actual":""} href="Controller?command=TrainingOverview">Trainings</a></li>
                <li><a ${param.actual eq 'Add Trainings'?"class = actual":""}href="registertraining.jsp">Add training</a></li>
            </c:if>
        </ul>
    </nav>
</header>