package controller.servlet;

import controller.commands.Command;
import controller.containers.CommandContainer;
import utils.factory.command.ContainerCreator;
import utils.factory.command.GetContainerCreatorImpl;
import utils.factory.command.PostContainerCreatorImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class HospitalServlet extends HttpServlet {
    private static final ContainerCreator getCommandsContainer = new GetContainerCreatorImpl();
    private static final ContainerCreator postCommandsContainer = new PostContainerCreatorImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        executeCommand(req, resp, getCommandsContainer, uri);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        executeCommand(req, resp, postCommandsContainer, uri);
    }

    private void executeCommand(HttpServletRequest request, HttpServletResponse response, ContainerCreator containerCreator, String uri) throws ServletException, IOException{
        CommandContainer commandContainer = containerCreator.createContainer();
        Command command = commandContainer.getCommand(uri);
        command.execute(request, response);
    }
}
