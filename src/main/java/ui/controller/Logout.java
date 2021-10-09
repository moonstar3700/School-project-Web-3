package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class Logout extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.invalidate();
        return "index.jsp";
    }

    /*request.getSession().invalidate();
    return "index.jsp"*/
}
