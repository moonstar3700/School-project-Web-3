package ui.controller;

import domain.model.Match;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class AddMatch extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        Match match = new Match();
        setHome(match, request, response, errors);
        setAway(match, request, response, errors);
        setDate(match, request, response, errors);
        setTime(match, request, response, errors);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        match.setCreator(user);
        match.setGroup(user.getGroup());

        if (errors.size() == 0) {
            try {
                service.addMatch(match);
                return "Controller?command=MatchOverview";
            }
            catch (Exception exc ) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                return "registermatch.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            return "registermatch.jsp";
        }
    }

    private void setHome(Match match, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        String home = request.getParameter("home");
        try {
            match.setHome(home);
            request.setAttribute("homePreviousValue", home);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setAway(Match match, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        String away = request.getParameter("away");
        try {
            match.setAway(away);
            request.setAttribute("awayPreviousValue", away);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setDate(Match match, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        try {
            match.setDate(date);
            request.setAttribute("datePreviousValue", date);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setTime(Match match, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        LocalTime time = LocalTime.parse(request.getParameter("time"));
        try {
            match.setTime(time);
            request.setAttribute("timePreviousValue", time);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }


}
