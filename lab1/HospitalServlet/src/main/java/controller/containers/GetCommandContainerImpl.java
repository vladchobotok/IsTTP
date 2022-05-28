package controller.containers;

import constants.HospitalJspPaths;
import controller.commands.*;
import controller.commands.admin.DoctorsList;
import controller.commands.admin.PatientsList;
import controller.commands.authorization.LogoutCommand;
import controller.commands.homepages.*;
import controller.commands.patient.DownloadCard;
import model.dao.classes.AssignmentDAOImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static constants.HospitalJspPaths.REGISTER_DOCTOR;
import static constants.HospitalJspPaths.REGISTER_PATIENT;
import static constants.HospitalUriPaths.*;

public class GetCommandContainerImpl implements CommandContainer{

    private final Map<String, Command> getCommands = new HashMap<>();
    private static final Logger logger = Logger.getLogger(GetCommandContainerImpl.class);

    public GetCommandContainerImpl(){
        getCommands.put(HOME_HOSPITAL, new HomeCommand());
        getCommands.put(LOGIN, (req, res) -> forward(req, res, HospitalJspPaths.SIGN_IN));
        getCommands.put(REGISTER, (req, res) -> forward(req, res, HospitalJspPaths.SIGN_UP));
        getCommands.put(LOGOUT, new LogoutCommand());

        getCommands.put(ADMIN_HOME, new AdminHomeCommand());
        getCommands.put(PATIENT_HOME, new PatientHomeCommand());
        getCommands.put(DOCTOR_HOME, new DoctorHomeCommand());
        getCommands.put(NURSE_HOME, new NurseHomeCommand());
        getCommands.put(ADMIN_DOCTORS_PAGE, new DoctorsList());
        getCommands.put(ADMIN_PATIENTS_PAGE, new PatientsList());
        getCommands.put(DOWNLOAD_CARD, new DownloadCard());

        getCommands.put(ADMIN_REGISTER_PATIENT, (req, res) -> forward(req, res, REGISTER_PATIENT));
        getCommands.put(ADMIN_REGISTER_DOCTOR, (req, res) -> forward(req, res, REGISTER_DOCTOR));
    }

    @Override
    public Command getCommand(String uri) {
        Command command = getCommands.get(uri);
        if (command == null) {
            return getCommands.get(HOME_HOSPITAL);
        }
        logger.info("Get command executed: " + uri);
        return command;
    }

    private static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
