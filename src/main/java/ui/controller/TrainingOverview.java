package ui.controller;

import domain.model.Role;
import domain.model.Training;
import domain.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TrainingOverview extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response)  {
        String filter = request.getParameter("filter");
        HttpSession session = request.getSession();
        User log = (User) session.getAttribute("user");
        Role[] roles = {Role.TRAINER};
        //try {
            checkRole(request, roles);
        /*}catch (NotAuthorizedException e){
            request.setAttribute("notAuthorized", "You are not authorized to look at this page.");
        }*/

        if (log != null) {
            if (filter != null){
                request.setAttribute("allTrainings", service.getAllTrainingsFilter(filter, log));
            }
            else {
                request.setAttribute("allTrainings", service.getAllTrainings(log));
            }
        }
        return "trainingoverview.jsp";
    }

    public static void checkRole(HttpServletRequest request, Role[] roles) {
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

    }
}
