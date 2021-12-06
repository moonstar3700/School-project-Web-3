package ui.controller;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Delete extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int userid = Integer.parseInt(request.getParameter("userid"));
        try {
            service.get(userid);
        }
        catch (Exception exc) {
            request.setAttribute("errors", exc.getMessage());
            return "Controller?command=UserOverview";
        }
        service.delete(userid);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getUserid() == userid){
            session.invalidate();
        }
        try {
            response.sendRedirect("Controller?command=UserOverview&confirmation=succesDelete");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Controller?command=UserOverview";
    }
}
