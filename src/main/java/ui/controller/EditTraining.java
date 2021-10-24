package ui.controller;

import domain.model.DomainException;
import domain.model.Training;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EditTraining extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();
        int trainingid = Integer.parseInt(request.getParameter("trainingid"));
        request.setAttribute("trainingid", trainingid);
        try {
            service.getTraining(trainingid);
        }
        catch (Exception exc ) {
            request.setAttribute("errors", exc.getMessage());
            return "Controller?command=TrainingOverview";
        }
        Training training = service.getTraining(trainingid);
        setDate(training, request, response, errors);
        setStart(training, request, response, errors);
        setEnd(training, request, response, errors);
        checkTraining(training, request, response, errors);

        if (errors.size() == 0) {
            try {
                service.updateTraining(training);
                return "Controller?command=TrainingOverview";
            }
            catch (Exception exc ) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                return "edittraining.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            return "edittraining.jsp";
        }
    }

    private void setDate(Training training, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        try {
            training.setDate(date);
            request.setAttribute("date", date);
        }
        catch (Exception exc) {
            training.forceDate(date);
            errors.add(exc.getMessage());
        }
    }
    private void setStart(Training training, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        LocalTime start = LocalTime.parse(request.getParameter("start"));
        try {
            training.setStart(start);
            request.setAttribute("start", start);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setEnd(Training training, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors) {
        LocalTime end = LocalTime.parse(request.getParameter("end"));
        try {
            training.setEnd(end);
            request.setAttribute("end", end);
        }
        catch (Exception exc) {
            errors.add(exc.getMessage());
        }
    }
    private void checkTraining(Training training, HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors){
        List<Training> list = service.getAllTrainings();
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
