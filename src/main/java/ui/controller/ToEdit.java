package ui.controller;

import domain.model.Group;
import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToEdit extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int userid;
        try  {
            userid = Integer.parseInt(request.getParameter("userid"));
        } catch (NumberFormatException e) {
            request.setAttribute("errors", "No user selected");
            return "Controller?command=UserOverview";
        }

        request.setAttribute("userid", userid);
        try {
            service.get(userid);
        }
        catch (Exception exc ) {
            request.setAttribute("errors", exc.getMessage());
            return "Controller?command=UserOverview";
        }
        User user = service.get(userid);
        String firstName = user.getFirstName();
        request.setAttribute("firstName", firstName);
        String lastName = user.getLastName();
        request.setAttribute("lastName", lastName);
        String email = user.getEmail();
        request.setAttribute("email", email);
        String role = user.getRole().getStringValue();
        request.setAttribute("role", role);
        String group = user.getGroup().getStringValue();
        request.setAttribute("group", group);
        return "edit.jsp";
    }
}
