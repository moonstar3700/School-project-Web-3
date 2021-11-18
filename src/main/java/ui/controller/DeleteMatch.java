package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteMatch extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int matchid = Integer.parseInt(request.getParameter("matchid"));
        try {
            service.getMatch(matchid);
        }
        catch (Exception exc) {
            request.setAttribute("errors", exc.getMessage());
            return "Controller?command=MatchOverview";
        }
        service.deleteMatch(matchid);
        try {
            response.sendRedirect("Controller?command=MatchOverview&confirmation=succesDelete");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Controller?command=MatchOverview";
    }
}
