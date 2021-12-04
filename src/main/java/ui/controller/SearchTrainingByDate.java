package ui.controller;

import domain.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchTrainingByDate extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        HashMap<User, List<Training>> trainingen = new HashMap<>();
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        HttpSession session = request.getSession();
        User log = (User) session.getAttribute("user");


        try {
            if (log == null) {
                request.setAttribute("notAuthorized", "You are not authorized to look at this page.");
            }
            if (log.getRole() == Role.TRAINER) {
                List<Training> trainingenUser = service.searchTrainingsByDate(date, log);
                trainingen.put(log, trainingenUser);
                request.setAttribute("allTrainings", trainingen);
            } else if (log.getRole() == Role.COORDINATOR) {
                List<User> users = service.getAllGroupWithTrainingOnDate(date, log);
                for (User u : users) {
                    List<Training> trainingenUser = service.searchTrainingsByDate(date, u);
                    trainingen.put(u, trainingenUser);
                }
                request.setAttribute("allTrainings", trainingen);
            } else if (log.getRole() == Role.ADMIN) {
                List<User> users = service.getAllWithTrainingOnDate(date);
                for (User u : users) {
                    List<Training> trainingenUser = service.searchTrainingsByDate(date, u);
                    trainingen.put(u, trainingenUser);
                }
                List<Training> trainingenNoUser = service.getAllZonderUserOnDate(date);
                if (trainingenNoUser.size() != 0){
                    User mock = new User();
                    mock.setLastName("Trainingen zonder user");
                    trainingen.put(mock, trainingenNoUser);
                }
                request.setAttribute("allTrainings", trainingen);
            }
        } catch(DomainException exc) {
            errors.add(exc.getMessage());
            request.setAttribute("errors", errors);
            return "searchTraining.jsp";
        }

        if (errors.size() == 0) {
            try {
                if (trainingen.size() == 0) {
                    return "notFound.jsp";
                } else{
                    request.setAttribute("allTrainings", trainingen);
                    request.setAttribute("date", date);
                    return "trainingFoundByDate.jsp";
                }
            }
            catch (DomainException exc ) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                return "searchTraining.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            return "searchTraining.jsp";
        }
    }


}


