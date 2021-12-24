package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmDelete extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int userid;

        HttpSession session = request.getSession();
        User log = (User) session.getAttribute("user");
        if (log == null){
            request.setAttribute("errors", "u moet ingelogd zijn om users te verwijderen");
            return "index.jsp";
        }

        try {
            userid = Integer.parseInt(request.getParameter("userid"));
        } catch (NumberFormatException e) {
            request.setAttribute("errors", "No user selected");
            return "Controller?command=UserOverview";
        }

        User user = service.get(userid);
        if (log.getRole() == Role.ADMIN && log.getUserid() != user.getUserid() || log.getRole() == Role.COORDINATOR && user.getGroup() != log.getGroup()){
            request.setAttribute("errors", "U heeft geen authenticatie om deze user te verwijderen");
            return "Controller?command=UserOverview";
        }

        request.setAttribute("userid", userid);
        try {
            String firstName = user.getFirstName();
            request.setAttribute("firstName", firstName);
            String lastName = user.getLastName();
            request.setAttribute("lastName", lastName);
            String group = user.getGroup().getStringValue();
            request.setAttribute("group", group);
            return "confirmdelete.jsp";
        }
        catch (Exception exc) {
            request.setAttribute("errors", exc.getMessage());
            return "Controller?command=UserOverview";
        }




    }
}
