package ui.controller;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmDelete extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int userid = Integer.parseInt(request.getParameter("userid"));
        request.setAttribute("userid", userid);
        User user = service.get(userid);
        String firstName = user.getFirstName();
        request.setAttribute("firstName", firstName);
        String lastName = user.getLastName();
        request.setAttribute("lastName", lastName);
        String group = user.getGroup().getStringValue();
        request.setAttribute("group", group);
        return "confirmdelete.jsp";
    }
}
