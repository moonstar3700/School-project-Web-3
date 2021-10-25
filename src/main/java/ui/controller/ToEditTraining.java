package ui.controller;

import domain.model.Training;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;

public class ToEditTraining extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
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
        LocalDate date = training.getDate();
        request.setAttribute("date", date);
        LocalTime start = training.getStart();
        request.setAttribute("start", start);
        LocalTime end = training.getEnd();
        request.setAttribute("end", end);
        return "edittraining.jsp";
    }
}
