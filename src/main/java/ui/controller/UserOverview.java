package ui.controller;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserOverview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("allUsers", service.getAll());
        HttpSession session = request.getSession();
        User log = (User) session.getAttribute("user");
        if (log == null){
            request.setAttribute("notAuthorized", "You are not authorized to look at this page.");
        }
        return "useroverview.jsp";
    }
}
