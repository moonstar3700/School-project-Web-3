package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.ArrayList;

public class Logout extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        ArrayList<String> errors = new ArrayList<String>();
        HttpSession session = request.getSession();
        try {
            session.invalidate();
            return "index.jsp";
        } catch (Exception exc){
            errors.add(exc.getMessage());
            request.setAttribute("errors", errors);
            return "index.jsp";
        }
    }

    /*request.getSession().invalidate();
    return "index.jsp"*/
}
