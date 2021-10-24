package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TrainingOverview extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("allTrainings", service.getAllTrainings());
        request.setAttribute("allTrainingdurations", service.getDurations());
        return "trainingoverview.jsp";
    }
}
