package ui.controller;

import domain.model.Match;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;

public class ToEditMatch extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int matchid;

        HttpSession session = request.getSession();
        User log = (User) session.getAttribute("user");
        if (log == null){
            request.setAttribute("errors", "U moet ingelogd zijn om wedstrijden aan te passen");
            return "index.jsp";
        }

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
        switch (log.getRole()) {
            case TRAINER:
                if (match.getCreator().getUserid() != log.getUserid()) {
                    request.setAttribute("errors", "U heeft geen authenticatie rechten voor deze match");
                    return "Controller?command=MatchOverview";
                }
                break;
            case COORDINATOR:
                if (match.getGroup() != log.getGroup()) {
                    request.setAttribute("errors", "U heeft geen authenticatie rechten voor deze match");
                    return "Controller?command=MatchOverview";
                }
                break;
        }
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
