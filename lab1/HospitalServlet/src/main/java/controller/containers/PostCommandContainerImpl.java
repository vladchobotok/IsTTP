package controller.containers;

import controller.commands.*;
import controller.commands.admin.*;
import controller.commands.authorization.LoginCommand;
import controller.commands.authorization.RegisterCommand;
import controller.commands.doctor.CompleteAssignment;
import controller.commands.doctor.CreateAssignment;
import controller.commands.doctor.DefineDiagnosis;
import controller.commands.homepages.*;
import controller.commands.patient.DownloadCard;
import model.dao.classes.AssignmentDAOImpl;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static constants.HospitalUriPaths.*;

public class PostCommandContainerImpl implements CommandContainer{

    private final Map<String, Command> postCommands = new HashMap<>();
    private static final Logger logger = Logger.getLogger(PostCommandContainerImpl.class);

    public PostCommandContainerImpl(){
        postCommands.put(HOME_HOSPITAL, new HomeCommand());
        postCommands.put(LOGIN, new LoginCommand());
        postCommands.put(REGISTER, new RegisterCommand());
        postCommands.put(ADMIN_HOME, new AdminHomeCommand());
        postCommands.put(PATIENT_HOME, new PatientHomeCommand());
        postCommands.put(DOCTOR_HOME, new DoctorHomeCommand());
        postCommands.put(NURSE_HOME, new NurseHomeCommand());
        postCommands.put(ADMIN_DOCTORS_PAGE, new DoctorsList());
        postCommands.put(ADMIN_PATIENTS_PAGE, new PatientsList());
        postCommands.put(ADMIN_SORTING_DOCTORS, new SortingCommand());
        postCommands.put(ADMIN_SORTING_PATIENTS, new SortingCommand());
        postCommands.put(ADMIN_REGISTER_PATIENT, new RegisterPatient());
        postCommands.put(ADMIN_REGISTER_DOCTOR, new RegisterDoctor());
        postCommands.put(ADMIN_ASSIGN_DOCTOR, new AssignDoctor());
        postCommands.put(DOCTOR_COMPLETE_ASSIGNMENT, new CompleteAssignment());
        postCommands.put(DOCTOR_CREATE_ASSIGNMENT, new CreateAssignment());
        postCommands.put(DOCTOR_DEFINE_DIAGNOSIS, new DefineDiagnosis());
    }

    @Override
    public Command getCommand(String uri) {
        Command command = postCommands.get(uri);
        if (command == null) {
            return postCommands.get(HOME_HOSPITAL);
        }
        logger.info("Post command executed: " + uri);
        return command;
    }

}
