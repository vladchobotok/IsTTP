package controller.commands.authorization;

import constants.HospitalJspPaths;
import controller.commands.Command;
import model.entity.Patient;
import model.entity.User;
import model.service.PatientService;
import utils.validation.AuthenticationValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class RegisterCommand implements Command {
    private final AuthenticationValidation authenticationValidation = new AuthenticationValidation();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        LocalDate birthday;
        if(!request.getParameter("birthday").equals("")){
            birthday = Date.valueOf((request.getParameter("birthday"))).toLocalDate();
        }
        else {
            birthday = null;
        }
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        int role = 1;

        User regUser = new User(name, surname, birthday, email, password, role);

        if (authenticationValidation.registerPatient(regUser, confirmPassword, request)) {
            request.getRequestDispatcher(HospitalJspPaths.SIGN_IN).forward(request, response);
            return;
        }

        request.getRequestDispatcher(HospitalJspPaths.SIGN_UP).forward(request, response);
    }
}
