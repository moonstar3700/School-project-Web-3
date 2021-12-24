package ui.controller;

import domain.model.DomainException;
import domain.model.Match;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class SearchMatchByDate extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        try {
            LocalDate date = LocalDate.parse(request.getParameter("date"));
        } catch (DateTimeParseException e) {
            errors.add("Ongeldige datum");
            request.setAttribute("errors", errors);
            return "searchMatch.jsp";
        }
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        if (errors.size() == 0) {
            try {
                ArrayList<Match> foundMatches = service.searchMatchesByDate(date);
                if (foundMatches.size() == 0) {
                    return "notFound.jsp";
                } else{
                    request.setAttribute("foundMatches", foundMatches);
                    request.setAttribute("date", date);
                    return "matchFoundByDate.jsp";
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
