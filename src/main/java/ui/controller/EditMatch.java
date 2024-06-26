package ui.controller;

import domain.model.DomainException;
import domain.model.Match;
import domain.model.User;
import domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EditMatch extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();
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
        } catch (DomainException | DbException exc) {
            request.setAttribute("errors", exc.getMessage());
            return "Controller?command=UserOverview";
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
        setHome(match, request, response, errors);
        setAway(match, request, response, errors);
        if (request.getParameter("winner") == null) {
            setDate(match, request, response, errors);
            setTime(match, request, response, errors);
        }
        if (request.getParameter("winner") != null) {
            setWinner(match, request, response, errors);
            setWinnerRegistration(match, request, response, errors);
        }


        if (errors.size() == 0) {
            try {
                service.updateMatch(match);
                response.sendRedirect("Controller?command=MatchOverview&confirmation=succesEdit");
                return "Controller?command=MatchOverview";
            } catch (DomainException | IOException | DbException exc) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                return "editMatch.jsp";
            }
        } else {
            request.setAttribute("errors", errors);
            return "editMatch.jsp";
        }
    }

    private void setHome(Match match, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        String home = request.getParameter("home");
        try {
            match.setHome(home);
            request.setAttribute("home", home);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setAway(Match match, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        String away = request.getParameter("away");
        try {
            match.setAway(away);
            request.setAttribute("away", away);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setDate(Match match, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        try {
            match.setDate(date);
            request.setAttribute("date", date);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setTime(Match match, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        LocalTime time = LocalTime.parse(request.getParameter("time"));
        try {
            match.setTime(time);
            request.setAttribute("time", time);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setWinner(Match match, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        String winner = request.getParameter("winner");
        try {
            match.setWinner(winner);
            request.setAttribute("winner", winner);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setWinnerRegistration(Match match, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        LocalDate winnerRegistration = LocalDate.now();
        try {
            match.setWinnerregistration(winnerRegistration);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }


}
