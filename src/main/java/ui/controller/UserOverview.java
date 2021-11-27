package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserOverview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        User log = (User) session.getAttribute("user");
        if (log == null){
            request.setAttribute("notAuthorized", "You are not authorized to look at this page.");
        } else if (log.getRole() == Role.TRAINER){
            request.setAttribute("trainer", true);
            request.setAttribute("allUsers", service.getAllGroup(log));
        }else if (log.getRole() == Role.COORDINATOR){
            request.setAttribute("allUsers", service.getAll());
            request.setAttribute("coordinator", true);
        }else{
            request.setAttribute("allUsers", service.getAll());
            request.setAttribute("admin", true);
        }

        return "useroverview.jsp";
    }
}
