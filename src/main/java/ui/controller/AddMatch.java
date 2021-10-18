package ui.controller;

import domain.model.Match;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddMatch extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        Match match = new Match();
        setHome(match, request, response);
        setAway(match, request, response);
        setDate(match, request, response);
        setTime(match, request, response);


        if (errors.size() == 0) {
            try {
                service.addMatch(match);
                return "index.jsp";
            }
            catch (Exception exc ) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                return "register.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            return "register.jsp";
        }
    }

    private void setHome(Match match, HttpServletRequest request, HttpServletResponse response) {
        String home = request.getParameter("home");
        try {
            match.setHome(home);
            request.setAttribute("homePreviousValue", home);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setAway(Match match, HttpServletRequest request, HttpServletResponse response) {
        String away = request.getParameter("away");
        try {
            match.setAway(away);
            request.setAttribute("awayPreviousValue", away);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setDate(Match match, HttpServletRequest request, HttpServletResponse response) {
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        try {
            match.setDate(date);
            request.setAttribute("datePreviousValue", date);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setTime(Match match, HttpServletRequest request, HttpServletResponse response) {
        LocalDateTime time = LocalDateTime.parse(request.getParameter("time"));
        try {
            match.setTime(time);
            request.setAttribute("timePreviousValue", time);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }


}
