package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTraining extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int trainingid = Integer.parseInt(request.getParameter("trainingid"));
        try {
            service.getTraining(trainingid);
        }
        catch (Exception exc) {
            request.setAttribute("errors", exc.getMessage());
            return "Controller?command=TrainingOverview";
        }
        service.deleteTraining(trainingid);
        return "Controller?command=TrainingOverview";
    }
}
