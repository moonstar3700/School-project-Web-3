package ui.controller;

import domain.model.Match;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmMatchDelete extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int matchid = Integer.parseInt(request.getParameter("matchid"));
        request.setAttribute("matchid", matchid);
        HttpSession session = request.getSession();
        User log = (User) session.getAttribute("user");
        if (log == null){
            request.setAttribute("errors", "U moet ingelogd zijn om wedstrijden te verwijderen");
            return "index.jsp";
        }
        Match match1 = service.getMatch(matchid);
        switch (log.getRole()) {
            case TRAINER:
                if (match1.getCreator().getUserid() != log.getUserid()) {
                    request.setAttribute("errors", "U heeft geen authenticatie rechten voor deze match");
                    return "Controller?command=MatchOverview";
                }
                break;
            case COORDINATOR:
                if (match1.getGroup() != log.getGroup()) {
                    request.setAttribute("errors", "U heeft geen authenticatie rechten voor deze match");
                    return "Controller?command=MatchOverview";
                }
                break;
        }
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
