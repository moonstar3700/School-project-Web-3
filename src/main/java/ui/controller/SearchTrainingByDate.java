package ui.controller;

import domain.model.DomainException;
import domain.model.Match;
import domain.model.Training;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchTrainingByDate extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (errors.size() == 0) {
            try {
                ArrayList<Training> foundTrainings = service.searchTrainingsByDate(date, user);
                if (foundTrainings.size() == 0) {
                    return "notFound.jsp";
                } else{
                    request.setAttribute("foundTrainings", foundTrainings);
                    request.setAttribute("date", date);
                    return "trainingFoundByDate.jsp";
                }
            }
            catch (DomainException exc ) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                return "searchTraining.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            return "searchTraining.jsp";
        }
    }


}
