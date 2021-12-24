package ui.controller;

import domain.model.Match;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;

public class ToEditMatch extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int matchid;
        try {
            matchid = Integer.parseInt(request.getParameter("matchid"));
        } catch (NumberFormatException e) {
            request.setAttribute("errors", "No match selected");
            return "Controller?command=MatchOverview";
        }

        request.setAttribute("matchid", matchid);
        try {
            service.getMatch(matchid);
        }
        catch (Exception exc ) {
            request.setAttribute("errors", exc.getMessage());
            return "Controller?command=MatchOverview";
        }
        Match match = service.getMatch(matchid);
        String home = match.getHome();
        request.setAttribute("home", home);
        String away = match.getAway();
        request.setAttribute("away", away);
        LocalDate date = match.getDate();
        request.setAttribute("date", date);
        LocalTime time = match.getTime();
        request.setAttribute("time", time);
        String winner = match.getWinner();
        request.setAttribute("winner", winner);
        return "editMatch.jsp";
    }
}
