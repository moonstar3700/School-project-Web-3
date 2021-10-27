package ui.controller;

import domain.model.DomainException;
import domain.model.Match;
import domain.model.Training;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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
        checkTraining(training, request, response, errors, user);

        if (errors.size() == 0) {
            try {
                service.addTraining(training, user);
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
            training.forceDate(date);
            errors.add(exc.getMessage());
        }
        catch (DateTimeParseException e){
            errors.add(e.getMessage());
        }
    }
    private void setStart(Training training, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        try {
            LocalTime start = LocalTime.parse(request.getParameter("start"));
            training.setStart(start);
            request.setAttribute("startPreviousValue", start);
        }
        catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
        catch (DateTimeParseException e){
            errors.add("Bad input for start time");
        }
    }

    private void setEnd(Training training, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        try {
            LocalTime end = LocalTime.parse(request.getParameter("end"));
            training.setEnd(end);
            request.setAttribute("endPreviousValue", end);
        }
        catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
        catch (DateTimeParseException e){
            errors.add("Bad input for end time");
        }
    }

    private void checkTraining(Training training, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors, User user){
            List<Training> list = service.getAllTrainings(user);
            try {
                for (Training item: list){
                    if (item.getUserID() == training.getUserID()){
                        item.checkTrainingMoment(training);
                    }
                }
            }
            catch (DomainException exc) {
                errors.add(exc.getMessage());
            }
    }
}
