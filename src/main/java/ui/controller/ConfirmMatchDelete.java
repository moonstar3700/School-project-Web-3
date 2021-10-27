package ui.controller;

import domain.model.Match;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmMatchDelete extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int matchid = Integer.parseInt(request.getParameter("matchid"));
        request.setAttribute("matchid", matchid);
        try {
            Match match = service.getMatch(matchid);
            String home = match.getHome();
            request.setAttribute("home", home);
            String away = match.getAway();
            request.setAttribute("away", away);
            String group = match.getGroup().getStringValue();
            request.setAttribute("group", group);
            return "confirmMatchDelete.jsp";
        }
        catch (Exception exc) {
            request.setAttribute("errors", exc.getMessage());
            return "Controller?command=MatchOverview";
        }




    }
}
