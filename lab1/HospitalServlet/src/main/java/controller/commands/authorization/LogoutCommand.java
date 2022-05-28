package controller.commands.authorization;

import constants.HospitalJspPaths;
import controller.commands.Command;
import utils.validation.AuthenticationValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {
    AuthenticationValidation authenticationValidation = new AuthenticationValidation();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        authenticationValidation.logout(httpSession);
        request.getRequestDispatcher(HospitalJspPaths.HOME_HOSPITAL).forward(request, response);
    }
}
