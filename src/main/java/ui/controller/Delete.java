package ui.controller;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int userid = Integer.parseInt(request.getParameter("userid"));
        service.delete(userid);
        return "Controller?command=Overview";
    }
}
