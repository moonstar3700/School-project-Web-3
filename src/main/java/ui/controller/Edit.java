package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Edit extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();
        int userid = Integer.parseInt(request.getParameter("userid"));
        request.setAttribute("userid", userid);
        try {
            service.get(userid);
        }
        catch (Exception exc ) {
            request.setAttribute("errors", exc.getMessage());
            return "Controller?command=Overview";
        }
        User user = service.get(userid);
        setFirstName(user, request, errors);
        setLastName(user, request, errors);
        setEmail(user, request, errors);
        setRole(user, request, errors);
        setGroup(user, request, errors);


        if (errors.size() == 0) {
            try {
                service.update(user);
                return "Controller?command=Overview";
            }
            catch (Exception exc ) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                return "edit.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            return "edit.jsp";
        }
    }

    private void setFirstName(User user, HttpServletRequest request, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try {
            user.setFirstName(firstName);
            request.setAttribute("firstName", firstName);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setLastName(User user, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try {
            user.setLastName(lastName);
            request.setAttribute("lastName", lastName);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setEmail(User user, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        List<User> users = service.getAll();
        for (User u : users) {
            if (u.getEmail().equals(email) && user.getUserid() != u.getUserid()) {
                errors.add("Email already in use");
            }
        }
        try {
            user.setEmail(email);
            request.setAttribute("email", email);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setRole(User user, HttpServletRequest request, ArrayList<String> errors) {
        String role = request.getParameter("role");
        try {
            user.setRole(role);
            request.setAttribute("role", role);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setGroup(User user, HttpServletRequest request, ArrayList<String> errors) {
        String group = request.getParameter("group");
        try {
            user.setGroup(group);
            request.setAttribute("group", group);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }
}
