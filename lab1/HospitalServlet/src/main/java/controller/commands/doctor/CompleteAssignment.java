package controller.commands.doctor;

import constants.HospitalJspPaths;
import constants.HospitalUriPaths;
import controller.commands.Command;
import model.entity.Patient;
import model.service.PatientService;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CompleteAssignment implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int patientId = Integer.parseInt(request.getParameter("patientId2"));

        PatientService patientService = new PatientService();
        Patient currentPatient = patientService.findPatientById(patientId);

        UserService userService = new UserService();
        userService.updateRole(5, currentPatient.getUser().getId());


        List<Patient> patients = (List<Patient>)session.getAttribute("patients");
        patients.remove(currentPatient);
        session.setAttribute("allPatients", patientService.findAll());
        session.setAttribute("patients", patients);

        if(session.getAttribute("currentNurse") != null){
            response.sendRedirect(HospitalUriPaths.NURSE_HOME);
        } else if (session.getAttribute("currentDoctor") != null) {
            response.sendRedirect(HospitalUriPaths.DOCTOR_HOME);
        }
    }
}
