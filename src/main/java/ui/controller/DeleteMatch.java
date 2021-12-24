package ui.controller;

import domain.model.Match;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteMatch extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int matchid = Integer.parseInt(request.getParameter("matchid"));

        HttpSession session = request.getSession();
        User log = (User) session.getAttribute("user");
        if (log == null){
            request.setAttribute("errors", "U moet ingelogd zijn om wedstrijden te verwijderen");
            return "index.jsp";
        }
        try {
            service.getMatch(matchid);
        }
        catch (Exception exc) {
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
        service.deleteMatch(matchid);
        try {
            response.sendRedirect("Controller?command=MatchOverview&confirmation=succesDelete");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Controller?command=MatchOverview";
    }
}
