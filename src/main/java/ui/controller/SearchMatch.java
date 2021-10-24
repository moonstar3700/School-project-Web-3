package ui.controller;

import domain.model.DomainException;
import domain.model.Group;
import domain.model.Match;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class SearchMatch extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        String home = request.getParameter("home");
        String away = request.getParameter("away");
        String group =  request.getParameter("group");
        if (errors.size() == 0) {
            try {
                Match match = service.searchMatches(home, away, group);
                if (match == null) {
                    return "notFound.jsp";
                } else{
                    request.setAttribute("match", match);
                    return "matchFound.jsp";
                }
            }
            catch (DomainException exc ) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                return "searchMatch.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            return "searchMatch.jsp";
        }
    }


}
