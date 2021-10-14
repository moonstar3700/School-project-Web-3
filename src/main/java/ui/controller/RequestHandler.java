package ui.controller;

import domain.service.UserServiceInMemory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected UserServiceInMemory service;

    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response);

    public UserServiceInMemory getService() {
        return service;
    }

    public void setService(UserServiceInMemory service) {
        this.service = service;
    }
}
