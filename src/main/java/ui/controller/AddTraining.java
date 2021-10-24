package ui.controller;

import domain.model.DomainException;
import domain.model.Match;
import domain.model.Training;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AddTraining extends RequestHandler{


    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        Training training = new Training();
        setDate(training, request, response, errors);
        setStart(training, request, response, errors);
        setEnd(training, request, response, errors);
        //training.calculateTime();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        training.setCreator(user);
        training.setCreatorId(user);

        if (errors.size() == 0) {
            try {
                service.addTraining(training);
                return "Controller?command=TrainingOverview";
            }
            catch (DomainException exc ) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                return "registertraining.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            return "registertraining.jsp";
        }
    }

    private void setDate(Training training, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        try {
            training.setDate(date);
            request.setAttribute("datePreviousValue", date);
        }
        catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }
    private void setStart(Training training, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        LocalTime start = LocalTime.parse(request.getParameter("start"));
        try {
            training.setStart(start);
            request.setAttribute("startPreviousValue", start);
        }
        catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setEnd(Training training, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        LocalTime end = LocalTime.parse(request.getParameter("end"));
        try {
            training.setEnd(end);
            request.setAttribute("endPreviousValue", end);
        }
        catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

}
