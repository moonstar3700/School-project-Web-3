package ui.controller;

import domain.model.Role;
import domain.model.User;
import domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Register extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        User user = new User();
        setFirstName(user, request, errors);
        setLastName(user, request, errors);
        setEmail(user, request, errors);
        setPassword(user, request, errors);
        setGroup(user, request, errors);
        user.setRole(Role.TRAINER); // weet niet of die zo juist is als een default. Maar gaan we waarschijnlijk in de toekomst sowieso moeten veranderen, want coördinators gaan ook nog bestaan.

        if (errors.size() == 0) {
            try {
                service.add(user);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                return "index.jsp";
            }
            catch (Exception exc ) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                return "register.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            return "register.jsp";
        }
    }

    private void setFirstName(User user, HttpServletRequest request, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try {
            user.setFirstName(firstName);
            request.setAttribute("firstNamePreviousValue", firstName);
        }
        catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setLastName(User user, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try {
            user.setLastName(lastName);
            request.setAttribute("lastNamePreviousValue", lastName);
        }
        catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setEmail(User user, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try {
            user.setEmail(email);
            request.setAttribute("emailPreviousValue", email);
        }
        catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setPassword(User user, HttpServletRequest request, ArrayList<String> errors) {
        String password = request.getParameter("password");
        try {
            user.setHashedPassword(password);
            request.setAttribute("passwordPreviousValue", password);
        }
        catch (IllegalArgumentException | UnsupportedEncodingException | NoSuchAlgorithmException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setGroup(User user, HttpServletRequest request, ArrayList<String> errors) {
        String group = request.getParameter("group");
        try {
            user.setGroup(group);
            request.setAttribute("groupPreviousValue", group);
        }
        catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }
}
