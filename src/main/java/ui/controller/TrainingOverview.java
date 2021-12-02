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
        Role[] roles = {Role.TRAINER, Role.COORDINATOR, Role.ADMIN};
        try {
            Utility.checkRole(request, roles);
        }catch (NotAuthorizedException e){
            request.setAttribute("notAuthorized", "You are not authorized to look at this page.");
        }
        if (log != null) {
            if (filter != null && log.getRole() == Role.TRAINER){
                request.setAttribute("allTrainings", trainingen.put(log, service.getAllTrainingsFilter(filter, log)));
            }
            else if (log.getRole() == Role.TRAINER){
                List<Training> trainingenUser = service.getAllTrainings(log);
                trainingen.put(log, trainingenUser);
                request.setAttribute("allTrainings", trainingen);
            }
            else if (log.getRole() == Role.COORDINATOR){
                List<User> users = service.getAllGroupWithTraining(log);
                for(User u: users){
                    List<Training> trainingenUser = service.getAllTrainings(u);
                    trainingen.put(u, trainingenUser);
                }
                request.setAttribute("allTrainings", trainingen);
            }
        }
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
