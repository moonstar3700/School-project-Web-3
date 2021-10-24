package ui.controller;

import domain.model.User;
import domain.service.DbException;

import javax.servlet.http.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Login extends RequestHandler{
    ArrayList<String> errors = new ArrayList<String>();

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        checkEmail(request, errors);

        if (errors.size() == 0) {
            try {
                return "index.jsp";
            }
            catch (Exception exc) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                return "index.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            return "index.jsp";
        }
    }

    private void checkEmail(HttpServletRequest request, ArrayList<String> errors){
        List<User> list = service.getAll();
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        boolean check = false;

        try {
            for (User u : list) {
                if (mail.equals(u.getEmail())) {
                    if (u.isCorrectPassword(password)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", u);
                        check = true;
                    }
                }
            }
            if (!check) {throw new IllegalArgumentException("No valid email/password");}

        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage()); }

        /*List<User> list = service.getAll();
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            for (User u : list) {
                if (mail.equals(u.getEmail())) {
                    if (u.isCorrectPassword(password)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", u);
                    }
                }
            }
        }
        catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
        }*/

    }
}


/*    private String helloagain(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Person person = (Person) request.getSession().getAttribute("user");
        person.setPassword("ttttttt");
        return "helloagain.jsp";
    }
    private String hello(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        Person person = users.getUserWithName(name);
        if (person != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", person);
            return "hello.jsp";
        } else {
            request.setAttribute("message", "Not a registered user. Use 'Elke' or 'Mieke'.");
            return "index.jsp";
        }
    }*/