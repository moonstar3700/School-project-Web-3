<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1> Dojo Kamiyama </h1>
    <nav>
        <ul>
            <li><a ${param.current eq 'Home'?"id = actual":""} href="Controller">Home</a></li>
            <li ><a ${param.current eq 'Users'?"id = actual":""} href="Controller?command=UserOverview">Users</a></li>
            <li ><a ${param.current eq 'Add Match'?"id = actual":""} href="registermatch.jsp">Add Match</a></li>
            <li ><a ${param.current eq 'Matches'?"id = actual":""} href="Controller?command=MatchOverview">Matches</a></li>
            <li><a ${param.current eq 'Register'?"id = actual":""} href="register.jsp">Register</a></li>
            <li><a ${param.current eq 'Add Trainings'?"id = actual":""}href="registertraining.jsp">Add training</a></li>
            <li><a ${param.current eq 'Trainings'?"id = actual":""} href="Controller?command=TrainingOverview">Trainings</a></li>
        </ul>
    </nav>
</header>