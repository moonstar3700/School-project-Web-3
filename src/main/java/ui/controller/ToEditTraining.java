package ui.controller;

import domain.model.Training;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;

public class ToEditTraining extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int trainingid;
        try {
            trainingid = Integer.parseInt(request.getParameter("trainingid"));
        } catch (NumberFormatException e) {
            request.setAttribute("errors", "No training selected");
            return "Controller?command=TrainingOverview";
        }

        request.setAttribute("trainingid", trainingid);
        try {
            service.getTraining(trainingid);
        }
        catch (Exception exc ) {
            request.setAttribute("errors", exc.getMessage());
            return "Controller?command=TrainingOverview";
        }
        Training training = service.getTraining(trainingid);
        if (training == null) {
            request.setAttribute("errors", "Training does not exist");
            return "Controller?command=TrainingOverview";
        }
        LocalDate date = training.getDate();
        request.setAttribute("date", date);
        LocalTime start = training.getStart();
        request.setAttribute("start", start);
        LocalTime end = training.getEnd();
        request.setAttribute("end", end);
        return "edittraining.jsp";
    }
}
