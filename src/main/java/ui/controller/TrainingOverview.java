package ui.controller;

import domain.model.Training;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TrainingOverview extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String filter = request.getParameter("filter");
        HttpSession session = request.getSession();
        User log = (User) session.getAttribute("user");
        if (log != null) {
            if (filter != null){
                request.setAttribute("allTrainings", service.getAllTrainingsFilter(filter, log));
            }
            else {
                request.setAttribute("allTrainings", service.getAllTrainings(log));
            }
        }
        return "trainingoverview.jsp";
    }
}
