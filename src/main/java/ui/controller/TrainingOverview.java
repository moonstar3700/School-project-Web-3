package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TrainingOverview extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String filter = request.getParameter("filter");
        if (filter != null){
            request.setAttribute("allTrainings", service.getAllTrainingsFilter(filter));
        }
        else {
            request.setAttribute("allTrainings", service.getAllTrainings());
        }
        return "trainingoverview.jsp";
    }
}
