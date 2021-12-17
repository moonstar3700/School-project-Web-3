package ui.controller;

import domain.model.Role;
import domain.model.Training;
import domain.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainingOverview extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response)  {
        HashMap<User, List<Training>> trainingen = new HashMap<>();
        String filter = request.getParameter("filter");
        HttpSession session = request.getSession();
        User log = (User) session.getAttribute("user");
        boolean emptyData = false;
        if (log == null) {
            request.setAttribute("notAuthorized", "You are not authorized to look at this page.");
        }
        if (log != null) {
            if (filter != null){
                if (log.getRole() == Role.TRAINER){
                    List<Training> trainingenUser = service.getAllTrainingsFilter(filter, log);
                    if (trainingenUser.size() == 0) {emptyData = true;}
                    trainingen.put(log, trainingenUser);
                    request.setAttribute("allTrainings", trainingen);
                }
                else if (log.getRole() == Role.COORDINATOR){
                    List<User> users = service.getAllGroupWithTraining(log);
                    if (users.size() == 0) {emptyData = true;}
                    for(User u: users){
                        List<Training> trainingenUser = service.getAllTrainingsFilter(filter, u);
                        trainingen.put(u, trainingenUser);
                    }
                    request.setAttribute("allTrainings", trainingen);
                }
                else if (log.getRole() == Role.ADMIN) {
                    List<User> users = service.getAllWithTraining();
                    if (users.size() == 0) {emptyData = true;}
                    for(User u: users){
                        List<Training> trainingenUser = service.getAllTrainings(u);
                        trainingen.put(u, trainingenUser);
                    }
                    List<Training> trainingenZonderUser = service.getAllTrainingsZonderUserFiltered(filter);
                    if (trainingenZonderUser.size() != 0) {
                        User mock = new User();
                        mock.setLastName("Trainingen zonder user");
                        trainingen.put(mock, trainingenZonderUser);
                    }

                    request.setAttribute("allTrainings", trainingen);
                }
            } else {
                if (log.getRole() == Role.TRAINER){
                    List<Training> trainingenUser = service.getAllTrainings(log);
                    if (trainingenUser.size() == 0) {emptyData = true;}
                    trainingen.put(log, trainingenUser);
                    request.setAttribute("allTrainings", trainingen);
                }
                else if (log.getRole() == Role.COORDINATOR){
                    List<User> users = service.getAllGroupWithTraining(log);
                    if (users.size() == 0) {emptyData = true;}
                    for(User u: users){
                        List<Training> trainingenUser = service.getAllTrainings(u);
                        trainingen.put(u, trainingenUser);
                    }
                    request.setAttribute("allTrainings", trainingen);
                }else if (log.getRole() == Role.ADMIN){
                    List<User> users = service.getAllWithTraining();
                    if (users.size() == 0) {emptyData = true;}
                    for(User u: users){
                        List<Training> trainingenUser = service.getAllTrainings(u);
                        trainingen.put(u, trainingenUser);
                    }
                    List<Training> trainingenZonderUser = service.getAllTrainingsZonderUser();
                    if (trainingenZonderUser.size() != 0){
                        User mock = new User();
                        mock.setLastName("Trainingen zonder user");
                        trainingen.put(mock, trainingenZonderUser);
                    }

                    request.setAttribute("allTrainings", trainingen);
                }
            }
        }
        request.setAttribute("emptytable", emptyData);
        return "trainingoverview.jsp";
    }





    /*public static void checkRole(HttpServletRequest request, Role[] roles) {
        // read user from session
        // if users role is different from given roles
        //      throw NotAuthorizedException
        boolean found = false;
        User user = (User) request.getSession().getAttribute("user");
        if (user != null)
            for (Role role : roles) {
                if (user.getRole().equals(role)) {
                    found = true;
                    break;
                }
            }
        if (!found)
            request.setAttribute("notAuthorized", "You are not authorized to look at this page.");

    }*/
}
